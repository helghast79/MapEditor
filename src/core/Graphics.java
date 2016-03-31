package core;

import appobjects.Cell;
import appobjects.ColorTool;
import appobjects.Cursor;
import appobjects.Tool;
import loadsave.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import utilities.ColorEnum;
import utilities.Direction;

import java.io.File;
import java.util.Stack;

/**
 * Created by Mariana on 25/02/16.
 */
public class Graphics {


    //cells
    protected Cell[][] cells = null;
    protected int totalCols, totalRows;
    protected int cellSize;


   protected Stack<char[][]> undoMapStack;
   protected Stack<char[][]> redoMapStack;

    //background canvas
    private Rectangle field = null;

    //class to listen to user input
    //private UserInputs userInputs;

    //cursor object
    protected Cursor cursor;

    //current paint color
    protected ColorEnum currentColor = ColorEnum.WHITE;

    //tools
    protected Tool[] verticalToolbar;
    protected ColorTool[] horizontalColorbar;
    protected ColorTool[] horizontalColorbar2;

    protected char[][] cellAsciiTemp; //holds the value of the cells to be commited to de undo stack - init is in the create cells
    protected boolean mousePressedFlag = false;
    protected boolean changesDetectedFlag = false;

    //grid positioning functions
    private int gridRowToY(int row) {
        return Config.BORDER_Y + cellSize * row;
    }

    private int gridColToX(int col) {
        return Config.BORDER_X + cellSize * col;
    }

    protected int cellsRowToY(int row) {
        return gridRowToY(row) + Config.C_CANVAS_CELL_GAP;
    }

    protected int cellsColToX(int col) {
        return gridColToX(col) + Config.C_CANVAS_CELL_GAP;
    }

    protected int xToCellCol(int x) {
        return (int) (Math.floor((x - Config.C_CANVAS_CELL_GAP - Config.BORDER_X) / cellSize));
    }

    protected int yToCellRow(int y) {
        return (int) (Math.floor((y - Config.C_CANVAS_CELL_GAP - Config.BORDER_Y) / cellSize));
    }


    //cells
    public void createCells(int totalRows, int totalCols) {

        if (undoMapStack == null) {
            initUndoStack();
        }
        if (redoMapStack == null) {
            initRedoStack();
        }

        //init the cellAsciiTemp
        cellAsciiTemp = new char[totalRows][totalCols];


        //delete cells if the exist already
        if (cells != null) {
            for (int i = 0; i < this.totalRows; i++) {

                for (int j = 0; j < this.totalCols; j++) {
                    cells[i][j].deleteRepresentation();
                }
            }
            cells = null;
        }

        //if total columns or rows are set to 0 reset them to default size
        if (totalCols == 0) {
            totalCols = Config.C_PREF_TOTAL_COLS;
        }

        if (totalRows == 0) {
            totalRows = Config.C_PREF_TOTAL_ROWS;
        }

        //set global variables total rows and columns
        this.totalRows = totalRows;
        this.totalCols = totalCols;

        //initialize cells array
        cells = new Cell[totalRows][totalCols];

        //set the size of each cell to fit the default canvas size
        cellSize = (int) Math.floor((Config.C_CANVAS_PREF_WIDTH - (Config.C_TOOL_SIZE + Config.C_TOOLBAR_FRAME_THICKNESS * 2) - Config.C_TOOLBAR_FRAME_THICKNESS) / (Math.max(totalRows, totalCols)));

        //initialize each cell
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                cells[i][j] = new Cell();
                cells[i][j].initCell(ColorEnum.WHITE.getAsciiFromColor(ColorEnum.WHITE.getColor()), gridColToX(j), gridRowToY(i), cellSize);
            }
        }


    }

    public void createCells(char[][] asciiMap) {


        if (undoMapStack == null) {
            initUndoStack();
        }
        if (redoMapStack == null) {
            initRedoStack();
        }

        //init the cellAsciiTemp
        cellAsciiTemp = new char[totalRows][totalCols];


        //delete cells if the exist already
        if (cells != null) {

            for (int i = 0; i < this.totalRows; i++) {

                for (int j = 0; j < this.totalCols; j++) {
                    cells[i][j].deleteRepresentation();
                }
            }
            cells = null;
        }

        this.totalRows = asciiMap.length;
        this.totalCols = asciiMap[0].length;

        cells = new Cell[this.totalRows][this.totalCols];

        cellSize = (int) Math.floor((Config.C_CANVAS_PREF_WIDTH - (Config.C_TOOL_SIZE + Config.C_TOOLBAR_FRAME_THICKNESS * 2) - Config.C_TOOLBAR_FRAME_THICKNESS) / (Math.max(this.totalRows, this.totalCols)));

        for (int i = 0; i < this.totalRows; i++) {

            for (int j = 0; j < this.totalCols; j++) {
                cells[i][j] = new Cell();
                cells[i][j].initCell(asciiMap[i][j], gridColToX(j), gridRowToY(i), cellSize);
            }
        }




    }

    public void drawCells() {
        for (int i = 0; i < this.totalRows; i++) {
            for (int j = 0; j < this.totalCols; j++) {
                cells[i][j].drawRepreentation();
            }
        }
    }

    protected char[][] getCellsAsciiMap() {
        char[][] cellAsciiMap = new char[totalRows][totalCols];

        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                cellAsciiMap[i][j] = cells[i][j].getAsciiVal();
            }

        }
        return cellAsciiMap;
    }

    protected void changeCellColor() {
        cells[yToCellRow(cursor.getPosY())][xToCellCol(cursor.getPosX())].setColor(currentColor.getColor());

    }

    protected void createCanvas() {

        if (field != null) {
            field.delete();

        }
        field = new Rectangle(0, 0, cellSize * Math.max(totalCols, totalRows) + Config.C_TOOL_SIZE + (3 * Config.C_TOOLBAR_FRAME_THICKNESS), cellSize * Math.max(totalCols, totalRows) + Config.C_TOOL_SIZE + (3 * Config.C_TOOLBAR_FRAME_THICKNESS));

        field.setColor(new Color(63, 63, 63));
        field.fill();

        loadToolBar();
        loadColorBar();
    }


    //Undo Stack
   protected void initUndoStack() {
        undoMapStack = new Stack<>();
    }

    public void undoSaveMap(char[][] mapToSave) {

        //don't save map if it's the same as a previously saved one
        if (!undoMapStack.isEmpty()) {
            if (compareAsciiMaps(undoMapStack.peek(), mapToSave)) {
                return;
            }
        }

        undoMapStack.push(mapToSave);
    }

    protected void undo() {
        if (undoMapStack.size() == 0) {
            return;
        }

        //put the the undo map on the redo stack
        redoSaveMap(getCellsAsciiMap());

        char[][] lastMapIn = undoMapStack.pop();

        createCells(lastMapIn);
        drawCells();

    }


    //Redo Stack
   protected void initRedoStack() {
        redoMapStack = new Stack<>();
    }

    public void redoSaveMap(char[][] mapToSave) {

        //don't save map if it's the same as a previously saved one
        if (!redoMapStack.isEmpty()) {
            if (compareAsciiMaps(redoMapStack.peek(), mapToSave)) {
                return;
            }
        }

        redoMapStack.push(mapToSave);
    }

    protected void redo() {
        if (redoMapStack.size() == 0) return;

        undoSaveMap(getCellsAsciiMap());

        char[][] lastMapIn = redoMapStack.pop();
        createCells(lastMapIn);
        drawCells();

    }

   protected boolean compareAsciiMaps(char[][] map1, char[][] map2) {
        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1[0].length; j++) {
                if (map1[i][j] != map2[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }


    //cursor
    public void initCursor() {

        int posX = cellsColToX((int) Math.ceil(totalCols / 2));
        int posY = cellsRowToY((int) Math.ceil(totalRows / 2));
        int width = cellSize - Config.C_CANVAS_CELL_GAP;

        if (cursor != null) {

            cursor.deleteRepresentation();
            cursor = null;
        }
        cursor = new Cursor(posX, posY, width);

    }

    public void move(Direction direction) {

        int newCursorCol = xToCellCol(cursor.getPosX()) + direction.getDirCol();
        int newCursorRow = yToCellRow(cursor.getPosY()) + direction.getDirRow();
        int dx, dy;

        if (isOutsideBorder(newCursorCol, newCursorRow)) {

            if (newCursorCol < 0) {

                newCursorCol = totalCols - 1;

            } else if (newCursorCol >= totalCols) {

                newCursorCol = 0;

            } else if (newCursorRow < 0) {

                newCursorRow = totalRows - 1;

            } else if (newCursorRow >= totalRows) {

                newCursorRow = 0;

            }
        }

        dx = cellsColToX(newCursorCol) - cursor.getPosX();
        dy = cellsRowToY(newCursorRow) - cursor.getPosY();

        cursor.moveCursor(dx, dy);

    }

    public boolean isOutsideBorder(int col, int row) {


        return col < 0 || row < 0 || col >= totalRows || row >= totalRows;
    }


    //current paint color
    public void changeCurrentColor(ColorEnum colorEnum) {
        currentColor = colorEnum;
    }


    //toolbars
    protected void loadToolBar() {

        String[] toolFilePaths = {Config.ICON_EXIT, Config.ICON_OPEN, Config.ICON_SAVE, Config.ICON_NEW, Config.ICON_UNDO, Config.ICON_REDO};

        verticalToolbar = new Tool[toolFilePaths.length];

        for (int i = 0; i < toolFilePaths.length; i++) {
            verticalToolbar[i] = new Tool(
                    Config.C_TOOLBAR_FRAME_THICKNESS,
                    Config.C_TOOLBAR_FRAME_THICKNESS + (i == 0 ? 0 : i + 1) * (Config.C_TOOL_SIZE + Config.C_TOOLBAR_FRAME_THICKNESS),
                    toolFilePaths[i]);
        }


    }

    protected void loadColorBar() {

        int maxColorsPerRow = 31;

        int a = Math.min(maxColorsPerRow, ColorEnum.values().length);
        int b = 0;

        if (ColorEnum.values().length > maxColorsPerRow) {
            a = maxColorsPerRow;
            b = ColorEnum.values().length - maxColorsPerRow;

            horizontalColorbar2 = new ColorTool[b];
        }

        horizontalColorbar = new ColorTool[a];


        for (int i = 0; i < ColorEnum.values().length; i++) {

            if (i < maxColorsPerRow) {
                horizontalColorbar[i] = new ColorTool(2 * (Config.C_TOOL_SIZE + Config.C_TOOLBAR_FRAME_THICKNESS) + i * (Config.C_COLORTOOL_SIZE + Config.C_COLORTOOL_GAP_THICKNESS),
                        Config.C_COLORTOOL_GAP_THICKNESS,
                        ColorEnum.values()[i]);
            } else {
                horizontalColorbar2[i - maxColorsPerRow] = new ColorTool(2 * (Config.C_TOOL_SIZE + Config.C_TOOLBAR_FRAME_THICKNESS) + (i - maxColorsPerRow) * (Config.C_COLORTOOL_SIZE + Config.C_COLORTOOL_GAP_THICKNESS),
                        2 * Config.C_COLORTOOL_GAP_THICKNESS + Config.C_COLORTOOL_SIZE,
                        ColorEnum.values()[i]);
            }


        }


    }


}

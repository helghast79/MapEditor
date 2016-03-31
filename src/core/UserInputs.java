package core;

import loadsave.*;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import utilities.ColorEnum;
import utilities.Direction;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

/**
 * Created by Mariana on 25/02/16.
 */
public class UserInputs implements MouseListener, MouseMotionListener {//KeyboardHandler

    //private Keyboard k;
    private Graphics graphics;

    // private boolean spaceState = false;

    public UserInputs(Graphics graphics) {
        this.graphics = graphics;
    }

/*

    public void initKeyboard() {

        k = new Keyboard(this);

        KeyboardEvent eventUP = new KeyboardEvent();
        eventUP.setKey(KeyboardEvent.KEY_UP);
        eventUP.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventUP);

        KeyboardEvent eventLEFT = new KeyboardEvent();
        eventLEFT.setKey(KeyboardEvent.KEY_LEFT);
        eventLEFT.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventLEFT);

        KeyboardEvent eventRIGHT = new KeyboardEvent();
        eventRIGHT.setKey(KeyboardEvent.KEY_RIGHT);
        eventRIGHT.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventRIGHT);

        KeyboardEvent eventDOWN = new KeyboardEvent();
        eventDOWN.setKey(KeyboardEvent.KEY_DOWN);
        eventDOWN.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventDOWN);

        KeyboardEvent eventWHITE = new KeyboardEvent();
        eventWHITE.setKey(KeyboardEvent.KEY_0);
        eventWHITE.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventWHITE);

        KeyboardEvent eventBLACK = new KeyboardEvent();
        eventBLACK.setKey(KeyboardEvent.KEY_1);
        eventBLACK.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventBLACK);

        KeyboardEvent eventRED = new KeyboardEvent();
        eventRED.setKey(KeyboardEvent.KEY_2);
        eventRED.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventRED);

        KeyboardEvent eventBLUE = new KeyboardEvent();
        eventBLUE.setKey(KeyboardEvent.KEY_3);
        eventBLUE.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventBLUE);

        KeyboardEvent eventYELLOW = new KeyboardEvent();
        eventYELLOW.setKey(KeyboardEvent.KEY_4);
        eventYELLOW.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventYELLOW);

        KeyboardEvent eventGREEN = new KeyboardEvent();
        eventGREEN.setKey(KeyboardEvent.KEY_5);
        eventGREEN.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventGREEN);

        KeyboardEvent eventORANGE = new KeyboardEvent();
        eventORANGE.setKey(KeyboardEvent.KEY_6);
        eventORANGE.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventORANGE);

        KeyboardEvent eventMAGENTA = new KeyboardEvent();
        eventMAGENTA.setKey(KeyboardEvent.KEY_7);
        eventMAGENTA.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventMAGENTA);

        KeyboardEvent eventCYAN = new KeyboardEvent();
        eventCYAN.setKey(KeyboardEvent.KEY_8);
        eventCYAN.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventCYAN);

        KeyboardEvent eventPINK = new KeyboardEvent();
        eventPINK.setKey(KeyboardEvent.KEY_9);
        eventPINK.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventPINK);

        KeyboardEvent eventPAINT = new KeyboardEvent();
        eventPAINT.setKey(KeyboardEvent.KEY_SPACE);
        eventPAINT.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(eventPAINT);

        KeyboardEvent eventNOPAINT = new KeyboardEvent();
        eventNOPAINT.setKey(KeyboardEvent.KEY_SPACE);
        eventNOPAINT.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        k.addEventListener(eventNOPAINT);

    }
*/

    public void initMouse() {
        Canvas c = Canvas.getInstance();
        c.addMouseListener(this);
        c.addMouseMotionListener(this);
    }

    public void move(Direction direction) {
        graphics.move(direction);
    }


   /* //Keyboard Events - SimpleGraphics
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        Direction dir;

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_UP:
                dir = Direction.UP;
                if (spaceState) {
                    graphics.changeCellColor();
                }
                move(dir);
                break;
            case KeyboardEvent.KEY_LEFT:
                dir = Direction.LEFT;
                if (spaceState) {
                    graphics.changeCellColor();
                }
                move(dir);
                break;
            case KeyboardEvent.KEY_RIGHT:
                dir = Direction.RIGHT;
                if (spaceState) {
                    graphics.changeCellColor();
                }
                move(dir);
                break;
            case KeyboardEvent.KEY_DOWN:
                dir = Direction.DOWN;
                if (spaceState) {
                    graphics.changeCellColor();
                }
                move(dir);
                break;
            case KeyboardEvent.KEY_0:
                graphics.changeCurrentColor(ColorEnum.WHITE);
                break;
            case KeyboardEvent.KEY_1:
                graphics.changeCurrentColor(ColorEnum.BLACK);
                break;
            case KeyboardEvent.KEY_2:
                graphics.changeCurrentColor(ColorEnum.RED);
                break;
            case KeyboardEvent.KEY_3:
                graphics.changeCurrentColor(ColorEnum.BLUE);
                break;
            case KeyboardEvent.KEY_4:
                graphics.changeCurrentColor(ColorEnum.YELLOW);
                break;
            case KeyboardEvent.KEY_5:
                graphics.changeCurrentColor(ColorEnum.GREEN);
                break;
            case KeyboardEvent.KEY_6:
                graphics.changeCurrentColor(ColorEnum.ORANGE);
                break;
            case KeyboardEvent.KEY_7:
                graphics.changeCurrentColor(ColorEnum.MAGENTA);
                break;
            case KeyboardEvent.KEY_8:
                graphics.changeCurrentColor(ColorEnum.CYAN);
                break;
            case KeyboardEvent.KEY_9:
                graphics.changeCurrentColor(ColorEnum.PINK);
                break;
            case KeyboardEvent.KEY_SPACE:

                if (!spaceState) {
                    graphics.changeCellColor();
                    spaceState = true;
                }

                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_SPACE:
                spaceState = false;
                break;
        }

    }
*/

    //Mouse Events - java AWT
    @Override
    public void mouseClicked(MouseEvent e) {


        //graphics.updateMouseClick((e.getX() - Config.X_AXIS_CORRECTION), (e.getY() - Config.Y_AXIS_CORRECTION));
        int mouseX = e.getX() - Config.X_AXIS_CORRECTION;
        int mouseY = e.getY() - Config.Y_AXIS_CORRECTION;

        mouseClickCheck_VerticalToolbar(mouseX, mouseY);
        boolean isColorToolSelected = mouseClickCheck_HorizontalColorbar(mouseX, mouseY);


        //deselect colorTools if other color is selected
        for (int i = 0; i < graphics.horizontalColorbar.length; i++) {
            if (!graphics.horizontalColorbar[i].click(mouseX, mouseY) && isColorToolSelected) {
                graphics.horizontalColorbar[i].select(false);
            }
        }
        for (int i = 0; i < graphics.horizontalColorbar2.length; i++) {
            if (!graphics.horizontalColorbar2[i].click(mouseX, mouseY) && isColorToolSelected) {
                graphics.horizontalColorbar2[i].select(false);
            }
        }

        checkGridCells(mouseX, mouseY);


    }

    @Override
    public void mousePressed(MouseEvent e) {
        //graphics.updateMousePressed((e.getX() - Config.X_AXIS_CORRECTION), (e.getY() - Config.Y_AXIS_CORRECTION));
        graphics.mousePressedFlag = true;
        graphics.cellAsciiTemp = graphics.getCellsAsciiMap();
       /* int mouseX = e.getX() - Config.X_AXIS_CORRECTION;
        int mouseY = e.getY() - Config.Y_AXIS_CORRECTION;

        for (int i = 0; i < graphics.totalRows; i++) {

            for (int j = 0; j < graphics.totalCols; j++) {

                if (graphics.cells[i][j].click(mouseX, mouseY)) {
                    graphics.undoSaveMap(graphics.getCellsAsciiMap());
                }

            }
        }*/
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        graphics.changesDetectedFlag = false;
        graphics.mousePressedFlag = false;

        if (!graphics.compareAsciiMaps(graphics.cellAsciiTemp, graphics.getCellsAsciiMap())) {
            graphics.undoSaveMap(graphics.cellAsciiTemp);
            graphics.redoMapStack.clear();//clear redo maps upon new changes
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //graphics.updateMouseDragged((e.getX() - Config.X_AXIS_CORRECTION), (e.getY() - Config.Y_AXIS_CORRECTION));
        int mouseX = e.getX() - Config.X_AXIS_CORRECTION;
        int mouseY = e.getY() - Config.Y_AXIS_CORRECTION;

        checkGridCells(mouseX, mouseY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        int mouseX = e.getX() - Config.X_AXIS_CORRECTION;
        int mouseY = e.getY() - Config.Y_AXIS_CORRECTION;

        for (int i = 0; i < graphics.verticalToolbar.length; i++) {
            graphics.verticalToolbar[i].hoover(mouseX, mouseY);
        }
        for (int i = 0; i < graphics.horizontalColorbar.length; i++) {
            graphics.horizontalColorbar[i].hoover(mouseX, mouseY);
        }
        for (int i = 0; i < graphics.horizontalColorbar2.length; i++) {
            graphics.horizontalColorbar2[i].hoover(mouseX, mouseY);
        }


    }


    private void mouseClickCheck_VerticalToolbar(int mouseX, int mouseY) {

        //check vertical toolbar
        for (int i = 0; i < graphics.verticalToolbar.length; i++) {
            if (graphics.verticalToolbar[i].click(mouseX, mouseY)) {

                switch (i) {

                    case 0: //exit application
                        System.exit(0);
                        break;

                    case 1: //File open
                        File fo = FileOpen.chooseFileToOpen();



                        if (fo != null) {
                            ReadFile rf = new ReadFile(fo.getPath());
                            graphics.createCells(rf.load());
                            graphics.createCanvas();
                            graphics.drawCells();
                            graphics.initCursor();
                            graphics.initUndoStack();
                            graphics.initRedoStack();
                        }
                        break;

                    case 2: // File save
                        File fs = FileSave.chooseFileToSave();
                        if (fs != null) {
                            WriteFile wf = new WriteFile(fs.getPath());
                            wf.save(graphics.getCellsAsciiMap());
                        }
                        break;

                    case 3: // File new

                        try {
                            int[] colsAndRows = InputDialog.showInputDialog(graphics.totalRows, graphics.totalCols);
                            graphics.createCells(colsAndRows[0], colsAndRows[1]);//the default map
                            graphics.createCanvas();
                            graphics.drawCells();
                            graphics.initCursor();
                            graphics.initUndoStack();
                            graphics.initRedoStack();

                        } catch (java.lang.NumberFormatException nfe) {
                            System.out.println("error " + nfe.getMessage());
                        }
                        break;

                    case 4:
                        graphics.undo();
                        break;

                    case 5:
                        graphics.redo();
                        break;
                }

            } else {
                graphics.verticalToolbar[i].select(false);
            }
        }
    }

    //returns true if color tool has been selected
    private boolean mouseClickCheck_HorizontalColorbar(int mouseX, int mouseY) {


        //check horizontal toolbar
        boolean colorToolSelected = false;
        for (int i = 0; i < graphics.horizontalColorbar.length; i++) {
            if (graphics.horizontalColorbar[i].click(mouseX, mouseY)) {
                colorToolSelected = true;
                graphics.horizontalColorbar[i].select(true);
                graphics.currentColor = graphics.horizontalColorbar[i].getRectangleColor();
                break;
            } else {
                graphics.horizontalColorbar[i].select(false);
            }
        }
        //continue check horizontal toolbar2
        for (int i = 0; i < graphics.horizontalColorbar2.length; i++) {
            if (graphics.horizontalColorbar2[i].click(mouseX, mouseY)) {
                colorToolSelected = true;
                graphics.horizontalColorbar2[i].select(true);
                graphics.currentColor = graphics.horizontalColorbar2[i].getRectangleColor();
            }
        }

        return colorToolSelected;
    }

    private void checkGridCells(int mouseX, int mouseY) {
        for (int i = 0; i < graphics.totalRows; i++) {

            for (int j = 0; j < graphics.totalCols; j++) {

                if (graphics.cells[i][j].click(mouseX, mouseY)) {

                    if (!graphics.mousePressedFlag) {

                        graphics.undoSaveMap(graphics.getCellsAsciiMap());
                        graphics.redoMapStack.clear();//clear redo maps upon new changes
                    }

                    int newCursorCol = graphics.xToCellCol(mouseX);
                    int newCursorRow = graphics.yToCellRow(mouseY);
                    int dx, dy;

                    dx = graphics.cellsColToX(newCursorCol) - (graphics.cursor.getPosX());
                    dy = graphics.cellsRowToY(newCursorRow) - (graphics.cursor.getPosY());

                    graphics.cursor.moveCursor(dx, dy);

                    graphics.changeCellColor();


                }

            }
        }
    }
}

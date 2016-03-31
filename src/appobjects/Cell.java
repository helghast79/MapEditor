package appobjects;

import core.Config;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import utilities.Clickable;
import utilities.ColorEnum;

/**
 * Created by macha on 27/02/2016.
 */
public class Cell implements Clickable {

    private char asciiVal;
    private int positionX;
    private int positionY;
    private int cellSize;


    private Rectangle rectangle;
    private Rectangle border;
    private Color color;



    public void initCell(char asciiVal, int positionX, int positionY, int cellSize) {

        this.asciiVal = asciiVal;
        this.color = ColorEnum.BLACK.getColorFromAscii(asciiVal);
        this.positionX = positionX;
        this.positionY = positionY;
        this.cellSize = cellSize;

        border = new Rectangle(positionX, positionY, cellSize, cellSize);
        border.setColor(Config.C_CANVAS_GRID_COLOR.getColor());

        rectangle = new Rectangle(positionX + Config.C_CANVAS_CELL_GAP, positionY + Config.C_CANVAS_CELL_GAP, cellSize - Config.C_CANVAS_CELL_GAP, cellSize - Config.C_CANVAS_CELL_GAP);
        rectangle.setColor(color);

    }

    public void drawRepreentation() {
        border.draw();
        rectangle.fill();
    }


    public char getAsciiVal() {
        return asciiVal;
    }

    public Color getColor() {
        return color;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getCellSize() {
        return cellSize;
    }




    public void setColor(Color color) {
        this.color = color;
        this.asciiVal = ColorEnum.BLACK.getAsciiFromColor(color);
        this.rectangle.setColor(color);
        this.rectangle.fill();
    }

    public void setAsciiVal(char asciiVal) {
        this.color = ColorEnum.BLACK.getColorFromAscii(asciiVal);
        this.asciiVal = asciiVal;
        this.rectangle.setColor(color);
        this.rectangle.fill();
    }


    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public void deleteRepresentation() {
        this.rectangle.delete();
        this.border.delete();

    }

    @Override
    public boolean click(int mousePosX, int mousePosY) {
        if (mousePosX >= positionX &&
                mousePosX <= positionX + cellSize &&
                mousePosY >= positionY &&
                mousePosY <= positionY + cellSize) {
            return true;
        }
        return false;
    }

}

package appobjects;

import core.Config;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Line;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 01/03/16.
 */
public class Cursor {


    private Rectangle rectangle;
    private Line lineUp, lineDown, lineLeft, lineRight;
    private int positionX;
    private int positionY;



    public Cursor(int positionX, int positionY, int cellSize) {

        this.positionX = positionX;
        this.positionY = positionY;

        initRepresentation(Config.C_CURSOR_COLOR.getColor(), positionX, positionY, cellSize);

    }

    public void deleteRepresentation() {

        lineUp.delete();
        lineDown.delete();
        lineLeft.delete();
        lineRight.delete();
    }

    public void moveCursor(int dx, int dy) {
        //rectangle.translate(dx,dy);
        lineUp.translate(dx, dy);
        lineDown.translate(dx, dy);
        lineLeft.translate(dx, dy);
        lineRight.translate(dx, dy);


        positionX += dx;
        positionY += dy;
    }

    public int getPosX() {
        return positionX;
    }

    public int getPosY() {
        return positionY;
    }

    public void initRepresentation(Color color, int positionX, int positionY, int cellSize) {


        /*rectangle = new Rectangle(positionX, positionY, cellSize, cellSize);
        rectangle.setColor(color);
        rectangle.fill();
       */

        lineUp = new Line(positionX + 1, positionY + 1, positionX + cellSize - 2, positionY + 1);
        lineDown = new Line(positionX + 1, positionY + cellSize - 2, positionX + cellSize - 2, positionY + cellSize - 2);
        lineLeft = new Line(positionX + 1, positionY + 1, positionX + 1, positionY + cellSize - 2);
        lineRight = new Line(positionX - 2 + cellSize, positionY + 1, positionX + cellSize - 2, positionY + cellSize - 2);


        lineUp.setColor(color);
        lineDown.setColor(color);
        lineLeft.setColor(color);
        lineRight.setColor(color);

        lineUp.draw();
        lineDown.draw();
        lineLeft.draw();
        lineRight.draw();

    }

}

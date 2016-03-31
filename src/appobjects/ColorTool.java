package appobjects;

import core.Config;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import utilities.Clickable;
import utilities.ColorEnum;
import utilities.Hooverable;

/**
 * Created by macha on 27/02/2016.
 */
public class ColorTool implements Clickable, Hooverable {

    private Rectangle innerSquare;
    private Rectangle outerSquare;
    private Rectangle fillSquare;
    private int posX;
    private int posY;

    private int size;
    private boolean hasFocus = false;
    private boolean isSelected = false;

    private ColorEnum rectangleColor;


    //create a filled square Toolbar
    public ColorTool(int positionX, int positionY, ColorEnum colorEnum) {

        posX = positionX;
        posY = positionY;

        fillSquare = new Rectangle(posX, posY, Config.C_COLORTOOL_SIZE, Config.C_COLORTOOL_SIZE);
        fillSquare.setColor(colorEnum.getColor());
        fillSquare.fill();

        size = Config.C_COLORTOOL_SIZE;

        rectangleColor = colorEnum;
    }


    public void select(boolean selectNow) {



        if (outerSquare == null) {
            outerSquare = new Rectangle(posX - Config.C_TOOLBAR_SELECTED_FRAME_THICKNESS,
                    posY - Config.C_TOOLBAR_SELECTED_FRAME_THICKNESS,
                    size + (2 * Config.C_TOOLBAR_SELECTED_FRAME_THICKNESS),
                    size + (2 * Config.C_TOOLBAR_SELECTED_FRAME_THICKNESS));
        }

        if (isSelected && !selectNow) {
            outerSquare.delete();
            isSelected = false;
            return;
        }

        if (!isSelected && selectNow) {
            outerSquare.setColor(Color.RED);
            outerSquare.draw();
            //setFocus(false);
            isSelected = true;
        }
    }

    public ColorEnum getRectangleColor() {
        return rectangleColor;
    }

    @Override
    public boolean click(int mousePosX, int mousePosY) {
        if (mousePosX >= posX && mousePosX <= posX + size && mousePosY >= posY && mousePosY <= posY + size) {
            return true;
        }
        return false;
    }


    @Override
    public void hoover(int mousePosX, int mousePosY) {


        if (innerSquare == null) {
            innerSquare = new Rectangle(posX, posY, size, size);
        }


        if (mousePosX >= posX && mousePosX <= posX + size && mousePosY >= posY && mousePosY <= posY + size) {
            if (!hasFocus) {
                innerSquare.setColor(Color.WHITE);
                innerSquare.draw();
                hasFocus = true;
            }
        } else {

            if (hasFocus) {
                innerSquare.delete();
                hasFocus = false;

            }
        }

    }

}

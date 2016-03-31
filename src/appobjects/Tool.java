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
public class Tool implements Clickable, Hooverable {

    private Picture pic;
    private Picture picHoover;
    private Picture picOn;
    private Rectangle innerSquare;
    private Rectangle outerSquare;
    private Rectangle fillSquare;
    private int posX;
    private int posY;

    private int size;
    private boolean hasFocus = false;
    private boolean isSelected = false;

    private ColorEnum rectangleColor;
    private String picFilePath;

    //create a picture Toolbar
    public Tool(int positionX, int positionY, String picFilePath) {

        posX = positionX;
        posY = positionY;
        this.picFilePath = picFilePath;

        pic = new Picture(posX, posY, picFilePath + ".png");
        pic.draw();
        size = pic.getWidth();

        picHoover = new Picture(posX, posY, picFilePath + "_ho.png");
        picOn = new Picture(posX, posY, picFilePath + "_on.png");
    }

    //create a filled square Toolbar
    public Tool(int positionX, int positionY, ColorEnum colorEnum) {

        posX = positionX;
        posY = positionY;

        fillSquare = new Rectangle(posX, posY, Config.C_COLORTOOL_SIZE, Config.C_COLORTOOL_SIZE);
        fillSquare.setColor(colorEnum.getColor());
        fillSquare.fill();

        size = Config.C_COLORTOOL_SIZE;

        rectangleColor = colorEnum;
    }


    public void select(boolean selectNow) {

        /*if (pic == null) {
            pic = new Picture(posX, posY, picFilePath + ".png");
        }
        if (picHoover == null) {
            picHoover = new Picture(posX, posY, picFilePath + "_ho.png");
        }
        if (picOn == null) {
            picOn = new Picture(posX, posY, picFilePath + "_on.png");
        }*/


        if (isSelected && !selectNow) {
            if (picOn != null) {
                picOn.delete();
            }
            if (pic == null) {
                pic = new Picture(posX, posY, picFilePath + ".png");
            }
            pic.draw();
            isSelected = false;
            return;
        }

        if (!isSelected && selectNow) {
            if (pic != null) {
                pic.delete();
            }
            if (picOn == null) {
                picOn = new Picture(posX, posY, picFilePath + "_on.png");
            }
            picOn.draw();

            isSelected = true;
        }


        /*if (outerSquare == null) {
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
        }*/
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

        if (mousePosX >= posX && mousePosX <= posX + size && mousePosY >= posY && mousePosY <= posY + size) {
            if (!hasFocus) {
                if (pic != null) {
                    pic.delete();
                }
                if (picHoover == null) {
                    picHoover = new Picture(posX, posY, picFilePath + "_ho.png");
                }
                picHoover.draw();
                hasFocus = true;
            }
        } else {

            if (hasFocus) {
                if (picHoover != null) {
                    picHoover.delete();
                }
                if (pic == null) {
                    pic = new Picture(posX, posY, picFilePath + ".png");
                }
                pic.draw();
                hasFocus = false;

            }
        }
       /* if (innerSquare == null) {
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
        }*/

    }

}

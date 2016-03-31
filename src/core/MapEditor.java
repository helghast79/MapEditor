package core;

/**
 * Created by codecadet on 25/02/16.
 */
public class MapEditor {



    private Graphics gpx;
    private UserInputs userInputs;

    public static void main(String[] args) {

        MapEditor mapEditor = new MapEditor();


    }


    public MapEditor() {

        Config.init();

        gpx = new Graphics();

        gpx.createCells(0, 0);//the default map
        gpx.createCanvas();
        gpx.drawCells();

        userInputs = new UserInputs(gpx);

        gpx.initCursor();

        //userInputs.initKeyboard();
        userInputs.initMouse();

    }




}

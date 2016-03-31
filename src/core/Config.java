package core;

import utilities.ColorEnum;

/**
 * Created by codecadet on 25/02/16.
 */
public final class Config {



    //FILE ACCESS
    public static final String FILE_RESOURCES_PATH = "/";


    //CORRECTIONS
    public static  int X_AXIS_CORRECTION = 2;
    public static  int Y_AXIS_CORRECTION = 26;

    //TOOLBARS



    //PICTURES PATH
    public static final String ICON_EXIT = FILE_RESOURCES_PATH + "btn_power";//extension (png) not needed on tool icons
    public static final String ICON_OPEN = FILE_RESOURCES_PATH + "btn_open";
    public static final String ICON_SAVE = FILE_RESOURCES_PATH + "btn_save";
    public static final String ICON_NEW = FILE_RESOURCES_PATH + "btn_new";
    public static final String ICON_UNDO = FILE_RESOURCES_PATH + "btn_undo";
    public static final String ICON_REDO = FILE_RESOURCES_PATH + "btn_redo";
    public static final String PIC_BACKGROUND = FILE_RESOURCES_PATH + "background.jpg";


    //CANVAS
    public static final int C_TOOL_SIZE = 30;
    public static final int C_TOOLBAR_FRAME_THICKNESS = 5;
    public static final int C_TOOLBAR_SELECTED_FRAME_THICKNESS = 1;

    public static final int C_COLORTOOL_SIZE = 14;//color tool size * 2 + gap = tool size
    public static final int C_COLORTOOL_GAP_THICKNESS = C_TOOL_SIZE-(2*C_COLORTOOL_SIZE)+1; //standard = 30-13-13 = 4


    //preferences - possibly adjusted
    public static int C_PREF_TOTAL_COLS = 30;
    public static int C_PREF_TOTAL_ROWS = 30;
    public static int C_CANVAS_PREF_WIDTH = 700;
    public static int C_CANVAS_PREF_HEIGHT = 700;

    public static final ColorEnum C_CANVAS_BACKGROUND_COLOR = ColorEnum.WHITE;
    public static final ColorEnum C_CANVAS_GRID_COLOR = ColorEnum.GRAY;
    public static final int C_CANVAS_CELL_GAP = 1;
    public static final ColorEnum C_CURSOR_COLOR = ColorEnum.BLACK;

    //REPRESENTATION
    public static final int BORDER_X = C_TOOLBAR_FRAME_THICKNESS + C_TOOL_SIZE + C_TOOLBAR_FRAME_THICKNESS; //10+30+10 = 50
    public static final int BORDER_Y = C_TOOLBAR_FRAME_THICKNESS + C_TOOL_SIZE + C_TOOLBAR_FRAME_THICKNESS; //10+30+10 = 50



    public static void init(){



        //Set X & Y Axis according to the system OS
        if(System.getProperty("os.name").equals("Windows 10")){ //windows 10
            X_AXIS_CORRECTION = 8;
            Y_AXIS_CORRECTION = 31;

        }else if(System.getProperty("os.name").equals("Mac Os X")){ //Mac OS X
            X_AXIS_CORRECTION = 2;
            Y_AXIS_CORRECTION = 26;
        }
        else {
            //Missing other systems configuration
        }



    }



}

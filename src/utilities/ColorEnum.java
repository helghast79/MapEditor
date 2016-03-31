package utilities;

import org.academiadecodigo.simplegraphics.graphics.Color;

/**
 * Created by codecadet on 25/02/16.
 */
public enum ColorEnum {
    WHITE(Color.WHITE, '0'),
    BLACK(Color.BLACK,'1'),
    RED(Color.RED,'2'),
    BLUE(Color.BLUE,'3'),
    YELLOW(Color.YELLOW,'4'),
    GREEN(Color.GREEN,'5'),
    ORANGE(Color.ORANGE,'6'),
    MAGENTA(Color.MAGENTA,'7'),
    CYAN(Color.CYAN,'8'),
    PINK(Color.PINK,'9'),

    //reds
    DARKRED(new Color(139,0,0),'a'),
    CRIMSON(new Color(220,20,60),'b'),
    LIGHTCORAL(new Color(240,128,128),'c'),

    //greens
    DARKGREEN(new Color(0,100,0),'d'),
    OLIVE(new Color(128,128,0),'e'),
    MEDIUMSEAGREEN(new Color(60,179,113),'f'),
    LIME(new Color(10,245,10),'g'),
    LIGHTGREEN(new Color(144,238,144),'h'),

    //blues
    DARKBLUE(new Color(0,0,139),'i'),
    DODGERBLUE(new Color(30,144,255),'j'),
    TURQUOISE(new Color(64,224,208),'k'),
    LIGHTBLUE(new Color(173,216,230),'l'),

    //yellows & orange
    GOLD(new Color(255,220,0),'n'),
    KHAKI(new Color(240,230,140),'m'),
    LIGHTYELLOW(new Color(255,255,224),'o'),

    //Pink
    MEDIUMVIOLETRED(new Color(199,21,133),'p'),
    HOTPINK(new Color(255,105,180),'q'),

    //Purple/violet/magenta
    SLATEBLUE(new Color(106,90,205),'r'),
    PLUM(new Color(221,160,221),'s'),

    //browns
    MARRON(new Color(128,0,0),'t'),
    SADDLEBROWN(new Color(139,69,19),'u'),
    SANDYBROWN(new Color(244,164,96),'v'),
    NAVAJOWHITE(new Color(255,222,173),'w'),

    //grays
    GRAY(new Color(128,128,128),'x'),
    SILVER(new Color(192,192,192),'y'),
    LIGHTGRAY(new Color(215,215,215),'z');

    //

    //private int colorNumber;
    private Color color;
    private char colorChar;

    ColorEnum(Color color, char colorChar) {
        //this.colorNumber = colorNumber;
        this.color = color;
        this.colorChar = colorChar;
    }

    public Color getColor(int colorIndex){
        return ColorEnum.values()[colorIndex].color;
    }
    public char getColorChar() {
        return colorChar;
    }

    public Color getColorFromAscii(char ascii) {
        for (int i = 0; i < ColorEnum.values().length; i++) {
            if( ColorEnum.values()[i].colorChar == ascii){
                return ColorEnum.values()[i].color;
            }
        }
        return null;
    }
    public char getAsciiFromColor(Color color) {
        for (int i = 0; i < ColorEnum.values().length; i++) {
            if( ColorEnum.values()[i].color.equals(color)){
                return ColorEnum.values()[i].colorChar;
            }
        }
        return '0';//white
    }

    public Color getColor(){
        return this.color;
    }


}

package loadsave;

import javax.swing.*;
import java.io.File;

/**
 * Created by macha on 01/03/2016.
 */
public class FileSave {


    public static File chooseFileToSave(){

        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();

        }
        return null;
    }

}

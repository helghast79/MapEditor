package loadsave;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by JSouza on 24/02/16 Academia de Codigo.
 */
public class WriteFile {
    File fileToSave;
    FileWriter fileWriter;


    public WriteFile(String file) {
        fileToSave = new File(file);
    }

    /**
     * Saves the state of each cell in a file
     *
     */
    public void save(char[][] mapToSave) {

        if (fileToSave.exists()) {
            fileToSave.delete();
        }

        try {
            fileWriter = new FileWriter(fileToSave, true);

            for (int i = 0; i < mapToSave.length ; i++) {

                String s = "";
                for (int j = 0; j < mapToSave[i].length ; j++) {

                    s = s + mapToSave[i][j] ;
                }

                //separate each row
                //note: "\n" is not reliable between system - use System.lineSeparator() or System.getProperty("line.separator") or String.format("%n");
                s = s + System.lineSeparator();
                fileWriter.write(s);

            }

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

package loadsave;

import java.io.*;

/**
 * Created by JSouza on 25/02/16 Academia de Codigo.
 */
public class ReadFile {
    File fl;
    int numberCol = 0;
    int numberRow = 0;
    char[][] asciiMap;


    public ReadFile(String file) {
        fl = new File(file);
    }


    public char[][] load() {
        int row = 0;

        if (!fl.exists()) {
            System.out.println("File not exist!");
            return null;
        }

        try {
            String s = "";

            initArray();

            FileReader fw2 = new FileReader(fl);
            BufferedReader bw2 = new BufferedReader(fw2);

            while ((s = bw2.readLine()) != null) {

                loadFile(s, row);
                row++;
            }
            bw2.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not exist! 2");

        } catch (IOException e) {

            e.printStackTrace();
        }

        return asciiMap;
    }


    private void loadFile(String s, int row) {

        try {

            for (int j = 0; j < numberCol; j++) {

                asciiMap[row][j] = s.toCharArray()[j];
            }

        } catch (NumberFormatException e) {
            System.out.println("File with invalid characters.");

        }


    }

    private void initArray() {


        try {
            String s;
            FileReader fw = new FileReader(fl);
            BufferedReader bw = new BufferedReader(fw);
            while (( s = bw.readLine()) != null) {

                if(numberRow==0){
                    numberCol = s.toCharArray().length;
                }
                numberRow++;
            }
            bw.close();

            asciiMap = new char[numberRow][numberCol];

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

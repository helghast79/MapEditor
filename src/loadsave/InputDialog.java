package loadsave;

import javax.swing.*;

/**
 * Created by codecadet on 02/03/16.
 */
public class InputDialog {


    public static int[] showInputDialog(int value1, int value2){
        JTextField firstInputBox = new JTextField();
        JTextField secondInputBox = new JTextField();

        firstInputBox.setText(Integer.toString(value1));
        secondInputBox.setText(Integer.toString(value2));

        Object[] msg = {"Number of rows:", firstInputBox, "Number of columns:", secondInputBox};

        JOptionPane op = new JOptionPane(
                msg,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialog = op.createDialog(null, "Create new Map");
        dialog.setVisible(true);

        int result = JOptionPane.OK_OPTION;
        int[] returnVal = new int[2];

        try
        {
            result = ((Integer)op.getValue()).intValue();
        }
        catch(Exception uninitializedValue)
        {}

        if(result == JOptionPane.OK_OPTION)
        {
            returnVal[0]=Integer.parseInt(firstInputBox.getText());
            returnVal[1]=Integer.parseInt(secondInputBox.getText());

        }


        return returnVal;


    }




}

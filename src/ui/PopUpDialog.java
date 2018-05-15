package ui;

import javax.swing.*;

public class PopUpDialog {

    public void winnerPopUpDialog(String winner){
        //umesto null treba ubaciti roditelja na koji ce iskociti ovaj prozor
        JOptionPane.showMessageDialog(null, "The winner is " + winner + "!");
    }
}

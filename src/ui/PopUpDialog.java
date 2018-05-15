package ui;

import javax.swing.*;
import java.awt.*;

public class PopUpDialog {

    private JPanel panel = new JPanel(new BorderLayout());

    private String popUpHeader = "End of game";
    private String[] options = {"Back to lobby" , "Exit"};

    public void winnerPopUpDialog(String winner) {

        JLabel text = new JLabel("The winner is " + winner + "!");
        panel.add(text, BorderLayout.CENTER);

        int selectedOption = JOptionPane.showOptionDialog(frame.getContentPane(), panel, popUpHeader, JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null );

        if(selectedOption == 0) { //selected "Back to lobby" button

        }
        else if(selectedOption == 1){ // selected "Exit" button
            System.exit(0);
        }
    }
}

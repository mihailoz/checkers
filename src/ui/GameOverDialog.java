package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverDialog extends JDialog implements ActionListener {

    JButton button;
    private DialogListener listener;

    public GameOverDialog(DialogListener listener, boolean victory, boolean isHost) {
        this.listener = listener;
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        setLayout(new BorderLayout());

        String message;

        if(victory) {
            this.setTitle("You have WON!");
            if(isHost) {
                message = "The Empire has fallen. Good game rebel.";
            } else {
                message = "You've gotten rid of the Rebel scum. Long live the Emperor!";
            }
        } else {
            this.setTitle("You have been defeated!");
            if(isHost) {
                message = "Rebellions are built on hope, learn from this defeat";
            } else {
                message = "You have failed me for the last time commander... (proceeds to force choke you)";
            }
        }


        JLabel label = new JLabel(message);
        label.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(label, BorderLayout.CENTER);

        button = new JButton("Back to lobby");
        button.addActionListener(this);
        add(button, BorderLayout.SOUTH);
        pack();
    }

    public void actionPerformed(ActionEvent e) {
        if(this.listener != null)
            listener.dialogClosed();

        dispose();
    }
}
package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitDialog extends JDialog implements ActionListener {

    JButton button;
    private DialogListener listener;

    public WaitDialog(DialogListener listener) {
        this.listener = listener;
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel label = new JLabel("Waiting for opponent to connect...");
        label.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(label, BorderLayout.CENTER);

        button = new JButton("Cancel");
        button.addActionListener(this);
        add(button, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(this.listener != null)
            listener.dialogClosed();

        dispose();
    }
}

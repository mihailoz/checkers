package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by mihailozdravkovic on 5/15/18.
 */
public class HostLobbyPanel extends JPanel {

    private JTextField portField, nickField;
    private JButton confirmBtn;

    public HostLobbyPanel() {
        nickField = new JTextField("MyNickname");
        nickField.setMinimumSize(new Dimension(400, 60));
        nickField.setMaximumSize(new Dimension(400, 60));
        portField = new JTextField("8000");
        portField.setMinimumSize(new Dimension(400, 60));
        portField.setMaximumSize(new Dimension(400, 60));

        confirmBtn = new JButton();
        confirmBtn.setAlignmentX(CENTER_ALIGNMENT);
        confirmBtn.setText("Host game");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(40, 20, 20, 20));

        JLabel nickLabel = new JLabel("Nickname");
        nickLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(nickLabel);
        add(nickField);
        add(Box.createVerticalGlue());

        JLabel portLabel = new JLabel("Port");
        portLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(portLabel);
        add(portField);
        add(Box.createVerticalGlue());

        add(confirmBtn);
    }

    public String getNickname() {
        return nickField.getText();
    }

    public String getPort() {
        return portField.getText();
    }

    public void addButtonListener(ActionListener listener) {
        if(listener != null)
            this.confirmBtn.addActionListener(listener);
    }
}

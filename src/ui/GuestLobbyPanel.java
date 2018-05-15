package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by mihailozdravkovic on 5/15/18.
 */
public class GuestLobbyPanel extends JPanel {

    private JTextField nickField, hostField, portField;
    private JButton confirmBtn;

    public GuestLobbyPanel() {
        hostField = new JTextField("localhost");
        hostField.setMinimumSize(new Dimension(400, 40));
        hostField.setMaximumSize(new Dimension(400, 40));
        nickField = new JTextField("MyNickname");
        nickField.setMinimumSize(new Dimension(400, 40));
        nickField.setMaximumSize(new Dimension(400, 40));
        portField = new JTextField("8000");
        portField.setMinimumSize(new Dimension(400, 40));
        portField.setMaximumSize(new Dimension(400, 40));

        confirmBtn = new JButton();
        confirmBtn.setAlignmentX(CENTER_ALIGNMENT);
        confirmBtn.setText("Join game");

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));


        JLabel nickLabel = new JLabel("Nickname");
        nickLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(nickLabel);
        add(nickField);
        add(Box.createVerticalGlue());

        JLabel hostLabel = new JLabel("Hostname");
        hostLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(hostLabel);
        add(hostField);
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

    public String getHost() {
        return hostField.getText();
    }

    public String getPort() {
        return portField.getText();
    }

    public void addButtonListener(ActionListener listener) {
        if(listener != null)
            this.confirmBtn.addActionListener(listener);
    }
}

package ui;

import commons.GameType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LobbyPanel extends JPanel implements ActionListener {

    private JComboBox typeComboBox;
    private JTextField hostField, nickField, portField;
    private JButton confirmBtn;

    private LobbyListener listener;

    public LobbyPanel(LobbyListener listener) {
        this.listener = listener;

        String[] options = new String[] {"Host game", "Join game"};
        typeComboBox = new JComboBox(options);

        hostField = new JTextField("localhost");
        nickField = new JTextField("MyNickname");
        portField = new JTextField("8000");

        confirmBtn = new JButton();
        this.addButtonListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(typeComboBox);

        this.showHostGameOptions();

        typeComboBox.addActionListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(((String) typeComboBox.getSelectedItem()).equals("Join game")) {
            showJoinGameOptions();
        } else {
            showHostGameOptions();
        }
    }

    private void showJoinGameOptions() {
        this.removeFields();

        this.add(nickField);
        this.add(hostField);
        this.add(portField);

        confirmBtn.setText("Join");

        this.add(confirmBtn);

    }

    private void showHostGameOptions() {
        this.removeFields();

        this.add(nickField);
        this.add(portField);

        confirmBtn.setText("Host");

        this.add(confirmBtn);
    }

    private void removeFields() {
        this.remove(hostField);
        this.remove(nickField);
        this.remove(portField);
    }

    private void addButtonListener() {
        confirmBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                GameType type = ((String) typeComboBox.getSelectedItem()).equals("Join game") ? GameType.JOIN : GameType.HOST;

                String host = hostField.getText();
                String nick = nickField.getText();
                int port;

                try {
                    port = Integer.parseInt(portField.getText());
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(LobbyPanel.this, "Port must be a number lesser than 65536!", "Warning", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(type.equals(GameType.JOIN) && host.isEmpty()) {
                    JOptionPane.showMessageDialog(LobbyPanel.this, "Host field cannot be empty!", "Warning", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(nick.isEmpty()) {
                    JOptionPane.showMessageDialog(LobbyPanel.this, "Nickname cannot be empty!", "Warning", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                listener.actionPerformed(type, nick, host, port);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    public interface LobbyListener {
        void actionPerformed(GameType type, String nick, String host, int port);
    }
}

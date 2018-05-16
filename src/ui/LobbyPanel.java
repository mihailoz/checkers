package ui;

import commons.GameType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LobbyPanel extends JPanel implements ItemListener {

    private final String HOST_PANEL = "Host game";
    private final String GUEST_PANEL = "Join game";

    private JComboBox typeComboBox;
    private JTextField hostField, nickField, portField;
    private JButton confirmBtn;

    private HostLobbyPanel hostPanel;
    private GuestLobbyPanel guestPanel;
    private JPanel cards;

    private LobbyListener listener;

    public LobbyPanel(LobbyListener listener) {
        this.listener = listener;
        this.setLayout(new BorderLayout());

        cards = new JPanel(new CardLayout());

        guestPanel = new GuestLobbyPanel();
        hostPanel = new HostLobbyPanel();

        addButtonListeners();

        cards.add(hostPanel, HOST_PANEL);
        cards.add(guestPanel, GUEST_PANEL);

        String[] options = new String[] { HOST_PANEL, GUEST_PANEL };
        JPanel comboBoxPane = new JPanel();
        typeComboBox = new JComboBox(options);
        typeComboBox.setEditable(false);
        typeComboBox.addItemListener(this);
        comboBoxPane.add(typeComboBox);

        this.add(comboBoxPane, BorderLayout.PAGE_START);
        this.add(cards, BorderLayout.CENTER);
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 240);
    }

    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    private void addButtonListeners() {
        guestPanel.addButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nick = guestPanel.getNickname();
                String host = guestPanel.getHost();
                try {
                    int port = Integer.parseInt(guestPanel.getPort());

                    if(host.isEmpty()) {
                        JOptionPane.showMessageDialog(LobbyPanel.this, "Hostname cannot be empty!", "Warning", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if(nick.isEmpty()) {
                        JOptionPane.showMessageDialog(LobbyPanel.this, "Nickname cannot be empty!", "Warning", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    LobbyPanel.this.listener.actionPerformed(GameType.JOIN, nick, host, port);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LobbyPanel.this, "Port has to be a number!", "Warning", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        hostPanel.addButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nick = hostPanel.getNickname();
                try {
                    int port = Integer.parseInt(hostPanel.getPort());

                    if(nick.isEmpty()) {
                        JOptionPane.showMessageDialog(LobbyPanel.this, "Nickname cannot be empty!", "Warning", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    LobbyPanel.this.listener.actionPerformed(GameType.HOST, nick, null, port);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LobbyPanel.this, "Port has to be a number!", "Warning", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void setEnabledFields(boolean b) {
        this.typeComboBox.setEnabled(b);
        this.hostPanel.setEnabledFields(b);
    }

    public interface LobbyListener {
        void actionPerformed(GameType type, String nick, String host, int port);
    }
}

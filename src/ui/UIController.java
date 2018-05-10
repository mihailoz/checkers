package ui;

import commons.GameType;
import socket.ConnectionManager;

import javax.swing.*;
import java.io.IOException;

public class UIController implements LobbyPanel.LobbyListener {

    private JFrame frame;

    private LobbyPanel lobbyPanel;

    private ConnectionManager connectionManager;

    public void launchGame() {
        frame = new JFrame("Space checkers in space");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lobbyPanel = new LobbyPanel(this);

        frame.add(lobbyPanel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(GameType type, String nick, String host, int port) {
        try {
            switch (type) {
                case JOIN:
                    connectionManager = new ConnectionManager(host, port);
                    break;
                case HOST:
                    connectionManager = new ConnectionManager(port);
                    break;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

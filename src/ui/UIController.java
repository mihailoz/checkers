package ui;

import commons.GameType;
import socket.ConnectionManager;

import javax.swing.*;

public class UIController implements LobbyPanel.LobbyListener, GameActionListener {

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
        switch (type) {
            case JOIN:
                connectionManager = new ConnectionManager(host, port, nick);
                break;
            case HOST:
                connectionManager = new ConnectionManager(port, nick);
                break;
        }

        connectionManager.addListener(this);
        connectionManager.connect();
    }

    @Override
    public void opponentNicknameRecieved(String nickname) {
        System.out.println("YEEEY our oppo is called: " + nickname);
    }

    @Override
    public void hostSocketOpened() {

    }

    @Override
    public void clientConnected() {

    }

    @Override
    public void validateMove() {

    }
}

package ui;

import commons.Board;
import commons.GameData;
import commons.GameType;
import socket.ConnectionManager;

import javax.swing.*;
import java.util.ArrayList;

public class UIController implements LobbyPanel.LobbyListener, GameActionListener {

    private JFrame frame;

    private LobbyPanel lobbyPanel;
    private GamePanel gamePanel;

    private WaitDialog waitDialog;

    private ConnectionManager connectionManager;

    private GameData gameData;

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
        gameData = new GameData(connectionManager.getNickname(), nickname, connectionManager.getGameType().equals(GameType.HOST));

        if(gamePanel != null) {
            gamePanel.setOpponentNick(nickname);
            gamePanel.setGameData(gameData);
            gamePanel.setConnectionManager(connectionManager);
        }

        System.out.println("Our opponent is called: " + nickname);
    }

    @Override
    public void gameStarted() {
        // OVDE TREBA PRIKAZATI TABLU, trebace vam da li je korisnik crni ili beli
        // to sam napravio da ako je host onda je beli, ako je gost onda je crn
        // mozete proveriti sta je preko funkcije connectionManager.getGameType()
        // dobicete ili GameType.JOIN ili GameType.HOST
        // moj predlog je da prvo samo prikazete tablu sa figurama, posle cemo lako to povezati da se pomera
        // po nahodjenju igraca


        // PRO TIP: kako prikazati tablu, iznad ima funkcija launchGame() koja prikazuje lobby
        // samo uradite frame.remove(lobbyPanel) i napravite svoj panel koji je tabla za igru i dodajte pomocu
        // frame.add(boardPanel)
        // ali ovo je sve standardan Swing, imate tamo na moodle-u svasta o swingu
        System.out.println("GAME STARTED");
        frame.remove(lobbyPanel);

        gamePanel = new GamePanel(this.connectionManager.getGameType(), new DialogListener() {
            @Override
            public void dialogClosed() {
                frame.remove(gamePanel);
                frame.add(lobbyPanel);
                frame.pack();
            }
        });

        if(gameData != null) {
            gamePanel.setPlayerNick(gameData.getPlayerNick());
            gamePanel.setOpponentNick(gameData.getOpponentNick());
            gamePanel.setGameData(gameData);
        } else {
            gamePanel.setPlayerNick(connectionManager.getNickname());
        }

        frame.add(gamePanel);
        frame.pack();
    }

    @Override
    public void hostSocketOpened() {
        // Ovde treba prikazati neki dialog koji govori korisnika da saceka da se konektuje neko da bi igrali
        // treba na tom dialog-u da stoji 'cancel' dugme koje cu ja posle povezati da prekine cekanje i vrati
        // korisnika u lobby
        waitDialog = new WaitDialog(new DialogListener() {
            @Override
            public void dialogClosed() {
                connectionManager.closeSocket();
                lobbyPanel.setEnabledFields(true);
            }
        });
        waitDialog.setLocationRelativeTo(this.frame);
        waitDialog.setVisible(true);
        lobbyPanel.setEnabledFields(false);
    }

    @Override
    public void clientConnected() {
        // Ovde disable-ujete cancel dugme u onom dialogu iz hostSocketOpened() funkcije jer je vec krenuo neko
        // da se konektuje i ne zelimo da prekinemo sad taj postupak konektovanja i razmene informacija
        System.out.println("CLIENT CONNECTED");
        waitDialog.dispose();
        lobbyPanel.setEnabledFields(true);
    }

    @Override
    public void opponentMoveRecieved(Board board) {
        // Dobili ste protivnicki potez, treba da pomerite figuru po podacimo iz Move argumenta ove funkcije
        // pogledajte move, trebalo bi da ima dovoljno podataka, ako vam treba jos nesto dodajte
        gamePanel.moveReceived(board);
    }
}

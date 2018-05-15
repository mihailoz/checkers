package ui;

import commons.GameData;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private BoardPanel boardPanel;
    private GameData gameData;
    private PlayerPanel playerPanel, opponentPanel;

    public GamePanel() {
        this.setLayout(new BorderLayout());

        boardPanel = new BoardPanel();

        this.add(boardPanel, BorderLayout.CENTER);

        playerPanel = new PlayerPanel();

        this.add(playerPanel, BorderLayout.SOUTH);

        opponentPanel = new PlayerPanel();

        this.add(opponentPanel, BorderLayout.NORTH);
    }

    public void setPlayerNick(String nick) {
        this.playerPanel.setPlayerNick(nick);
    }

    public void setOpponentNick(String nick) {
        this.opponentPanel.setPlayerNick(nick);
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;

        boardPanel.updateBoard(gameData);
    }
}

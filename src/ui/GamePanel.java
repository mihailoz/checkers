package ui;

import commons.GameData;
import commons.GameType;
import logic.TurnController;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private BoardPanel boardPanel;
    private GameData gameData;
    private PlayerPanel playerPanel, opponentPanel;
    private TurnController turnController;

    public GamePanel(GameType gt) {
        this.setLayout(new BorderLayout());

        boolean isHost = gt.equals(GameType.HOST);

        boardPanel = new BoardPanel(isHost);

        this.add(boardPanel, BorderLayout.CENTER);

        playerPanel = new PlayerPanel(isHost);
        opponentPanel = new PlayerPanel(!isHost);

        this.add(opponentPanel, isHost ? BorderLayout.NORTH : BorderLayout.SOUTH);
        this.add(playerPanel, isHost ? BorderLayout.SOUTH : BorderLayout.NORTH);
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

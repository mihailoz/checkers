package ui;

import commons.GameType;
import commons.Move;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements BoardPanel.BoardListener {

    private BoardPanel boardPanel;
    private HistoryPanel historyPanel;
    private PlayerPanel playerPanel, opponentPanel;

    public GamePanel(GameType gt, DialogListener listener) {
        this.setLayout(new BorderLayout());

        boolean isHost = gt.equals(GameType.HOST);

        historyPanel = new HistoryPanel();
        boardPanel = new BoardPanel(isHost, listener, this);

        this.add(boardPanel, BorderLayout.CENTER);
        this.add(historyPanel, BorderLayout.EAST);

        playerPanel = new PlayerPanel(isHost);
        opponentPanel = new PlayerPanel(!isHost);

        this.add(opponentPanel, isHost ? BorderLayout.NORTH : BorderLayout.SOUTH);
        this.add(playerPanel, isHost ? BorderLayout.SOUTH : BorderLayout.NORTH);
    }

    public void setPlayerNick(String nick) {
        this.historyPanel.setPlayer(nick);
        this.playerPanel.setPlayerNick(nick);
    }

    public void setOpponentNick(String nick) {
        this.historyPanel.setOpponent(nick);
        this.opponentPanel.setPlayerNick(nick);
    }

    public BoardPanel getBoardPanel() {
        return this.boardPanel;
    }

    public HistoryPanel getHistoryPanel() {
        return historyPanel;
    }

    public void setPlayerTurn() {
        playerPanel.setOnTurn(true);
        opponentPanel.setOnTurn(false);
    }

    @Override
    public void movePlayed(Move move) {
        historyPanel.addPlayerMove(move);
        playerPanel.setOnTurn(false);
        opponentPanel.setOnTurn(true);
    }
}

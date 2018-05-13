package src.commons;

import java.util.ArrayList;

public class GameData {

    private Board board;
    private String playerNick, opponentNick;
    private ArrayList<Move> moveHistory;

    public GameData(String player, String opponent) {
        this.moveHistory = new ArrayList<>();
        this.playerNick = player;
        this.opponentNick = opponent;
        this.board = new Board();
        this.board.resetBoard();
    }

    public void addMove(Move move) {
        moveHistory.add(move);
    }

    public Board getBoard() {
        return board;
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public String getOpponentNick() {
        return opponentNick;
    }

    public ArrayList<Move> getMoveHistory() {
        return moveHistory;
    }
}


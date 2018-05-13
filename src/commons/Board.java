package src.commons;

public class Board {

    private Field[] board;

    public Board() {
        board = new Field[50];
    }

    public void resetBoard() {
        for(int i = 1; i <= 50; i++)
            setField(Field.EMPTY, i);

        for(int i = 1; i <= 20; i++)
            setField(Field.OPPONENT_FIGURE, i);

        for(int i = 31; i <= 50; i++)
            setField(Field.PLAYER_FIGURE, i);
    }

    private void setField(Field f, int i) {
        if (i < 1 || i > 50)
            throw new IllegalArgumentException("Field identifier must be a number from 1 to 50");

        board[i - 1] = f;
    }

    private Field getField(int i) {
        if (i < 1 || i > 50)
            return null;

        return board[i - 1];
    }
}



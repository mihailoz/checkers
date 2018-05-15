package commons;

public class Board {

    private Field[] board;

    public Board() {
        board = new Field[100];
    }

    public void resetBoard() {
        for(int i = 0; i < 100; i++)
            setField(Field.EMPTY, i);

        for(int i = 0; i < 20; i++)
            setField(Field.OPPONENT_FIGURE, i);

        for(int i = 30; i < 100; i++)
            setField(Field.PLAYER_FIGURE, i);
    }

    private void setField(Field f, int i) {
        if (i < 0 || i >= 100)
            throw new IllegalArgumentException("Field identifier must be a number from 1 to 50");

        board[i] = f;
    }

    public Field getField(int i) {
        if (i < 0 || i >= 100)
            return null;

        return board[i];
    }

    public int getSize() {
        return 100;
    }
}



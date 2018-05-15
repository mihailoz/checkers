package commons;

public class Board {

    public Field[] getBoard() {
        return board;
    }

    private Field[] board;

    public Board() {
        board = new Field[51];
    }

    public void resetBoard() {
        for(int i = 1; i < 51; i++)
            setField(Field.EMPTY, i);

        for(int i = 1; i < 21; i++)
            setField(Field.OPPONENT_FIGURE, i);

        for(int i = 31; i < 51; i++)
            setField(Field.PLAYER_FIGURE, i);
    }

    private void setField(Field f, int i) {
        if (i < 1 || i > 50)
            throw new IllegalArgumentException("Field identifier must be a number from 1 to 50");

        board[i] = f;
    }

    public Field getField(int i) {
        if (i < 1 || i > 50)
            return null;

        return board[i];
    }

    public int getSize() {
        return 51;
    }
}



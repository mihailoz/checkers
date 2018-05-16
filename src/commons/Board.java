package commons;

public class Board {

    private Field[] board;
    private String orientation;
    public Board() {
        board = new Field[51];
    }

    private Board(Field[] fields,String orientation){
        this.board = fields;
        this.orientation = orientation;
    }

    public void resetBoard(boolean isHost) {
        for(int i = 1; i < 51; i++)
            setField(Field.EMPTY, i);

        for(int i = 1; i < 2; i++)
            setField(isHost ? Field.OPPONENT_FIGURE : Field.PLAYER_FIGURE, i);


        for(int i = 7; i < 8; i++)
            setField(isHost ? Field.PLAYER_FIGURE : Field.OPPONENT_FIGURE, i);

        this.orientation = isHost ? "PLAYER" : "GUEST";
    }

    public void setField(Field f, int i) {
        if (i < 1 || i > 50)
            throw new IllegalArgumentException("Field identifier must be a number from 1 to 50");

        board[i] = f;
    }

    public Field getField(int i) {
        if (i < 1 || i > 50)
            return null;

        return board[i];
    }

    public void reversePlayers() {
        for(int i = 1; i < this.getSize(); i++) {
            if(getField(i).equals(Field.PLAYER_FIGURE))
                setField(Field.OPPONENT_FIGURE, i);
            else if (getField(i).equals(Field.PLAYER_QUEEN))
                setField(Field.OPPONENT_QUEEN, i);
            else if (getField(i).equals(Field.OPPONENT_FIGURE))
                setField(Field.PLAYER_FIGURE, i);
            else if (getField(i).equals(Field.OPPONENT_QUEEN))
                setField(Field.PLAYER_QUEEN, i);
        }
    }

    public Field[] getFields() {
        return board;
    }

    public int getSize() {
        return 51;
    }

    public String getOrientation() {
        return orientation;
    }
}



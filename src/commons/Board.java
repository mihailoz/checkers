package commons;

public class Board {

    private Field[] board;
    private String orientation;

    public Board boardForOpponent(){
        Field[] opponentBoard = new Field[board.length];
        for(Field f : opponentBoard) {
            if (f == Field.PLAYER_FIGURE)
                f = Field.OPPONENT_FIGURE;

            else if (f == Field.PLAYER_QUEEN)
                f = Field.OPPONENT_QUEEN;

            else if (f == Field.OPPONENT_FIGURE)
                f = Field.PLAYER_FIGURE;

            else if (f == Field.OPPONENT_QUEEN)
                f = Field.PLAYER_QUEEN;
        }
        String s;
        if(orientation.equals("PLAYER"))
            s="GUEST";
        else
            s="PLAYER";
        return new Board(opponentBoard,s);
    }

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

        for(int i = 1; i < 21; i++)
            setField(isHost ? Field.OPPONENT_FIGURE : Field.PLAYER_FIGURE, i);


        for(int i = 31; i < 51; i++)
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



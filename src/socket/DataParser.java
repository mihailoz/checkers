package socket;

import commons.Board;
import commons.Field;
import commons.Move;

import java.util.ArrayList;

public class DataParser {

    private static final int INDEX_CODE = 209;
    public static final int NICKNAME_CODE = INDEX_CODE + 1;
    public static final int MOVE_CODE = INDEX_CODE + 2;
    public static final int VICTORY_CODE = INDEX_CODE + 3;

    public static class DecodedData {
        private int code;
        private Object data;

        public DecodedData(int code, Object data) {
            this.code = code;
            this.data = data;
        }

        public int getCode() {
            return code;
        }

        public Object getData() {
            return data;
        }
    }

    public static class MoveData {
        private Board board;
        private int startPosition;
        private int endPosition;

        public MoveData(Board board, int startPosition, int endPosition) {
            this.board = board;
            this.startPosition = startPosition;
            this.endPosition = endPosition;
        }

        public Board getBoard() {
            return board;
        }

        public int getStartPosition() {
            return startPosition;
        }

        public int getEndPosition() {
            return endPosition;
        }
    }

    public static String encodeMove(Board board, Move move) {
        StringBuilder str = new StringBuilder();
        str.append(String.valueOf(MOVE_CODE));
        str.append(";");

        for(int i = 1; i < board.getSize(); i++) {
            str.append(board.getField(i).toInteger());
            str.append(" ");
        }

        str.append(";");
        str.append(String.valueOf(move.getStartPosition()));
        str.append(";");
        str.append(String.valueOf(move.getEndPosition()));

        return str.toString();
    }

    public static String encodeVictory() {
        return String.valueOf(VICTORY_CODE);
    }

    public static String encodeNick(String nickname) {
        return String.valueOf(NICKNAME_CODE) + ";" + nickname;
    }

    public static DecodedData decode(String data) {
        int code = Integer.parseInt(data.split(";")[0]);

        switch (code) {
            case NICKNAME_CODE:
                return new DecodedData(NICKNAME_CODE, data.split(";")[1]);
            case MOVE_CODE:
                return new DecodedData(MOVE_CODE, decodeMove(data));
            case VICTORY_CODE:
                return new DecodedData(VICTORY_CODE, null);
            default:
                return null;
        }
    }

    public static MoveData decodeMove(String data) {
        String[] splitted = data.split(";");

        String[] ints = splitted[1].split(" ");

        ArrayList<Integer> fields = new ArrayList<>();

        for(String s : ints) {
            fields.add(Integer.parseInt(s));
        }

        Board board = new Board();

        for(int i = 0; i < fields.size(); i++) {
            board.setField(Field.fromInteger(fields.get(i)), i + 1);
        }

        board.reversePlayers();

        return new MoveData(board, Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]));
    }
}

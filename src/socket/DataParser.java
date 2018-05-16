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

    public static String encodeMove(Board board) {
        StringBuilder move = new StringBuilder();
        move.append(String.valueOf(MOVE_CODE));
        move.append(";");

        for(int i = 1; i < board.getSize(); i++) {
            move.append(board.getField(i).toInteger());
            move.append(" ");
        }

        return move.toString();
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

    public static Board decodeMove(String data) {
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

        return board;
    }
}

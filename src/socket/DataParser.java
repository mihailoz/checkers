package socket;

import commons.Field;
import commons.Move;

public class DataParser {

    private static final int INDEX_CODE = 209;
    public static final int NICKNAME_CODE = INDEX_CODE + 1;
    public static final int MOVE_CODE = INDEX_CODE + 2;
    public static final int VALIDATED_MOVE_CODE = INDEX_CODE + 3;

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

    public static String encodeMove(Move move) {
        return String.valueOf(MOVE_CODE) +
                ";" +
                move.getStartPosition() +
                ";" +
                move.getEndPosition() +
                ";" +
                move.getEaten() +
                ";" +
                move.getFigure().toString();
    }

    public static String encodeNick(String nickname) {
        return String.valueOf(NICKNAME_CODE) + ";" + nickname;
    }

    public static String encodeValidateMove(boolean b) {
        return String.valueOf(VALIDATED_MOVE_CODE) + ";" + String.valueOf(b);
    }

    public static DecodedData decode(String data) {
        int code = Integer.parseInt(data.split(";")[0]);

        switch (code) {
            case NICKNAME_CODE:
                return new DecodedData(NICKNAME_CODE, data.split(";")[1]);
            case MOVE_CODE:
                return new DecodedData(MOVE_CODE, decodeMove(data));
            case VALIDATED_MOVE_CODE:
                return new DecodedData(VALIDATED_MOVE_CODE, Boolean.parseBoolean(data.split(";")[1]));
            default:
                return null;
        }
    }

    public static Move decodeMove(String data) {
        String[] splitted = data.split(";");

        int startPosition = Integer.parseInt(splitted[1]);
        int endPosition = Integer.parseInt(splitted[2]);
        int eaten = Integer.parseInt(splitted[3]);
        Field figure = Field.valueOf(splitted[4]);

        return new Move(startPosition, endPosition, eaten, figure);
    }
}

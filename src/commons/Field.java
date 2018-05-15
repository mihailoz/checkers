package commons;

public enum Field {
    PLAYER_FIGURE,
    OPPONENT_FIGURE,
    PLAYER_QUEEN,
    OPPONENT_QUEEN,
    EMPTY;

    public static Field fromInteger(int i) {
        switch (i) {
            case 0: return EMPTY;
            case 1: return PLAYER_FIGURE;
            case 2: return OPPONENT_FIGURE;
            case 3: return PLAYER_QUEEN;
            case 4: return OPPONENT_QUEEN;
            default: return EMPTY;
        }
    }

    public int toInteger() {
        switch (this) {
            case EMPTY: return 0;
            case PLAYER_FIGURE: return 1;
            case OPPONENT_FIGURE: return 2;
            case PLAYER_QUEEN: return 3;
            case OPPONENT_QUEEN: return 4;
            default: return -1;
        }
    }
}

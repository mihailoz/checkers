package commons;

public class Move {

    private int startPosition, endPosition;
    private int eatenPosition;
    private Field checker;

    public Move(int startPosition, int endPosition, int eaten, Field checker) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.eatenPosition = eaten;
        this.checker = checker;

    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public int getEatenPosition() {
        return eatenPosition;
    }

    public Field getChecker(){  return checker; }

}

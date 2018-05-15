package commons;

public class Move {

    private int startPosition, endPosition;
    private int eatenPosition;
    private Field figure;

    public Move(int startPosition, int endPosition, int eaten, Field figure) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.eatenPosition = eaten;
        this.figure = figure;

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

    public Field getFigure(){  return figure; }

}

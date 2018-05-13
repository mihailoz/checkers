package src.commons;

public class Move {

    private int startPosition, endPosition;
    private int eaten;
    private Field figure;

    public Move(int startPosition, int endPosition, int eaten, Field figure) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.eaten = eaten;
        this.figure = figure;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public int getEaten() {
        return eaten;
    }

    public Field getFigure() {
        return figure;
    }
}

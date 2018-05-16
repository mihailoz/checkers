package socket;

import commons.Board;


public abstract class DataListener {

    public abstract void clientConnected();

    public void dataReceived(String s) {

        DataParser.DecodedData data = DataParser.decode(s);

        switch (data.getCode()) {
            case DataParser.MOVE_CODE:
                this.moveReceived((DataParser.MoveData) data.getData());
                break;
            case DataParser.NICKNAME_CODE:
                this.nicknameReceived((String) data.getData());
                break;
            case DataParser.VICTORY_CODE:
                this.victoryReceived();
                break;
        }

    }

    public abstract void nicknameReceived(String nickname);

    public abstract void victoryReceived();

    public abstract void moveReceived(DataParser.MoveData moves);
}

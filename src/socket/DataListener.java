package socket;

import commons.Move;

public abstract class DataListener {

    public abstract void clientConnected();

    public void dataReceived(String s) {

        DataParser.DecodedData data = DataParser.decode(s);

        switch (data.getCode()) {
            case DataParser.MOVE_CODE:
                this.moveReceived((Move) data.getData());
                break;
            case DataParser.NICKNAME_CODE:
                this.nicknameReceived((String) data.getData());
                break;
            case DataParser.VALIDATED_MOVE_CODE:
                this.validatedMoveReceived((boolean) data.getData());
                break;
        }

    }

    public abstract void nicknameReceived(String nickname);

    public abstract void validatedMoveReceived(boolean b);

    public abstract void moveReceived(Move move);
}

package socket;

import commons.GameType;
import commons.Move;
import ui.GameActionListener;

import java.util.ArrayList;
import java.util.List;

public class ConnectionManager extends DataListener {

    private enum GameStatus {
        NOT_CONNECTED,
        CONNECTED,
        STARTED,
        PLAYER_TURN,
        OPPONENT_TURN;
    }

    private GameStatus status;

    private GameType type;
    private ClientSocket clientSocket;
    private HostSocket hostSocket;
    private String nickname;

    private List<GameActionListener> listeners;

    public ConnectionManager(String hostname, int port, String nick) {
        this.nickname = nick;
        this.type = GameType.JOIN;
        this.status = GameStatus.NOT_CONNECTED;

        this.listeners = new ArrayList<>();

        this.clientSocket = new ClientSocket(hostname, port, this);
    }

    public ConnectionManager(int port, String nick) {
        this.nickname = nick;
        this.type = GameType.HOST;
        this.status = GameStatus.NOT_CONNECTED;

        this.listeners = new ArrayList<>();

        this.hostSocket = new HostSocket(port, this);
    }

    public void connect() {
        if(this.type.equals(GameType.JOIN)) {
            if (this.clientSocket.connect()) {
                this.status = GameStatus.CONNECTED;
            }
        } else if(this.hostSocket.startListening()) {
            this.status = GameStatus.CONNECTED;
            for (GameActionListener listener : this.listeners) {
                listener.hostSocketOpened();
            }
        }
    }

    public void sendData(String data) {
        if(this.type.equals(GameType.HOST)) {
            hostSocket.sendData(data);
        } else {
            clientSocket.sendData(data);
        }
    }

    public void addListener(GameActionListener listener) {
        for (GameActionListener l : this.listeners) {
            if(l.equals(listener))
                return;
        }

        this.listeners.add(listener);
    }

    public boolean isConnected() {
        return (!this.status.equals(GameStatus.NOT_CONNECTED));
    }

    @Override
    public void clientConnected() {
        this.sendData(DataParser.encodeNick(this.nickname));
        for (GameActionListener listener : this.listeners) {
            listener.clientConnected();
        }
    }

    @Override
    public void nicknameReceived(String nickname) {
        if(type.equals(GameType.JOIN))
            this.sendData(DataParser.encodeNick(this.nickname));

        this.status = GameStatus.STARTED;

        for (GameActionListener listener : this.listeners) {
            listener.opponentNicknameRecieved(nickname);
        }
    }

    @Override
    public void validatedMoveReceived(boolean b) {

    }

    @Override
    public void moveReceived(Move move) {

    }
}
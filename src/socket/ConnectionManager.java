package socket;

import commons.Board;
import commons.GameStatus;
import commons.GameType;
import ui.GameActionListener;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager extends DataListener {

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

    public void closeSocket() {
        if(this.type.equals(GameType.HOST)) {
            hostSocket.stopThread();
        } else {
            clientSocket.stopThread();
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

        for(GameActionListener listener : this.listeners) {
            listener.gameStarted();
        }

        for (GameActionListener listener : this.listeners) {
            listener.opponentNicknameRecieved(nickname);
        }

        if(type.equals(GameType.HOST)) {
            this.status = GameStatus.PLAYER_TURN;
        } else {
            this.status = GameStatus.OPPONENT_TURN;
        }
    }

    @Override
    public void victoryReceived() {
        for (GameActionListener listener : this.listeners) {
            listener.victory();
        }
    }

    @Override
    public void moveReceived(Board board) {
        // Aleksa ovde pozovi svoju funkciju za validaciju poteza!
        // Ako je sve ok uradi sledeci kod
        // this.sendData(DataParser.encodeValidateMove(true))
        for (GameActionListener listener : this.listeners) {
            listener.opponentMoveRecieved(board);
        }

        // ako nije onda
        // this.sendData(DataParser.encodeValidateMove(false))
    }

    public String getNickname() {
        return nickname;
    }

    public GameType getGameType() {
        return this.type;
    }
}

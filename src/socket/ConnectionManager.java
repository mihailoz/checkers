package socket;

import commons.GameType;

import java.io.IOException;

public class ConnectionManager extends ClientSocketListener {

    private GameType type;
    private ClientSocket clientSocket;
    private HostSocket hostSocket;

    public ConnectionManager(String hostname, int port) throws IOException {
        this.type = GameType.JOIN;

        this.clientSocket = new ClientSocket(hostname, port, this);
    }

    public ConnectionManager(int port) throws IOException {
        this.type = GameType.HOST;

        this.hostSocket = new HostSocket(port);
    }

    @Override
    public void moveReceived() {
        System.out.println("Move recieved");
    }
}

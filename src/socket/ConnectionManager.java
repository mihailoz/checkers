package socket;

import java.io.IOException;

public class ConnectionManager extends ClientSocketListener {

    static enum Type {
        HOST, GUEST
    }

    private Type type;
    private ClientSocket clientSocket;
    private HostSocket hostSocket;

    public ConnectionManager(String hostname, int port) throws IOException {
        this.type = Type.GUEST;

        this.clientSocket = new ClientSocket(hostname, port, this);
    }

    public ConnectionManager(int port) throws IOException {
        this.type = Type.HOST;

        this.hostSocket = new HostSocket(port);
    }

    @Override
    public void moveReceived() {

    }
}

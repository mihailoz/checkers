package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientSocket {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    private ClientSocketListener listener;
    private String hostname;

    private int port;

    ClientSocket(String hostname, int port, ClientSocketListener listener) throws IOException {
        this.hostname = hostname;
        this.port = port;
        this.listener = listener;

        this.connect();
    }

    private void connect() throws IOException {
        this.socket = new Socket(hostname, port);
        this.writer = new PrintWriter(this.socket.getOutputStream(), true);
        this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

        String data;
        while ((data = reader.readLine()) != null) {
            if(listener != null) {
                listener.dataReceived(data);
            }
        }
    }
}

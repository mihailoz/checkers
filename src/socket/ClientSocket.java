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

    ClientSocket(String hostname, int port, ClientSocketListener listener) {
        this.hostname = hostname;
        this.port = port;
        this.listener = listener;

        this.connect();
    }

    private void connect() {
        Runnable clientTask = new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(hostname, port);
                    writer = new PrintWriter(socket.getOutputStream(), true);
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String data;
                    while ((data = reader.readLine()) != null) {
                        if(listener != null) {
                            listener.dataReceived(data);
                        }
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        };

        Thread clientThread = new Thread(clientTask);
        clientThread.start();
    }
}

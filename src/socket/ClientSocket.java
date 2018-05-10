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

    private DataListener listener;
    private String hostname;

    private int port;

    ClientSocket(String hostname, int port, DataListener listener) {
        this.hostname = hostname;
        this.port = port;
        this.listener = listener;
    }

    public boolean connect() {
        Runnable clientTask = () -> {
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
        };

        try {
            Thread clientThread = new Thread(clientTask);
            clientThread.start();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean sendData(String data) {
        if(this.socket == null || !this.socket.isConnected() || this.socket.isClosed())
            return false;

        writer.println(data);
        return true;
    }
}

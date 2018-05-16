package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class HostSocket {

    private ServerSocket socket;
    private Socket client;

    private int port;

    private PrintWriter writer;
    private BufferedReader reader;

    private DataListener listener;

    private Thread hostThread;

    HostSocket(int port, DataListener listener) {
        this.port = port;
        this.listener = listener;
    }

    public boolean startListening() {
        Runnable hostTask = () -> {
            try {
                socket = new ServerSocket(port);
                client = socket.accept();

                writer = new PrintWriter(client.getOutputStream(), true);
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

                if(client.isConnected())
                    listener.clientConnected();

                String data;
                while ((data = reader.readLine()) != null) {
                    if(listener != null)
                        listener.dataReceived(data);
                }
            } catch (IOException ioe) {
                System.out.println("SOCKET CLOSED");
            }
        };

        try {
            hostThread = new Thread(hostTask);
            hostThread.start();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void stopThread() {
        hostThread.interrupt();
        try {
            if(socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendData(String data) {
        if(this.socket == null || this.socket.isClosed())
            return false;

        writer.println(data);
        return true;
    }
}

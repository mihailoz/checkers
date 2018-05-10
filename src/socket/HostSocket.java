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

    HostSocket(int port) {
        this.port = port;

        this.startListening();
    }

    private void startListening() {
        Runnable hostTask = new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new ServerSocket(port);
                    client = socket.accept();

                    writer = new PrintWriter(client.getOutputStream(), true);
                    reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

                    String data;
                    while ((data = reader.readLine()) != null) {
                        System.out.println(data);
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        };


        Thread hostThread = new Thread(hostTask);
        hostThread.start();
    }
}

package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HostSocket {

    private ServerSocket socket;
    private Socket client;

    private int port;

    private PrintWriter writer;
    private BufferedReader reader;

    public HostSocket(int port) throws IOException {
        this.port = port;

        this.startListening();
    }

    private void startListening() throws IOException {
        socket = new ServerSocket(port);
        client = socket.accept();

        writer = new PrintWriter(client.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

        writer.println("EVO GA NEKI DATA BRE");

        String data;
        while ((data = reader.readLine()) != null) {
            System.out.println(data);
        }
    }
}

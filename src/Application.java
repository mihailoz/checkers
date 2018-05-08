import socket.ConnectionManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Application {

    public static void main(String[] args) {
        System.out.println("host/guest: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String hostGuestStr = br.readLine();

            if(hostGuestStr.equalsIgnoreCase("host")) {
                System.out.println("Port: ");
                String port = br.readLine();

                ConnectionManager manager = new ConnectionManager(Integer.parseInt(port));
            } else {
                System.out.println("Hostname: ");
                String hostname = br.readLine();
                System.out.println("Port: ");
                String port = br.readLine();

                ConnectionManager manager = new ConnectionManager(hostname, Integer.parseInt(port));
            }
        } catch (Exception e) {

        }
    }
}

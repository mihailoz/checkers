package socket;

public abstract class ClientSocketListener {

    public void dataReceived(String data) {
        System.out.println(data);
    }

    public abstract void moveReceived();
}

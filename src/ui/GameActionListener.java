package ui;

public interface GameActionListener {

    void opponentNicknameRecieved(String nickname);
    void hostSocketOpened();
    void clientConnected();
    void validateMove();

}

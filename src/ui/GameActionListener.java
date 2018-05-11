package ui;

import commons.Move;

public interface GameActionListener {

    void opponentNicknameRecieved(String nickname);
    void gameStarted();
    void hostSocketOpened();
    void clientConnected();
    void opponentMoveRecieved(Move move);

}

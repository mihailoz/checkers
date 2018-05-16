package ui;

import socket.DataParser;


public interface GameActionListener {

    void opponentNicknameRecieved(String nickname);
    void gameStarted();
    void hostSocketOpened();
    void clientConnected();
    void opponentMoveRecieved(DataParser.MoveData board);
    void victory();

}

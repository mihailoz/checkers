package ui;

import commons.Board;
import commons.Move;

import java.util.ArrayList;

public interface GameActionListener {

    void opponentNicknameRecieved(String nickname);
    void gameStarted();
    void hostSocketOpened();
    void clientConnected();
    void opponentMoveRecieved(Board board);

}

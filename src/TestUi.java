import commons.GameData;
import ui.BoardPanel;

import javax.swing.*;

public class TestUi {
    public static void main(String[] args){
        JFrame frame = new JFrame();

        BoardPanel panel = new BoardPanel(true);
        panel.updateBoard(new GameData("PLAYER1", "PLAYER2", true));

        frame = new JFrame("Checkers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }
}

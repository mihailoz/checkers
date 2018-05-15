import ui.BoardPanel;

import javax.swing.*;

public class TestUi {
    public static void main(String[] args){
        JFrame frame = new JFrame();

        BoardPanel panel = new BoardPanel();

        frame.add(panel);
        frame.setVisible(true);

    }
}

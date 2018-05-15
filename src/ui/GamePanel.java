package ui;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        this.setLayout(new BorderLayout());

        this.add(new BoardPanel(), BorderLayout.CENTER);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }

}

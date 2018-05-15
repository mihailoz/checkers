package ui;


import commons.Field;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by mihailozdravkovic on 5/15/18.
 */
public class FieldComponent extends JPanel {

    private static String WHITE_CHECKER_PATH = "./resources/white_checker.png";
    private static String BLACK_CHECKER_PATH = "./resources/black_checker.png";

    private BufferedImage image;
    private Field type;

    public FieldComponent() {
        this(Field.EMPTY);
    }

    public FieldComponent(Field type) {
        setOpaque(true);
        this.setLayout(new FlowLayout());
        ((FlowLayout) getLayout()).setAlignment(FlowLayout.CENTER);

        this.setType(type);
    }

    public void setType(Field type) {
        if(type.equals(this.type))
            return;

        switch (type) {
            case PLAYER_FIGURE:
                try {
                    image = ImageIO.read(new File(WHITE_CHECKER_PATH));
                    this.add(new JLabel(new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_FAST))));
                } catch (IOException ex) {
                    // handle exception...
                }
                break;
            case OPPONENT_FIGURE:
                try {
                    image = ImageIO.read(new File(BLACK_CHECKER_PATH));
                    this.add(new JLabel(new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_FAST))));
                } catch (IOException ex) {
                    // handle exception...
                }
                break;
        }

        this.revalidate();
        this.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(60, 60);
    }
}

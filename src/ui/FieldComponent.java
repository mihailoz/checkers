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
        this(Field.EMPTY, null);
    }

    public FieldComponent(int fieldNumber) {
        this(Field.EMPTY, fieldNumber);
    }

    public FieldComponent(Field type, Integer fieldNumber) {
        setOpaque(true);
        this.setLayout(new GridBagLayout());


        this.setType(type);

        if(fieldNumber != null) {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.anchor = GridBagConstraints.NORTHWEST;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            c.weighty = 0.1;

            JLabel numberLabel = new JLabel(fieldNumber.toString());
            numberLabel.setForeground(Color.LIGHT_GRAY);
            add(numberLabel, c);
        }
    }

    public void setType(Field type) {
        if(type.equals(this.type))
            return;

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;

        switch (type) {
            case PLAYER_FIGURE:
                try {
                    image = ImageIO.read(new File(WHITE_CHECKER_PATH));
                    this.add(new JLabel(new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_FAST))), c);
                } catch (IOException ex) {
                    // handle exception...
                }
                break;
            case OPPONENT_FIGURE:
                try {
                    image = ImageIO.read(new File(BLACK_CHECKER_PATH));
                    this.add(new JLabel(new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_FAST))), c);
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

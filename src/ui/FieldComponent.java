package ui;


import commons.Field;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by mihailozdravkovic on 5/15/18.
 */
public class FieldComponent extends JPanel {

    private static String WHITE_CHECKER_PATH = "./resources/rebellion_checker.png";
    private static String BLACK_CHECKER_PATH = "./resources/empire_checker.png";
    private static String WHITE_QUEEN_PATH = "./resources/rebellion_queen.png";
    private static String BLACK_QUEEN_PATH = "./resources/empire_queen.png";

    private JLabel imageContainer;
    private boolean highlighted;
    private Field type;
    private Color bgColor;
    private FieldListener listener;

    public FieldComponent() {
        this(null);
    }

    public FieldComponent(Integer fieldNumber) {
        setOpaque(true);
        this.setLayout(new GridBagLayout());

        this.highlighted = false;

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

            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if(listener != null)
                        listener.fieldClicked(fieldNumber, highlighted, FieldComponent.this.type);
                }
            });
        }
    }

    public void setListener(FieldListener listener) {
        this.listener = listener;
    }

    public void setType(Field type, boolean isHost) {
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


        BufferedImage image;
        switch (type) {
            case PLAYER_FIGURE:
                try {
                    if(isHost)
                        image = ImageIO.read(new File(WHITE_CHECKER_PATH));
                    else
                        image = ImageIO.read(new File(BLACK_CHECKER_PATH));

                    if(imageContainer != null)
                        this.remove(imageContainer);

                    imageContainer = new JLabel(new ImageIcon(image.getScaledInstance(60, 60, Image.SCALE_FAST)));
                    this.add(imageContainer, c);
                } catch (IOException ex) {
                    // handle exception...
                }
                break;
            case OPPONENT_FIGURE:
                try {
                    if(isHost)
                        image = ImageIO.read(new File(BLACK_CHECKER_PATH));
                    else
                        image = ImageIO.read(new File(WHITE_CHECKER_PATH));

                    if(imageContainer != null)
                        this.remove(imageContainer);

                    imageContainer = new JLabel(new ImageIcon(image.getScaledInstance(60, 60, Image.SCALE_FAST)));
                    this.add(imageContainer, c);
                } catch (IOException ex) {
                    // handle exception...
                }
                break;
            case PLAYER_QUEEN:
                try {
                    if(isHost)
                        image = ImageIO.read(new File(WHITE_QUEEN_PATH));
                    else
                        image = ImageIO.read(new File(BLACK_QUEEN_PATH));

                    if(imageContainer != null)
                        this.remove(imageContainer);

                    imageContainer = new JLabel(new ImageIcon(image.getScaledInstance(60, 60, Image.SCALE_FAST)));
                    this.add(imageContainer, c);
                } catch (IOException ex) {
                    // handle exception...
                }
                break;
            case OPPONENT_QUEEN:
                try {
                    if(isHost)
                        image = ImageIO.read(new File(BLACK_QUEEN_PATH));
                    else
                        image = ImageIO.read(new File(WHITE_QUEEN_PATH));

                    if(imageContainer != null)
                        this.remove(imageContainer);

                    imageContainer = new JLabel(new ImageIcon(image.getScaledInstance(60, 60, Image.SCALE_FAST)));
                    this.add(imageContainer, c);
                } catch (IOException ex) {
                    // handle exception...
                }
                break;
            case EMPTY:
                if(imageContainer != null)
                    this.remove(imageContainer);

                imageContainer = null;
                break;
        }

        this.type = type;
        this.revalidate();
        this.repaint();
    }

    public Field getType() {
        return this.type;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(80, 80);
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        if(this.highlighted == highlighted)
            return;

        this.highlighted = highlighted;

        if(highlighted) {
            this.bgColor = this.getBackground();
            this.setBackground(Color.CYAN);
        } else {
            this.setBackground(this.bgColor);
        }
    }

    public interface FieldListener {
        void fieldClicked(int j, boolean highlighted, Field type);
    }
}

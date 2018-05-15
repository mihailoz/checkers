package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BoardPanel extends JPanel {

    public BoardPanel() {

        JLabel[] blackFields = new JLabel[50];
        JLabel[] whiteFields = new JLabel[50];

        for(int i = 0; i < blackFields.length; i++){
            blackFields[i] = new JLabel();
            blackFields[i].setIcon(new ImageIcon("/home/luka/Luka/Skola/OOP2/GrupniZadaci/checkers-master/checkers/src/ui/pictures/gray1.jpg"));
            blackFields[i].setPreferredSize(new Dimension(100,100));
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
            blackFields[i].setBorder(border);
        }

        for(int i = 0; i < whiteFields.length; i++){
            whiteFields[i] = new JLabel();
            whiteFields[i].setIcon(new ImageIcon("/home/luka/Luka/Skola/OOP2/GrupniZadaci/checkers-master/checkers/src/ui/pictures/gray2.jpg"));
            whiteFields[i].setPreferredSize(new Dimension(100,100));
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
            whiteFields[i].setBorder(border);
        }

        this.setLayout(new GridLayout(10,10));
        this.setSize(10000,10000);

        for(int i = 0; i < 10; i++){
            if(i % 2 == 0){
                for(int j = 0; j < 5; j++){
                    this.add(whiteFields[5 * i + j]);
                    this.add(blackFields[5 * i + j]);
                }
            }
            else{
                for(int j = 0; j < 5; j++){
                    this.add(blackFields[5 * i + j]);
                    this.add(whiteFields[5 * i + j]);
                }
            }
        }

    }

}

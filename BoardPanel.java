package ui;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    public BoardPanel() {

        JButton[] blackButtons = new JButton[50];
        JButton[] whiteButtons = new JButton[50];

        for(int i = 0; i < blackButtons.length; i++){
            blackButtons[i] = new JButton();
            blackButtons[i].setBackground(Color.BLACK);
        }

        for(int i = 0; i < whiteButtons.length; i++){
            whiteButtons[i] = new JButton();
            whiteButtons[i].setBackground(Color.WHITE);
        }

        this.setLayout(new GridLayout(10,10));
        this.setSize(1000,1000);

        for(int i = 0; i < 10; i++){
            if(i % 2 == 0){
                for(int j = 0; j < 5; j++){
                    this.add(whiteButtons[5 * i + j]);
                    this.add(blackButtons[5 * i + j]);
                }
            }
            else{
                for(int j = 0; j < 5; j++){
                    this.add(blackButtons[5 * i + j]);
                    this.add(whiteButtons[5 * i + j]);
                }
            }
        }


    }

}

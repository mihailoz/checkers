package ui;

import commons.GameData;
import logic.FlipField;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private FieldComponent[] fields = new FieldComponent[100];

    public BoardPanel() {
        int j = 1;
        for(int i = 0; i < fields.length; i++) {
            if((i / 10) % 2 == 0) {
                if(i % 2 == 0) {
                    fields[i] = new FieldComponent();
                    fields[i].setBackground(Color.lightGray);
                } else {
                    fields[i] = new FieldComponent(FlipField.boardToLogic[j]);
                    j++;
                    fields[i].setBackground(Color.darkGray);
                }
            } else {
                if(i % 2 == 0) {
                    fields[i] = new FieldComponent(FlipField.boardToLogic[j]);
                    j++;
                    fields[i].setBackground(Color.darkGray);
                } else {
                    fields[i] = new FieldComponent();
                    fields[i].setBackground(Color.lightGray);
                }
            }
        }

        this.setLayout(new GridLayout(10,10));

        for(int i = 0; i < fields.length; i++){
            this.add(fields[i]);
        }
    }

    public void updateBoard(GameData gameData) {
        for(int i = 0; i < gameData.getBoard().getSize(); i++) {
            fields[i].setType(gameData.getBoard().getField(i));
        }
    }
}

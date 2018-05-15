package ui;

import commons.Field;
import commons.GameData;
import logic.FlipField;
import logic.TurnController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static logic.FlipField.boardToLogic;
import static logic.FlipField.logicToBoard;

public class BoardPanel extends JPanel implements FieldComponent.FieldListener {

    private FieldComponent[] fields = new FieldComponent[100];
    private FieldComponent[] blackFields = new FieldComponent[51];

    private java.util.List<Integer> highlightedFields;

    private TurnController turnController;

    public BoardPanel() {
        highlightedFields = new ArrayList<>();

        int j = 1;
        for(int i = 0; i < fields.length; i++) {
            if((i / 10) % 2 == 0) {
                if(i % 2 == 0) {
                    fields[i] = new FieldComponent();
                    fields[i].setBackground(Color.lightGray);
                } else {
                    fields[i] = new FieldComponent(j);
                    blackFields[j] = fields[i];
                    fields[i].setListener(this);
                    j++;
                    fields[i].setBackground(Color.darkGray);
                }
            } else {
                if(i % 2 == 0) {
                    fields[i] = new FieldComponent(j);
                    blackFields[j] = fields[i];
                    fields[i].setListener(this);
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
        turnController = new TurnController("PLAYER", gameData.getBoard());

        for(int i = 1; i < gameData.getBoard().getSize(); i++) {
            blackFields[i].setType(gameData.getBoard().getField(i));
        }
    }

    @Override
    public void fieldClicked(int j, boolean highlighted, Field type) {
        if(type.equals(Field.PLAYER_FIGURE) || type.equals(Field.PLAYER_QUEEN)) {
            if(highlighted) {
                for(Integer i : this.highlightedFields)
                    blackFields[i].setHighlighted(false);
            } else {
                for(Integer i : this.highlightedFields)
                    blackFields[i].setHighlighted(false);

                this.highlightedFields.clear();
                blackFields[j].setHighlighted(true);
                this.highlightedFields.add(j);

                turnController.makePathsForField(j);
                java.util.List<Integer> fields = turnController.availableFields();
                for(Integer i : fields) {
                    blackFields[i].setHighlighted(true);
                    this.highlightedFields.add(i);
                }
            }
        }
    }
}

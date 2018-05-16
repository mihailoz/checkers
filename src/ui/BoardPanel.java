package ui;

import commons.*;
import logic.FlipField;
import logic.TurnController;
import socket.ConnectionManager;
import socket.DataParser;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static logic.FlipField.boardToLogic;
import static logic.FlipField.logicToBoard;

public class BoardPanel extends JPanel implements FieldComponent.FieldListener {

    private FieldComponent[] fields = new FieldComponent[100];
    private FieldComponent[] blackFields = new FieldComponent[51];

    private java.util.List<Integer> highlightedFields;

    private TurnController turnController;
    private ConnectionManager connectionManager;
    private DialogListener dialogListener;

    private boolean onTurn;
    private boolean isHost;
    private boolean turnStarted = false;

    public BoardPanel(boolean isHost, DialogListener dialogListener) {
        highlightedFields = new ArrayList<>();


        this.dialogListener = dialogListener;
        this.onTurn = isHost;
        this.isHost = isHost;

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
        turnController = new TurnController(gameData.getBoard().getOrientation(), gameData.getBoard());


        for(int i = 1; i < gameData.getBoard().getSize(); i++) {
            blackFields[i].setType(gameData.getBoard().getField(i), isHost);
        }
    }

    public void updateBoard(boolean opponentsTurn) {
        for(int i = 1; i < turnController.getBoard().getSize(); i++) {
            if(!blackFields[i].getType().equals(turnController.getBoard().getField(i))) {
                blackFields[i].setType(turnController.getBoard().getField(i), isHost);
            }
        }

        if(opponentsTurn && turnController.isGameOver()) {
            GameOverDialog god = new GameOverDialog(dialogListener, false, isHost);
        }
    }

    @Override
    public void fieldClicked(int j, boolean highlighted, Field type) {
        if(!onTurn)
            return;

        if((type.equals(Field.PLAYER_FIGURE) || type.equals(Field.PLAYER_QUEEN)) && !turnStarted) {
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
        } else if (highlighted && type.equals(Field.EMPTY)) {
            if(turnController.availableFields().contains(j)) {
                turnController.choosePath(j);
                updateBoard(false);

                if(turnController.isGameOver()) {
                    GameOverDialog god = new GameOverDialog(dialogListener, true, isHost);
                }

                for(Integer i : this.highlightedFields)
                    blackFields[i].setHighlighted(false);

                this.highlightedFields.clear();

                if(turnController.isTurnOver()) {
                    connectionManager.sendData(DataParser.encodeMove(this.turnController.getBoard()));
                    turnStarted = false;
                    onTurn = false;
                } else {
                    blackFields[j].setHighlighted(true);
                    highlightedFields.add(j);

                    for (Integer i : turnController.availableFields()) {
                        blackFields[i].setHighlighted(true);
                        highlightedFields.add(i);
                    }

                    turnStarted = true;
                }

                System.out.println("Played move: " + j);
            }
        }
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void moveReceived(Board board) {
        turnController = new TurnController(isHost ? "PLAYER" : "ELSE",
                board);

        updateBoard(true);
        System.out.println("Move recieved");
        onTurn = true;
    }
}

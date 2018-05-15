package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mihailozdravkovic on 5/15/18.
 */
public class PlayerPanel extends JPanel {

    private String playerNick;
    private JLabel playerLabel;
    private boolean onTurn;

    public PlayerPanel(boolean onTurn) {
        playerLabel = new JLabel();

        this.add(playerLabel);
        this.setOnTurn(onTurn);
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public void setPlayerNick(String playerNick) {
        this.playerNick = playerNick;

        this.setOnTurn(this.onTurn);
    }

    public void setOnTurn(boolean b) {
        if(b) {
            this.playerLabel.setForeground(Color.RED);
            this.playerLabel.setText(playerNick + " (on turn)");
        } else {
            this.playerLabel.setForeground(Color.DARK_GRAY);
            this.playerLabel.setText(playerNick);
        }

        this.onTurn = b;
    }
}

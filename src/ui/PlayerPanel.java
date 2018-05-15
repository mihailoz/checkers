package ui;

import javax.swing.*;

/**
 * Created by mihailozdravkovic on 5/15/18.
 */
public class PlayerPanel extends JPanel {

    private String playerNick;
    private JLabel playerLabel;

    public PlayerPanel() {
        playerLabel = new JLabel();

        this.add(playerLabel);
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public void setPlayerNick(String playerNick) {
        this.playerNick = playerNick;

        this.playerLabel.setText(playerNick);
    }
}

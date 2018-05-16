package ui;

import commons.Move;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HistoryPanel extends JPanel {

    private String player, opponent;
    private JScrollPane scrollPane;
    private JPanel panel;

    public HistoryPanel() {
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(200, 1000));

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        scrollPane.setViewportView(panel);

        this.add(scrollPane);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 600);
    }

    public void addPlayerMove(Move mv) {
        panel.add(new JLabel(player + ": " + mv.getStartPosition() + " -> " + mv.getEndPosition()));
        panel.revalidate();
        panel.repaint();
    }

    public void addOpponentMove(int startPosition, int endPosition) {
        panel.add(new JLabel(opponent + ": " + startPosition + " -> " + endPosition));
        panel.revalidate();
        panel.repaint();
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }
}

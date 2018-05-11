import ui.UIController;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIController ui = new UIController();
                ui.launchGame();
            }
        });
    }
}

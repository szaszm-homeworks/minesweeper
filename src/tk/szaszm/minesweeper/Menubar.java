package tk.szaszm.minesweeper;

import javax.swing.*;
import java.awt.event.ActionListener;

class Menubar extends JMenuBar {
    private JMenuItem newGame;
    private ActionListener newGameListener;

    Menubar() {
        super();

        newGameListener = null;

        JMenu file = new JMenu("File");
        file.setMnemonic('f');
        newGame = new JMenuItem("New game");
        newGame.setMnemonic('n');
        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic('x');
        exit.addActionListener(actionEvent -> System.exit(0));
        file.add(newGame);
        file.add(new JSeparator());
        file.add(exit);

        add(file);
    }

    void setNewGameListener(ActionListener actionListener) {
        newGameListener = actionListener;
        if(newGame == null) return;
        if(newGameListener != null) newGame.removeActionListener(newGameListener);
        newGame.addActionListener(actionListener);
    }
}

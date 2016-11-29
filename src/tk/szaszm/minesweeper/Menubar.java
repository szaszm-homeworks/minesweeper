package tk.szaszm.minesweeper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by marci on 2016.11.28..
 */
class Menubar extends JMenuBar {
    private JMenu file;
    private JMenu edit;
    private JMenuItem newGame;
    private ActionListener newGameListener;

    Menubar() {
        super();

        newGameListener = null;

        file = new JMenu("File");
        file.setMnemonic('f');
        newGame = new JMenuItem("New game");
        newGame.setMnemonic('n');
        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic('x');
        exit.addActionListener(actionEvent -> System.exit(0));
        file.add(newGame);
        file.add(new JSeparator());
        file.add(exit);

        edit = new JMenu("Edit");
        edit.setMnemonic('e');

        add(file);
        add(edit);
    }

    public void setNewGameListener(ActionListener actionListener) {
        newGameListener = actionListener;
        if(newGame == null) return;
        if(newGameListener != null) newGame.removeActionListener(newGameListener);
        newGame.addActionListener(actionListener);
    }
}

package tk.szaszm.minesweeper;

import javax.swing.*;
import java.awt.event.ActionListener;

class Menubar extends JMenuBar {
    private JMenuItem newGame;
    private ActionListener newGameListener;

    private JMenuItem results;
    private ActionListener resultsListener;

    Menubar() {
        super();

        newGameListener = null;

        JMenu game = new JMenu("Game");
        game.setMnemonic('f');
        newGame = new JMenuItem("New game");
        newGame.setMnemonic('n');

        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic('x');
        exit.addActionListener(actionEvent -> System.exit(0));

        results = new JMenuItem("Results");
        results.setMnemonic('r');

        game.add(newGame);
        game.add(results);
        game.add(new JSeparator());
        game.add(exit);

        add(game);
    }

    void setNewGameListener(ActionListener actionListener) {
        if(newGame == null) return;
        if(newGameListener != null) newGame.removeActionListener(newGameListener);
        newGame.addActionListener(actionListener);
        newGameListener = actionListener;
    }

    void setResultsListener(ActionListener actionListener) {
        if(results == null) return;
        if(resultsListener != null) results.removeActionListener(resultsListener);
        results.addActionListener(actionListener);
        resultsListener = actionListener;
    }
}

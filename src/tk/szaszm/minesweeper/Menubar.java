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

    Menubar() {
        super();

        file = new JMenu("File");
        file.setMnemonic('f');
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(actionEvent -> System.exit(0));
        file.add(exit);

        edit = new JMenu("Edit");
        edit.setMnemonic('e');

        add(file);
        add(edit);
    }
}

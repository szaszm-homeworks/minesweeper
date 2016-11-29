package tk.szaszm.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window extends JFrame {
    private Menubar menubar;
    private Board playBoard;

    public Window() throws IOException {
        super("Minesweeper");
        menubar = new Menubar();
        setJMenuBar(menubar);

        FieldGraphicsProvider fieldGraphicsProvider = new FieldGraphicsProvider();
        playBoard = new Board(4,3, fieldGraphicsProvider);

        setLayout(new FlowLayout());
        add(playBoard);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }
}

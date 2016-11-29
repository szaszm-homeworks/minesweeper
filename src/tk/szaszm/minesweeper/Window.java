package tk.szaszm.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window extends JFrame {
    private Board board;
    private BoardGenerator boardGenerator;
    private FieldGraphicsProvider fieldGraphicsProvider;

    public Window(BoardGenerator boardGenerator, FieldGraphicsProvider fieldGraphicsProvider, Menubar menubar) throws IOException {
        super("Minesweeper");

        this.fieldGraphicsProvider = fieldGraphicsProvider;
        this.boardGenerator = boardGenerator;

        setLayout(new FlowLayout());
        setJMenuBar(menubar);
        menubar.setNewGameListener(actionEvent -> generateBoard());

        generateBoard();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void generateBoard() {
        if(board != null) remove(board);
        board = new Board(16, 9, fieldGraphicsProvider);
        boardGenerator.generateBombs(board, 1);
        add(board);
        revalidate();
        repaint();
    }
}

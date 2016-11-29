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

        setResizable(false);

        generateBoard();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void generateBoard() {
        if(board != null) remove(board);
        int width = 9;
        int height = 9;
        setMinimumSize(new Dimension(width * Field.SIZE + 20, height * Field.SIZE + 30));
        setMaximumSize(new Dimension(width * Field.SIZE + 20, height * Field.SIZE + 30));
        setPreferredSize(new Dimension(width * Field.SIZE + 20, height * Field.SIZE + 30));
        board = new Board(width, height, fieldGraphicsProvider, boardGenerator);
        add(board);
        revalidate();
        repaint();
    }
}

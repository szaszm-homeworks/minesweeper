package tk.szaszm.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window extends JFrame {
    private Board board;
    private BoardGenerator boardGenerator;
    private FieldGraphicsProvider fieldGraphicsProvider;
    private boolean timerStarted;
    private Topbar topbar;

    public Window(BoardGenerator boardGenerator, FieldGraphicsProvider fieldGraphicsProvider, Menubar menubar) throws IOException {
        super("Minesweeper");

        this.fieldGraphicsProvider = fieldGraphicsProvider;
        this.boardGenerator = boardGenerator;
        timerStarted = false;

        setLayout(new FlowLayout());
        setJMenuBar(menubar);
        menubar.setNewGameListener(actionEvent -> generateBoard());

        setResizable(false);

        topbar = new Topbar();
        add(topbar);

        generateBoard();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void generateBoard() {
        if(board != null) remove(board);
        int width = 9;
        int height = 9;
        setMinimumSize(new Dimension(width * Field.SIZE + 20, height * Field.SIZE + 70));
        setMaximumSize(new Dimension(width * Field.SIZE + 20, height * Field.SIZE + 70));
        setPreferredSize(new Dimension(width * Field.SIZE + 20, height * Field.SIZE + 70));
        board = new Board(width, height, fieldGraphicsProvider, boardGenerator, this);
        add(board);
        revalidate();
        repaint();
    }

    public void startTimer() {
        if(timerStarted) {
            timerStarted = false;
            topbar.stopTimer();
        }
        topbar.resetTimer();
        timerStarted = true;
        topbar.startTimer();
    }

    public void stopTimer() {
        topbar.stopTimer();
    }
}

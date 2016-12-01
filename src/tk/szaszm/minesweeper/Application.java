package tk.szaszm.minesweeper;

import java.io.IOException;

/**
 * Created by marci on 2016.11.29..
 */
public class Application {
    private Window window;
    private Menubar menubar;
    private BoardGenerator boardGenerator;
    private FieldGraphicsProvider fieldGraphicsProvider;

    public Application() throws IOException {
        this.menubar = new Menubar();
        boardGenerator = new BoardGenerator();
        fieldGraphicsProvider = new FieldGraphicsProvider();
        window = new Window(boardGenerator, fieldGraphicsProvider, this.menubar);
    }


    public void run() {
        window.setVisible(true);
    }
}

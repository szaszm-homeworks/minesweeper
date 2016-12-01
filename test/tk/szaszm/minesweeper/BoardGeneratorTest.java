package tk.szaszm.minesweeper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by marci on 2016.12.01..
 */
public class BoardGeneratorTest {
    private Board board;
    private BoardGenerator generator;

    @Before
    public void setup() {
        generator = new BoardGenerator();
        board = new Board(9, 9, null, generator, null);
    }

    @Test
    public void testGenerateBombs() {
        Assert.assertEquals(0, countBombs(board));
        generator.generateBombs(board, 5);
        Assert.assertEquals(8, countBombs(board));
        generator.resetBoard(board);
        Assert.assertEquals(0, countBombs(board));
    }

    private int countBombs(Board board) {
        int cnt = 0;
        for (int i = 0; i < board.getBoardWidth() * board.getBoardHeight(); i++) {
            Field field = board.getFieldAt(i);
            if(field.isBomb()) cnt++;
        }
        return cnt;
    }
}

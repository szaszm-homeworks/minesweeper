package tk.szaszm.minesweeper;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by marci on 2016.11.29..
 */
public class BoardGenerator {
    private static Random rnd = new Random();

    private double getBombRatioByDifficultyLevel(int difficultyLevel) {
        if(difficultyLevel < 0) difficultyLevel = 0;
        if(difficultyLevel > 9) difficultyLevel = 9;
        return 0.015 * (5 + difficultyLevel);
    }

    public void generateBombs(Board board, int difficultyLevel) {
        System.out.println("generate");
        double bombRatio = getBombRatioByDifficultyLevel(difficultyLevel);
        int len = board.getBoardHeight()*board.getBoardWidth();
        int nBombs = (int)(len*bombRatio);
        ArrayList<Integer> bombPositions = new ArrayList<>();
        for (int i = 0; i < nBombs; i++) {
            int newpos = rnd.nextInt(len - bombPositions.size());
            int cnt = 0;
            for(int pos: bombPositions) {
                if(pos <= newpos) cnt++;
            }
            newpos += cnt;
            bombPositions.add(newpos);
        }

        for(int pos: bombPositions) {
            board.getFieldAt(pos).setBomb(true);
        }
    }

    public void resetBoard(Board board) {
        System.out.println("reset");
        for (int i = 0; i < board.getBoardWidth() * board.getBoardHeight(); i++) {
            Field field = board.getFieldAt(i);
            field.setFieldState(FieldState.UNREVEALED);
            field.setBomb(false);
        }
        board.setEnded(false);
        board.setInitialized(false);
    }
}

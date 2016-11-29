package tk.szaszm.minesweeper;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by marci on 2016.11.29..
 */
public class BoardGenerator {
    private double getBombRatioByDifficultyLevel(int difficultyLevel) {
        if(difficultyLevel < 0) difficultyLevel = 0;
        if(difficultyLevel > 9) difficultyLevel = 9;
        return 0.02 * (1 + difficultyLevel);
    }

    public void generateBombs(Board board, int difficultyLevel) {
        double bombRatio = getBombRatioByDifficultyLevel(difficultyLevel);
        int len = board.getBoardHeight()*board.getBoardWidth();
        int nBombs = (int)(len*bombRatio);
        ArrayList<Integer> bombPositions = new ArrayList<>();
        Random rnd = new Random();
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
}

package tk.szaszm.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by marci on 2016.11.30..
 */
public class ResultsWindow extends JFrame {
    private JButton close;
    private ArrayList<Result> results;
    private ResultsView resultsView;
    private int currentDifficultyLevel;

    public ResultsWindow() {
        super("Results");
        setLayout(new FlowLayout());
        currentDifficultyLevel = 0;

        resultsView = new ResultsView();
        add(resultsView);

        close = new JButton("Close");
        close.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                onCloseButtonPressed();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) { }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) { }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) { }

            @Override
            public void mouseExited(MouseEvent mouseEvent) { }
        });

        add(close);

        setMinimumSize(new Dimension(250, 200));
        setPreferredSize(new Dimension(250, 200));
        setMaximumSize(new Dimension(250, 200));
        setResizable(false);
    }

    private void refreshResultsView() {
        resultsView.loadResults(results);
    }

    private void loadScores(int difficultyLevel) {
        results = null;
        currentDifficultyLevel = difficultyLevel;
        try {
            FileInputStream fileInputStream = new FileInputStream(difficultyLevel+".ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            results = (ArrayList<Result>)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch(Exception ignored) { }
        if(results == null) results = new ArrayList<>();
    }

    private void saveScores(int difficultyLevel) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(difficultyLevel+".ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(results);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    private void addScore(String name, int points) {
        addScore(new Result(name, points));
    }

    private void addScore(Result result) {
        results.add(result);
        Collections.sort(results);
    }

    private static int counter = 2;
    public void showScoresForDifficulty(int difficultyLevel) {
        currentDifficultyLevel = difficultyLevel;
        loadScores(difficultyLevel);
        addScore("name", counter++);
        setVisible(true);
        refreshResultsView();
    }

    private void onCloseButtonPressed() {
        try {
            saveScores(currentDifficultyLevel);
            setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

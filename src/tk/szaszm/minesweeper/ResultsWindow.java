package tk.szaszm.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.lang.ref.WeakReference;
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
    private NameInputPanel nameInputPanel;
    private WeakReference<Window> window;

    public ResultsWindow(Window window) {
        super("Results");
        this.window = new WeakReference<>(window);
        setLayout(new FlowLayout());
        currentDifficultyLevel = 0;

        resultsView = new ResultsView();
        add(resultsView);

        nameInputPanel = new NameInputPanel(this, 0);
        nameInputPanel.setVisible(false);
        add(nameInputPanel);

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
            truncateResults();
        } catch(Exception ignored) { }
        if(results == null) results = new ArrayList<>();
    }

    private void truncateResults() {
        if(results.size() >= 6) {
            results.subList(6, results.size()).clear();
        }
    }

    private void saveScores(int difficultyLevel) throws IOException {
        Collections.sort(results);
        truncateResults();
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
        truncateResults();
    }

    private static int counter = 2;
    public void showScoresForDifficulty(int difficultyLevel) {
        currentDifficultyLevel = difficultyLevel;
        loadScores(difficultyLevel);
        setVisible(true);
        nameInputPanel.setVisible(false);
        refreshResultsView();
    }

    public void showWithNameInput(int difficultyLevel, int points) {
        currentDifficultyLevel = difficultyLevel;
        loadScores(difficultyLevel);
        nameInputPanel.setVisible(true);
        nameInputPanel.setPoints(points);
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


    public void setResultFromPanel(String name, int points) {
        addScore(name, points);
        refreshResultsView();
        window.get().generateBoard();
    }
}

package tk.szaszm.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by marci on 2016.11.30..
 */
public class Topbar extends JPanel {
    private Timer timer;
    private int counter;
    private JLabel jLabel;

    public Topbar() {
        super(new FlowLayout());
        counter = 0;
        timer = new Timer(1000, actionEvent -> incrementCounter());

        jLabel = new JLabel("0");
        add(jLabel);

        timer.setInitialDelay(1000);
    }

    public void stopTimer() {
        timer.stop();
    }

    public void resetTimer() {
        counter = 0;
        jLabel.setText(Integer.toString(counter));
    }

    public void startTimer() {
        timer.start();
    }

    private void incrementCounter() {
        counter++;
        jLabel.setText(Integer.toString(counter));
    }
}

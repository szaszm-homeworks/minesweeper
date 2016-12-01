package tk.szaszm.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.ref.WeakReference;

/**
 * Created by marci on 2016.12.01..
 */
public class NameInputPanel extends JPanel {
    private JTextField jTextField;
    private WeakReference<ResultsWindow> resultsWindow;
    private int points;

    public NameInputPanel(ResultsWindow resultsWindow, int points) {
        super(new FlowLayout());
        this.points = points;
        this.resultsWindow = new WeakReference<>(resultsWindow);
        add(new JLabel("Name:"));
        jTextField = new JTextField();
        jTextField.setMinimumSize(new Dimension(100, 25));
        jTextField.setMaximumSize(new Dimension(100, 25));
        jTextField.setPreferredSize(new Dimension(100, 25));
        add(jTextField);
        JButton save = new JButton("Save");
        save.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                handleSaveClick();
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
        add(save);
    }

    private void handleSaveClick() {
        resultsWindow.get().setResultFromPanel(jTextField.getText(), points);
        setVisible(false);
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

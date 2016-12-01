package tk.szaszm.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by marci on 2016.11.30..
 */
public class ResultsView extends JPanel {
    private GroupLayout layout;

    public ResultsView() {
        super(new BorderLayout());
        layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        setMinimumSize(new Dimension(200, 150));
        setPreferredSize(new Dimension(200, 150));
        setMaximumSize(new Dimension(200, 150));
    }

    public void loadResults(ArrayList<Result> results) {
        removeAll();

        Collections.sort(results);

        GroupLayout.Group hgroup = layout.createSequentialGroup();
        GroupLayout.Group vgroup = layout.createSequentialGroup();
        ArrayList<JLabel> names = new ArrayList<>();
        ArrayList<JLabel> points = new ArrayList<>();

        for (Result result: results) {
            JLabel nameJLabel = new JLabel(result.name);
            names.add(nameJLabel);
            add(nameJLabel);
            JLabel pointsJLabel = new JLabel(Integer.toString(result.points));
            points.add(pointsJLabel);
            add(pointsJLabel);

            vgroup.addGroup(layout.createParallelGroup()
                    .addComponent(nameJLabel, GroupLayout.Alignment.LEADING)
                    .addComponent(pointsJLabel, GroupLayout.Alignment.TRAILING));
        }

        GroupLayout.Group namesGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING, true);
        for(JLabel nameJLabel: names) {
            namesGroup.addComponent(nameJLabel);
        }

        GroupLayout.Group pointsGroup = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        for(JLabel pointJLabel: points) {
            pointsGroup.addComponent(pointJLabel);
        }

        hgroup.addGroup(namesGroup).addGroup(pointsGroup);

        layout.setHorizontalGroup(hgroup);
        layout.setVerticalGroup(vgroup);
    }
}

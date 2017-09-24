package uk.co.datadisk.mazegenerator;

import javax.swing.*;
import java.awt.*;

public class OptionsDialog extends JDialog {

    private static final long serialVersionUID = -7901947777761618592L;

    private int rows = 0;
    private int cols = 0;
    private int type = 0;

    private JTextField rowsField = new JTextField(3);
    private JTextField colsField = new JTextField(3);
    private JRadioButton mazeButton = new JRadioButton("Maze");
    private JRadioButton antiButton = new JRadioButton("Anti-Maze");


    public OptionsDialog(int rows, int cols, int type) {
        this.rows = rows;
        this.cols = cols;
        this.type = type;

        setTitle("Maze Generator Options");

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);

        JLabel rowsLabel = new JLabel("Rows:");
        mainPanel.add(rowsLabel);

        rowsField.setText("" + rows);
        mainPanel.add(rowsField);

        JLabel colsLabel = new JLabel("Cols:");
        mainPanel.add(colsLabel);

        colsField.setText("" + cols);
        mainPanel.add(colsField);

        JLabel typeLabel = new JLabel("Maze Type:");
        mainPanel.add(typeLabel);

        mainPanel.add(mazeButton);
        mainPanel.add(antiButton);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(mazeButton);
        buttonGroup.add(antiButton);

        if(type == MazeGenerator.TYPE_MAZE) {
            mazeButton.setSelected(true);
        } else {
            antiButton.setSelected(true);
        }

        // button panel
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.PAGE_END);


    }
}

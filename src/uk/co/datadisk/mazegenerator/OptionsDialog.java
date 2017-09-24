package uk.co.datadisk.mazegenerator;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsDialog extends JDialog {

    private static final long serialVersionUID = -7901947777761618592L;

    private int rows = 0;
    private int cols = 0;
    private int type = 0;
    private boolean canceled = true;

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

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        buttonPanel.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        buttonPanel.add(cancelButton);

        getRootPane().setDefaultButton(okButton);
    }

    private void cancel() {
        setVisible(false);
    }

    private void close() {

        try {
            String rowsString = rowsField.getText();
            String colsString = colsField.getText();

            int newRows = Integer.parseInt(rowsString);
            int newCols = Integer.parseInt(colsString);

            if (newRows > 1 && newCols > 1) {
                rows = newRows;
                cols = newCols;

                if (mazeButton.isSelected()) {
                    type = MazeGenerator.TYPE_MAZE;
                } else {
                    type = MazeGenerator.TYPE_ANTIMAZE;
                }

                setVisible(false);
                canceled = false;
            } else {
                String message = "You must have at least one row and one column";
                JOptionPane.showMessageDialog(this, message);
            }
        }
        catch(NumberFormatException e){
                String message = "Rows and Columns must be valid numbers";
                JOptionPane.showMessageDialog(this, message);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getMazeType() {
        return type;
    }

    boolean isCanceled() {
        return canceled;
    }
}

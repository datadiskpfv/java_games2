package uk.co.datadisk.mazegenerator;

import uk.co.datadisk.mycomponents.TitleLabel;

import javax.swing.*;
import java.awt.*;

public class MazeGenerator extends JFrame {

    private static final long serialVersionUID = 5501197773139684949L;

    private int rows = 5;
    private int cols = 5;

    private Cell[][] cell = new Cell[rows][cols];

    TitleLabel titleLabel = new TitleLabel("Maze");

    public MazeGenerator() {

        initGUI();

        setTitle("Maze Generator");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        add(titleLabel, BorderLayout.PAGE_START);

        Cell cell = new Cell();
        add(cell, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
            System.out.println("Error with UIManager");
        }

        EventQueue.invokeLater(MazeGenerator::new);
    }
}

package uk.co.datadisk.mazegenerator;

import uk.co.datadisk.mycomponents.TitleLabel;

import javax.swing.*;
import java.awt.*;

public class MazeGenerator extends JFrame {

    private static final long serialVersionUID = 5501197773139684949L;

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

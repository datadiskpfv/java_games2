package uk.co.datadisk.watchyourstep;

import java.awt.*;
import java.lang.reflect.Array;
import javax.swing.*;

import uk.co.datadisk.mycomponents.TitleLabel;

public class WatchYourStep extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final int GRIDSIZE = 10;
    private static final int NUMBEROFHOLES = 10;
    private TerrainButton terrain[][] = new TerrainButton[GRIDSIZE][NUMBEROFHOLES];
    private int totalRevealed = 0;

    private WatchYourStep() {
        initGUI();
        setTitle("Watch Your Step");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        TitleLabel titleLabel = new TitleLabel("Watch Your Step");
        add(titleLabel, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
        add(centerPanel, BorderLayout.CENTER);

        for (int row = 0; row < GRIDSIZE; row++) {
            for (int col = 0; col < GRIDSIZE; col++) {
              terrain[row][col] = new TerrainButton(row, col);
              centerPanel.add(terrain[row][col]);
            }
        }
    }

    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
            System.out.println("Error with UIManager");
        }

        EventQueue.invokeLater(WatchYourStep::new);
    }
}
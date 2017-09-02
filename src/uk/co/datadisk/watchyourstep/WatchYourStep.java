package uk.co.datadisk.watchyourstep;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Random;
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
        setHoles();
        setTitle("Watch Your Step");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setHoles() {
        Random rand = new Random();
        for (int i = 0; i < NUMBEROFHOLES; i++) {
            int pickRow = rand.nextInt(GRIDSIZE);
            int pickCol = rand.nextInt(GRIDSIZE);

            while (terrain[pickRow][pickCol].hasHole()) {
                pickRow = rand.nextInt(GRIDSIZE);
                pickCol = rand.nextInt(GRIDSIZE);
            }
            terrain[pickRow][pickCol].setHole(true);
            addToNeighborsHoleCount(pickRow, pickCol);
            //terrain[pickRow][pickCol].reveal(true);
        }
    }

    private void addToNeighborsHoleCount(int row, int col) {
        addToHoleCount(row, col-1);
        addToHoleCount(row, col+1);
        addToHoleCount(row+1, col);
        addToHoleCount(row-1, col);
        addToHoleCount(row+1, col-1);
        addToHoleCount(row+1, col+1);
        addToHoleCount(row-1, col-1);
        addToHoleCount(row-1, col+1);
    }

    private void addToHoleCount(int row, int col) {
        if (row > -1 && row < GRIDSIZE && col > -1 && col < GRIDSIZE) {
            terrain[row][col].increaseHoleCount();
            //terrain[row][col].reveal(true);
        }
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
              terrain[row][col].addActionListener((new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      TerrainButton button = (TerrainButton) e.getSource();
                      int row = button.getRow();
                      int col = button.getCol();
                      clickedTerrain(row, col);
                  }
              }));
              centerPanel.add(terrain[row][col]);
            }
        }
    }

    private void clickedTerrain(int row, int col) {
        if (terrain[row][col].hasHole()){
            String message = "You stepped on a hole, you lose, you you want to play again? ";
            promptForNewGame(message);
        } else {
            check(row, col);
            checkNeighbours(row, col);
            System.out.println("Checking if won totalRevealed: " + Integer.toString(totalRevealed) + " comp: " + Integer.toString(GRIDSIZE*GRIDSIZE-NUMBEROFHOLES));
            if (totalRevealed == GRIDSIZE*GRIDSIZE-NUMBEROFHOLES) {
                String message = "Congratulations you won!!, do you want to play again? ";
                promptForNewGame(message);
            }
        }
    }

    private void promptForNewGame(String message) {
        showHoles();
        int option = JOptionPane.showConfirmDialog(this, message, "Play Again?", JOptionPane.YES_NO_OPTION);
        if ( option == JOptionPane.YES_OPTION) {
            newGame();
        } else {
            System.exit(0);
        }
    }

    private void showHoles() {
        for (int row = 0; row < GRIDSIZE; row++) {
            for (int col = 0; col < GRIDSIZE; col++) {
                if(terrain[row][col].hasHole()){
                    terrain[row][col].reveal(true);
                }
            }
        }
    }

    private void newGame() {
        for (int row = 0; row < GRIDSIZE; row++) {
            for (int col = 0; col < GRIDSIZE; col++) {
               terrain[row][col].reset();
            }
        }
        setHoles();
        totalRevealed = 0;
    }

    private void check(int row, int col) {
        if (row> -1 && row < GRIDSIZE && col> -1 && col < GRIDSIZE && !terrain[row][col].isRevealed() && !terrain[row][col].hasHole()) {
            terrain[row][col].reveal(true);
            totalRevealed++;
            if(!terrain[row][col].isNextToHoles()){
                checkNeighbours(row,col);
            }
        }
    }

    private void checkNeighbours(int row, int col) {
        check(row, col-1);
        check(row, col+1);
        check(row+1, col);
        check(row-1, col);
        check(row+1, col-1);
        check(row+1, col+1);
        check(row-1, col-1);
        check(row-1, col+1);
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
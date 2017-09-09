package uk.co.datadisk.mazegenerator;

import uk.co.datadisk.mycomponents.TitleLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class MazeGenerator extends JFrame {

    private static final long serialVersionUID = 5501197773139684949L;

    private int rows = 5;
    private int cols = 5;

    private int row = 0;
    private int col = 0;
    private int endRow = rows-1;
    private int endCol = col-1;

    private Cell[][] cell = new Cell[rows][cols];

    private TitleLabel titleLabel = new TitleLabel("Maze");
    private JPanel mazePanel = new JPanel();

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

        // center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.BLACK);
        add(centerPanel, BorderLayout.CENTER);

        // maze panel
        newMaze();
        centerPanel.add(mazePanel);

        // button panel

        // listeners
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
               int keyCode = e.getKeyCode();
               moveBall(keyCode);
            }
        });
    }

    private void moveTo(int nextRow, int nextCol, int firstDirection, int secondDirection) {
        cell[row][col].setCurrent(false);
        cell[row][col].addPath(firstDirection);
        row = nextRow;
        col = nextCol;
        cell[row][col].setCurrent(true);
        cell[row][col].addPath(secondDirection);

    }

    private void moveBall(int direction) {
        switch(direction) {
            case KeyEvent.VK_UP:
                // move up if this cell does not have a top wall
                if(!cell[row][col].isWall(Cell.TOP)) {
                    moveTo(row-1, col, Cell.TOP, Cell.BOTTOM);
                }
                break;
            case KeyEvent.VK_DOWN:
                // move up if this cell does not have a top wall
                if(!cell[row][col].isWall(Cell.BOTTOM)) {
                    moveTo(row+1, col, Cell.BOTTOM, Cell.TOP);
                }
                break;
            case KeyEvent.VK_LEFT:
                // move up if this cell does not have a top wall
                if(!cell[row][col].isWall(Cell.LEFT)) {
                    moveTo(row, col-1, Cell.LEFT, Cell.RIGHT);
                }
                break;
            case KeyEvent.VK_RIGHT:
                // move up if this cell does not have a top wall
                if(!cell[row][col].isWall(Cell.RIGHT)) {
                    moveTo(row, col+1, Cell.RIGHT, Cell.LEFT);
                }
                break;
        }
    }

    private void newMaze() {
        mazePanel.setLayout(new GridLayout(rows, cols));
        cell = new Cell[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cell[r][c] = new Cell(r, c);
                mazePanel.add(cell[r][c]);
            }
        }
        generateMaze();

        row = 0;
        col = 0;
        endRow = rows-1;
        endCol = cols-1;
        cell[row][col].setCurrent(true);
        cell[endRow][endCol].setEnd(true);
    }

    private void generateMaze() {
        ArrayList<Cell> tryLaterCell = new ArrayList<>();
        int totalCells = rows * cols;
        int visitedCells = 1;

        // start at a random cell
        Random rand = new Random();
        int r = rand.nextInt(rows);
        int c = rand.nextInt(cols);

        // while not all cells have yet been visited
        while(visitedCells < totalCells) {
            // find all neighbours with all walls intact
            ArrayList<Cell> neighbours = new ArrayList<>();
            if (isAvailable(r - 1, c)) {
                neighbours.add(cell[r - 1][c]);
            }
            if (isAvailable(r + 1, c)) {
                neighbours.add(cell[r + 1][c]);
            }
            if (isAvailable(r, c - 1)) {
                neighbours.add(cell[r][c - 1]);
            }
            if (isAvailable(r, c + 1)) {
                neighbours.add(cell[r][c + 1]);
            }
            // if one or more found
            if (neighbours.size() > 0) {
                // if more than one found, add this
                // cell to the list to try again
                if (neighbours.size() >  1) {
                    tryLaterCell.add(cell[r][c]);
                }
                // pick neighbour and remove the wall
                int pick = rand.nextInt(neighbours.size());
                Cell neighbour = neighbours.get(pick);
                cell[r][c].openTo(neighbour);
                // go to the neighbour and increment
                // the number visited
                r = neighbour.getRow();
                c = neighbour.getCol();
                visitedCells++;
            } else {
                // if none was found, go to one of the
                // cells that was saved to try later
                Cell nextCell = tryLaterCell.remove(0);
                r = nextCell.getRow();
                c = nextCell.getCol();
            }
        }
    }

    private boolean isAvailable(int r, int c) {
        boolean available = false;
        if (r >= 0 && r < rows && c >=0 && c < cols && cell[r][c].hasAllWalls()) {
            available = true;
        }
        return available;
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

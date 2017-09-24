package uk.co.datadisk.mazegenerator;

import uk.co.datadisk.mycomponents.TitleLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class MazeGenerator extends JFrame {

    private static final long serialVersionUID = 5501197773139684949L;

    public static int TYPE_MAZE = 0;
    public static int TYPE_ANTIMAZE = 1;
    private int type = TYPE_MAZE;

    private int rows = 30;
    private int cols = 20;

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
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        add(buttonPanel, BorderLayout.PAGE_END);

        JButton newMazeButton = new JButton("New Maze");
        newMazeButton.setFocusable(false);
        newMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newMaze();
            }
        });
        buttonPanel.add(newMazeButton);

        JButton optionsButton = new JButton("Options");
        newMazeButton.setFocusable(false);
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeOptions();
            }
        });
        buttonPanel.add(optionsButton);

        // listeners
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
               int keyCode = e.getKeyCode();
               moveBall(keyCode);
            }
        });
    }

    private void changeOptions() {
        OptionsDialog dialog = new OptionsDialog(rows, cols, type);
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        if (!dialog.isCanceled()) {
            rows = dialog.getRows();
            cols = dialog.getCols();
            type = dialog.getMazeType();

            newMaze();
        }
    }

    private void moveTo(int nextRow, int nextCol, int firstDirection, int secondDirection) {
        cell[row][col].setCurrent(false);
        cell[row][col].addPath(firstDirection);
        row = nextRow;
        col = nextCol;
        cell[row][col].setCurrent(true);
        cell[row][col].addPath(secondDirection);
        System.out.println("Current: " + row + "," + col);

    }

    private void moveBall(int direction) {
        switch(direction) {
            case KeyEvent.VK_UP:
                // move up if this cell does not have a top wall
                if(!cell[row][col].isWall(Cell.TOP)) {
                    moveTo(row-1, col, Cell.TOP, Cell.BOTTOM);
                    // move up more if this is a long column
                    while(!cell[row][col].isWall(Cell.TOP) && cell[row][col].isWall(Cell.LEFT) && cell[row][col].isWall(Cell.RIGHT)) {
                        moveTo(row-1, col, Cell.TOP, Cell.BOTTOM);
                    }
                }
                break;
            case KeyEvent.VK_DOWN:
                // move up if this cell does not have a top wall
                if(!cell[row][col].isWall(Cell.BOTTOM)) {
                    moveTo(row+1, col, Cell.BOTTOM, Cell.TOP);
                    // move up more if this is a long column
                    while(!cell[row][col].isWall(Cell.BOTTOM) && cell[row][col].isWall(Cell.LEFT) && cell[row][col].isWall(Cell.RIGHT)) {
                        moveTo(row+1, col, Cell.BOTTOM, Cell.TOP);
                    }
                }
                break;
            case KeyEvent.VK_LEFT:
                // move up if this cell does not have a top wall
                if(!cell[row][col].isWall(Cell.LEFT)) {
                    moveTo(row, col-1, Cell.LEFT, Cell.RIGHT);
                    // move up more if this is a long column
                    while(!cell[row][col].isWall(Cell.LEFT) && cell[row][col].isWall(Cell.BOTTOM) && cell[row][col].isWall(Cell.TOP)) {
                        moveTo(row, col-1, Cell.LEFT, Cell.RIGHT);
                    }
                }
                break;
            case KeyEvent.VK_RIGHT:
                // move up if this cell does not have a top wall
                if(!cell[row][col].isWall(Cell.RIGHT)) {
                    moveTo(row, col+1, Cell.RIGHT, Cell.LEFT);
                    // move up more if this is a long column
                    while(!cell[row][col].isWall(Cell.RIGHT) && cell[row][col].isWall(Cell.BOTTOM) && cell[row][col].isWall(Cell.TOP)) {
                        moveTo(row, col+1, Cell.RIGHT, Cell.LEFT);
                    }
                }
                break;
        }
        // puzzle solved
        if (row == endRow && col == endCol) {
            String message = "Congratulations you have won!!!!!";
            JOptionPane.showMessageDialog(this, message);
        }
    }

    private void newMaze() {
        if (type == TYPE_MAZE) {
            titleLabel.setText("Maze");
        } else {
            titleLabel.setText("Anti-Maze");
        }
        mazePanel.removeAll();
        mazePanel.setLayout(new GridLayout(rows, cols));
        //cell = new Cell[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cell[r][c] = new Cell(r, c, type);
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
        mazePanel.revalidate();
        pack();
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
            System.out.println("Row: " + r + "  Col: " + c);

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
                    System.out.println("neightbours greater than 1");
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
                System.out.println("visitedCells: " + visitedCells);
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

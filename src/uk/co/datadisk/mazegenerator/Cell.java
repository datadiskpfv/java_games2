package uk.co.datadisk.mazegenerator;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {

    private static final long serialVersionUID = -1001811519651371753L;

    private static final int SIZE = 20;

    public static final int TOP = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;

    private int row = -1;
    private int col = -1;
    private int type = MazeGenerator.TYPE_MAZE;

    private boolean[] wall = {true, true, true, true};
    private boolean[] path = {false, false, false, false};

    private boolean current = false;
    private boolean end = false;

    public Cell(int row, int col, int type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isWall(int index) {
        return wall[index];
    }

    public boolean hasAllWalls() {
        boolean allWalls = wall[0] && wall[1] && wall[2] && wall[3];
        return allWalls;
    }

    public void removeWall(int w) {
        wall[w] = false;
        repaint();
    }

    public void openTo(Cell neighbour) {
        if (row < neighbour.getRow()) {
            removeWall(BOTTOM);
            neighbour.removeWall(TOP);
        } else if (row > neighbour.getRow()) {
            removeWall(TOP);
            neighbour.removeWall(BOTTOM);
        } else if (col < neighbour.getCol()) {
            removeWall(RIGHT);
            neighbour.removeWall(LEFT);
        } else if (col > neighbour.getCol()) {
            removeWall(LEFT);
            neighbour.removeWall(RIGHT);
        }
    }

    public void paintComponent(Graphics g) {
        // draw the background
        g.setColor(Color.WHITE);
        g.fillRect(0,0, SIZE, SIZE);
        g.setColor(Color.BLACK);

        // draw the walls, for either normal maze or anti-maze
        if (type == MazeGenerator.TYPE_MAZE) {
            if (isWall(TOP)) {
                g.drawLine(0, 0, SIZE, 0);
            }
            if (isWall(LEFT)) {
                g.drawLine(0, 0, 0, SIZE);
            }
        } else {
            if (!isWall(TOP)) {
                g.drawLine(0, 0, SIZE, 0);
            }
            if (!isWall(LEFT)) {
                g.drawLine(0, 0, 0, SIZE);
            }
        }

        // draw the path
        g.setColor(Color.GREEN);
        if(path[TOP]) {
            g.drawLine(SIZE/2, 0, SIZE/2, SIZE/2);
        }
        if(path[BOTTOM]) {
            g.drawLine(SIZE/2, SIZE, SIZE/2, SIZE/2);
        }
        if(path[LEFT]) {
            g.drawLine(0, SIZE/2, SIZE/2, SIZE/2);
        }
        if(path[RIGHT]) {
            g.drawLine(SIZE, SIZE/2, SIZE/2, SIZE/2);
        }

        // draw the balls
        if(current) {
            g.setColor(Color.GREEN);
            g.fillOval(3,3, SIZE-6, SIZE-6);
        } else if (end) {
            g.setColor(Color.RED);
            g.fillOval(3,3, SIZE-6, SIZE-6);
        }
    }

    public void setCurrent(boolean current) {
        this.current = current;
        repaint();
    }

    public void setEnd(boolean end) {
        this.end = end;
        repaint();
    }

    public void addPath(int side) {
        path[side] = true;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = new Dimension(SIZE,SIZE);
        return size;
    }
}

package uk.co.datadisk.mazegenerator;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;

public class Cell extends JPanel {

    private static final long serialVersionUID = -1001811519651371753L;

    private static final int SIZE = 20;

    private static final int TOP = 0;
    private static final int RIGHT = 1;
    private static final int BOTTOM = 2;
    private static final int LEFT = 3;

    private int row = -1;
    private int col = -1;

    private boolean[] wall = {true, true, true, true};

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
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


    public void paintComponent(Graphics g) {
        // draw the background
        g.setColor(Color.WHITE);
        g.fillRect(0,0, SIZE, SIZE);
        g.setColor(Color.BLACK);

        // draw the walls
        if(isWall(0)) {
            g.drawLine(0,0, SIZE, 0);
        }

        if(isWall(3)) {
            g.drawLine(0,0, 0, SIZE);
        }
        // draw the path

        // draw the balls
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = new Dimension(SIZE,SIZE);
        return size;
    }
}

package uk.co.datadisk.watchyourstep;

import javax.swing.*;
import java.awt.*;

public class TerrainButton extends JButton {
    private static final long serialVersionUID = 1L;

    private static final int SIZE = 50;
    private int row = 0;
    private int col = 0;
    private int nextToHoles = 0;

    private boolean hole = false;
    private boolean revealed = false;

    public TerrainButton(int row, int col) {
        this.row = row;
        this.col = col;

        Dimension size = new Dimension(SIZE, SIZE);
        setPreferredSize(size);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean hasHole() {
        return hole;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setHole(boolean hasHole) {
        this.hole = hasHole;
    }

    public void increaseHoleCount() {
        nextToHoles++;
    }

    public boolean isNextToHoles() {
         return nextToHoles > 0;
    }

    public void reveal(boolean reveal) {
        this.revealed = reveal;

        if (revealed) {
            if (hasHole()) {
                setBackground(Color.BLACK);
            } else  {
                setBackground(Color.CYAN);
                if(nextToHoles>0) {
                    setText("" + nextToHoles);
                }
            }
        } else {
            setBackground(null);
            setText("");
        }
    }

    public void reset() {
        hole = false;
        revealed = false;
        nextToHoles = 0;
        setText("");
        setBackground(null);

    }
}
package uk.co.datadisk.framed;

import javax.swing.*;
import java.awt.*;

public class LightButton extends JButton {

    private static final long serialVersionUID = 464901630432294048L;

    private static final int MAXSIZE = 50;
    private int row = 0;
    private int col = 0;

    public LightButton(int row, int col) {
        this.row = row;
        this.col = col;
        setBackground(Color.BLACK);
        Dimension size = new Dimension(MAXSIZE, MAXSIZE);
        setPreferredSize(size);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void turnOn() {
        setBackground(Color.RED);
    }

    public void turnOff() {
        setBackground(Color.BLACK);
    }

    public boolean isLit() {
        Color color = getBackground();
        System.out.println("COLOR: " + color);
        boolean isLit = color.equals(color.RED);
        return isLit;
    }

    public void toggle() {
        if( isLit() ) {
            turnOff();
        } else {
            turnOn();
        }
    }
}
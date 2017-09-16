package uk.co.datadisk.greedy;

import javax.swing.*;
import java.awt.*;

public class Die extends JPanel {

    private static final long serialVersionUID = 2890986310733853834L;

    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    private static final int AVAILABLE = 0;
    private static final int SELECTED = 1;
    private static final int HELD = 2;

    private int value = 1;
    private int state = AVAILABLE;

    public Dimension getPreferredSize() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        return size;
    }

    public void paintComponent(Graphics g) {
        // fill the background
        switch (state) {
            case AVAILABLE:
                g.setColor(Color.WHITE);
                break;
            case SELECTED:
                g.setColor(Color.RED);
                break;
            case HELD:
                g.setColor(Color.LIGHT_GRAY);
                break;
        }
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // draw a border
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
        // draw the dots
    }

    private void drawDot(Graphics g, int x, int y) {
        g.fillOval(x - 5, y - 5, 10, 10 );
    }
}

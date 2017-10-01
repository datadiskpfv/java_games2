package uk.co.datadisk.bakersdozen;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {

    private static final long serialVersionUID = -8817160256895838560L;

    private static final int CARDWIDTH = Deck.getCARDWIDTH();
    private static final int CARDHEIGHT = Deck.getCARDHEIGHT();

    private static final int SPACING = 4;
    private static final int MARGIN = 10;
    private static final int WIDTH = 13 * CARDWIDTH + 12 * SPACING + 2 * MARGIN;
    private static final int HEIGHT = 9 * CARDHEIGHT +  3 * MARGIN;

    private Deck deck;
    private Card card;

    public TablePanel() {
        deck = new Deck();
        deal();
    }

    private void deal() {
        card = deck.deal();
        card.setXY(10,10);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        return size;
    }

    @Override
    public void paintComponent(Graphics g) {
        card.draw(g);
    }
}

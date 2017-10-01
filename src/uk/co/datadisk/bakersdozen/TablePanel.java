package uk.co.datadisk.bakersdozen;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class TablePanel extends JPanel {

    private static final long serialVersionUID = -8817160256895838560L;

    private static final int CARDWIDTH = Deck.getCARDWIDTH();
    private static final int CARDHEIGHT = Deck.getCARDHEIGHT();

    private static final int SPACING = 4;
    private static final int MARGIN = 10;
    private static final int WIDTH = 13 * CARDWIDTH + 12 * SPACING + 2 * MARGIN;
    private static final int HEIGHT = 9 * CARDHEIGHT +  3 * MARGIN;

    private static final int FOUNDATIONX = WIDTH/2 - (4*CARDHEIGHT + 3*SPACING) / 2;
    private static final int FOUNDATIONY = MARGIN;
    private static final int BOARDX = MARGIN;
    private static final int BOARDY = CARDHEIGHT + MARGIN + MARGIN;
    private static final int OVERLAP = (int) (CARDHEIGHT*.65);

    private Deck deck;
    private Deck savedDeck = new Deck();
    private CardStack[] foundation = new CardStack[4];
    private CardStack[] column = new CardStack[13];

    private Card movingCard;
    private int mouseX = 0;
    private int mouseY = 0;
    private int fromCol = 0;

    public TablePanel() {
        int x = FOUNDATIONX;
        int y = FOUNDATIONY;

        for (int i = 0; i < 4; i++) {
            foundation[i] = new CardStack(x,y,0);
            x += CARDWIDTH + SPACING;
        }

        x = BOARDX;
        y = BOARDY;

        for (int i = 0; i < 13; i++) {
            column[i] = new CardStack(x,y,OVERLAP);
            x += CARDWIDTH + SPACING;
        }

        newGame();

        // mouse listeners
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                clicked(x, y);
            }

            public void mouseReleased(MouseEvent e){
                int x = e.getX();
                int y = e.getX();
                released(x, y);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                dragged(x,y);
            }
        });
    }

    public void newGame() {
        deck = new Deck();
        savedDeck.copyFrom(deck);
        deal();
    }

    public void replay() {
        deck.copyFrom(savedDeck);
        deal();
    }

    private void released(int x, int y){
        if(movingCard!=null) {
            boolean validMove = false;

            // play on foundation
            for (int i = 0; i < 4 && !validMove; i++) {
                int foundationx = foundation[i].getX();
                int foundationy = foundation[i].getY();
                if(movingCard.isNear(foundationx,foundationy)){

                    // empty foundation?
                    if(foundation[i].size() == 0){
                        if(movingCard.getValue() ==0){
                            validMove = true;
                            foundation[i].add(movingCard);
                            movingCard = null;
                        }
                    }
                    // otherwise, valid suit and rank?
                    else {
                        Card topCard = foundation[i].getLast();
                        if(movingCard.getSuit()==topCard.getSuit() && movingCard.getValue()==topCard.getValue()+1){
                            validMove = true;
                            foundation[i].add(movingCard);
                            movingCard = null;
                            isGameOver();
                        }
                    }
                }
            }

            // play on a column?
            for (int i = 0; i < 13 && !validMove; i++) {
                if(column[i].size()>0){
                    Card card = column[i].getLast();
                    //System.out.println("in play on column: " + movingCard.isNear(card));
                    if(movingCard.isNear(card) && movingCard.getValue() == card.getValue()-1){
                        System.out.println("inside last if - play on column");
                        validMove = true;
                        column[i].add(movingCard);
                        movingCard = null;
                    }
                }
            }

            // otherwise, return to column
            if(!validMove) {
                column[fromCol].add(movingCard);
                movingCard = null;
            }
            repaint();
        }


    }

    private void isGameOver() {
        boolean gameOver = true;
        for (int i = 0; i < 4 && gameOver; i++) {
            if (foundation[i].size() != 13){
                gameOver = false;
            }
        }

        if(gameOver) {
            String message = "You won! Do you want to play again?";
            int option = JOptionPane.showConfirmDialog(this, message, "Play again?", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                newGame();
            } else {
                System.exit(0);
            }
        }
    }

    private void dragged(int x, int y) {
        if(movingCard!=null) {
            int changeX = x - mouseX;
            int changeY = y - mouseY;
            movingCard.addToXY(changeX, changeY);
            mouseX = x;
            mouseY = y;
            repaint();
        }
    }

    private void clicked(int x, int y) {
        movingCard = null;
        for (int col = 0; col < 13 && movingCard == null; col++) {
            if (column[col].size()>0) {
                Card card = column[col].getLast();
                if(card.contains(x,y)){
                    movingCard = card;
                    mouseX = x;
                    mouseY = y;
                    column[col].removeLast();
                    fromCol = col;
                }
            }
        }
    }

    private void deal() {
        for (int i = 0; i < 4; i++) {
            foundation[i].clear();
        }
        for (int i = 0; i < 13; i++) {
            column[i].clear();
        }
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 13; col++) {
                Card card = deck.deal();
                if(card.getValue() == 12) {
                    column[col].addToBegining(card);
                } else {
                    column[col].add(card);
                }
            }
        }
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        return size;
    }

    @Override
    public void paintComponent(Graphics g) {
        //draw background
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // draw foundation
        for (int i = 0; i < 4; i++) {
            if (foundation[i].size()>0){
                foundation[i].draw(g);
            } else {
                int x = foundation[i].getX();
                int y = foundation[i].getY();
                Card.drawOutline(g,x,y);
            }
        }

        // draw board
        for (int i = 0; i < 13; i++) {
            column[i].draw(g);
        }

        // draw moving card
        if (movingCard != null) {
            movingCard.draw(g);
        }
    }
}

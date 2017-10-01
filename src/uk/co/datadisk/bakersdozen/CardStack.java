package uk.co.datadisk.bakersdozen;

import java.awt.*;
import java.util.ArrayList;

public class CardStack {

    ArrayList<Card> cards = new ArrayList<>();
    private int stackX = 0;
    private int stackY = 0;
    private int overlap = 0;

    public CardStack(int stackX, int stackY, int overlap) {
        this.stackX = stackX;
        this.stackY = stackY;
        this.overlap = overlap;
    }

    public void add(Card card) {
        int cardx = stackX;
        int cardy = stackY+overlap*cards.size();
        card.setXY(cardx, cardy);
        cards.add(card);
    }

    public void addToBegining(Card card) {
        card.setXY(stackX, stackY);
        cards.add(0, card);

        for (int i = 1; i < cards.size(); i++) {
           Card nextCard = cards.get(i);
           nextCard.addToXY(0, overlap);
        }
    }

    public void draw(Graphics g) {
        if (cards.size() > 0 && overlap == 0) {
            int lastIndex = cards.size() - 1;
            Card card = cards.get(lastIndex);
            card.draw(g);
        } else {
            for (int i = 0; i < cards.size(); i++) {
                Card card = cards.get(i);
                card.draw(g);
            }
        }
    }

    public int size() {
        return cards.size();
    }

    public int getX() {
        return stackX;
    }

    public int getY() {
        return stackY;
    }

    public void clear() {
        cards.clear();
    }

    public Card getLast() {
        int index = cards.size() -1;
        return cards.get(index);
    }

    public void removeLast() {
        int index = cards.size()-1;
        cards.remove(index);
    }
}

package uk.co.datadisk.bakersdozen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private static final String RANKS[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    // Unicodes for heart, diamond, club and spade
    private static final String SUITSYMBOLS[] = { "\u2665", "\u2666", "\u2660", "\u2663"};

    private static final int VALUES[] = {0,1,2,3,4,5,6,7,8,9,10,11,12};

    private static final int CARDWIDTH = 30;
    private static final int CARDHEIGHT = 50;

    private static final String FILENAME = "/images/cards.png";

    private ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        Random rand = new Random();
        try {
            InputStream input = getClass().getResourceAsStream(FILENAME);
            BufferedImage cardsImage = ImageIO.read(input);
            for (int suit = 0; suit < SUITSYMBOLS.length; suit++) {
                for (int rank = 0; rank < RANKS.length; rank++) {
                    int pos = 0;
                    if (cards.size() > 0){
                        pos = rand.nextInt(cards.size()+1);
                    }
                    int x = rank * CARDWIDTH;
                    int y = suit * CARDHEIGHT;
                    Image image = cardsImage.getSubimage(x, y, CARDWIDTH, CARDHEIGHT);
                    Card card = new Card(RANKS[rank], suit, VALUES[rank], image);
                    cards.add(pos, card);
                }
            }

        } catch (IOException e) {
            String message = FILENAME + " could not be opened";
            JOptionPane.showMessageDialog(null, message);
        }
    }

    public static String[] getRANKS() {
        return RANKS;
    }

    public static String[] getSUITSYMBOLS() {
        return SUITSYMBOLS;
    }

    public static int getCARDWIDTH() {
        return CARDWIDTH;
    }

    public static int getCARDHEIGHT() {
        return CARDHEIGHT;
    }

    public Card deal() {
        Card card = cards.remove(0);
        return card;
    }

    public int size() {
        return cards.size();
    }

    public Card get(int index){
        Card card = cards.get(index);
        return card;
    }

    public void copyFrom(Deck deck){
        cards.clear();
        for (int i = 0; i < deck.size(); i++) {
            Card card = deck.get(i);
            cards.add(card);
        }
    }
}

package uk.co.datadisk.wordbuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LetterPanel extends JPanel {

    private static final long serialVersionUID = -6131110234638793166L;

    private static final Color BROWN = new Color(49, 22, 3);
    private static final String IMAGENAME = "WoodTile.jpg";

    private String letter = "";
    private int points = -1;
    private int column = -1;

    private BufferedImage image = null;
    // private int size = 40;
    private int size = 50;

    private Font smallFont = new Font(Font.DIALOG, Font.BOLD, 12);
    private Font bigFont = new Font(Font.DIALOG, Font.BOLD, 30);
    private FontMetrics bigFM;
    private FontMetrics smallFM;

    public LetterPanel(String letter, int points) {
        this.letter = letter;
        this.points = points;

        initPanel();
    }

    public LetterPanel() {
        initPanel();
    }

    private void initPanel() {
        if ( image == null ) {
            try {
                image = ImageIO.read(new File(IMAGENAME));
            } catch (IOException e) {
                String message = IMAGENAME + " could not be opened";
                JOptionPane.showMessageDialog(null, message);
            }
        }

        bigFM = getFontMetrics(bigFont);
        smallFM = getFontMetrics(smallFont);
    }

    public void paintComponent(Graphics g) {
        if ( letter.length() == 0) {
            g.setColor(BROWN);
            g.fillRect(0,0, size, size);
        } else {
            if(image == null) {
                g.setColor(Color.WHITE);
                g.fillRect(0,0, size, size);
            } else {
                g.drawImage(image,0,0,this);
            }
            g.setColor(Color.BLACK);
            g.drawRect(0,0, size-1, size-1);

            g.setFont(bigFont);
            int letterWidth = bigFM.stringWidth(""+letter);
            int x = (size-letterWidth)/2;
            int y = size * 3 / 4;
            g.drawString(letter, x, y);

            g.setFont(smallFont);
            letterWidth = smallFM.stringWidth(""+points);
            x = size - letterWidth - 2;
            y = size * 17 / 20;
            g.drawString("" + points, x, y);
        }
    }

    public Dimension getPreferredSize() {
        Dimension dimension = new Dimension(size, size);
        return dimension;
    }

    public String getLetter() {
        return letter;
    }

    public int getPoints() {
        return points;
    }

    public int getColumn() {
        return column;
    }

    public int getPanelSize() {
        return size;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setEmpty() {
        letter = "";
        points = -1;
        repaint();
    }

    public boolean hasNoLetter() {
        return (points == -1);
    }

    public void copy(LetterPanel letterPanel2) {
        letter = letterPanel2.getLetter();
        points = letterPanel2.getPoints();
        column = letterPanel2.getColumn();
        repaint();
    }
}

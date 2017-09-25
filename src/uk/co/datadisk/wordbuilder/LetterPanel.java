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

    private BufferedImage image = null;
    private int size = 40;

    private Font smallFont = new Font(Font.DIALOG, Font.BOLD, 12);
    private Font largeFont = new Font(Font.DIALOG, Font.BOLD, 30);

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
        }
    }
}

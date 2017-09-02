package uk.co.datadisk.slidingtiles;

import uk.co.datadisk.mycomponents.TitleLabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SlidingTiles extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final String FILENAME = "slidingTilesImage.jpg";

    private int tileSize = 50;
    private int gridSize = 4;

    private BufferedImage image = null;

    private TileButton tile[][] = new TileButton[gridSize][gridSize];

    JPanel centerPanel = new JPanel();

    private SlidingTiles() {
        try {
            image = ImageIO.read(new File(FILENAME));

            TileButton.setTileSizeAndMaxTiles(tileSize, gridSize*gridSize);
            initGUI();
            setTitle("Sliding Tiles");
            setResizable(false);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
        catch (IOException e) {
            String message = "File " + FILENAME + " cannot be open ";
            JOptionPane.showMessageDialog(this, message);
        }
    }

    private void initGUI() {
        TitleLabel titleLabel = new TitleLabel("Sliding Tiles");
        add(titleLabel, BorderLayout.PAGE_START);

        // Main Panel
        divideImage();

        // button panel
    }

    private void divideImage() {
        centerPanel.setLayout(new GridLayout(gridSize, gridSize));
        add(centerPanel, BorderLayout.CENTER);

        int imageId = 0;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int x = col*tileSize;
                int y = row*tileSize;

                BufferedImage subImage = image.getSubimage(x, y, tileSize, tileSize);
                ImageIcon imageIcon = new ImageIcon(subImage);
                tile[row][col] = new TileButton(imageIcon, imageId, row, col);
                centerPanel.add(tile[row][col]);
                imageId++;
            }
        }
    }

    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
            System.out.println("Error with UIManager");
        }

        EventQueue.invokeLater(SlidingTiles::new);
    }
}

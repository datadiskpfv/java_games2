package uk.co.datadisk.slidingtiles;

import uk.co.datadisk.mycomponents.TitleLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SlidingTiles extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int GRIDSIZE = 4;
    private static final String FILENAME = "slidingTilesImage.jpg";

    private int tileSize = 50;
    private int gridSize = 4;

    private BufferedImage image = null;

    private SlidingTiles() {
        TileButton.setTileSizeAndMaxTiles(gridSize, gridSize);
        initGUI();
        setTitle("Sliding Tiles");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        TitleLabel titleLabel = new TitleLabel("Sliding Tiles");
        add(titleLabel, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
        add(centerPanel, BorderLayout.CENTER);
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

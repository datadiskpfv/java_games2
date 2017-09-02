package uk.co.datadisk.slidingtiles;

import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {

    private static final long serialVersionUID = -6171173491246028404L;

    private static int tileSize = 0;
    private static int maxTiles = 0;

    private ImageIcon imageIcon;

    private int imageId = 0;
    private int row = 0;
    private int col = 0;

    public TileButton(ImageIcon imageIcon, int imageId, int row, int col) {
        this.row = row;
        this.col = col;
        setImage(imageIcon, imageId);

        setBackground(Color.WHITE);
        setBorder(null);
        Dimension size = new Dimension(tileSize, tileSize);
        setPreferredSize(size);
        setFocusPainted(false);
    }

    public void setImage(ImageIcon imageIcon, int imageId) {
        this.imageIcon = imageIcon;
        this.imageId = imageId;

        if (imageId == maxTiles-1) {
            setIcon(null);
        } else {
            setIcon(imageIcon);
        }
    }

    public ImageIcon getImage() {
        return imageIcon;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getImageId() {
        return imageId;
    }

    static void setTileSizeAndMaxTiles(int size, int max) {
        tileSize = size;
        maxTiles = max;
    }
}
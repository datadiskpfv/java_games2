package uk.co.datadisk.imageresizer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private static final long serialVersionUID = -4281533083699873039L;

    private ImageResizer imageResizer = null;
    private BufferedImage image = null;

    public ImagePanel(ImageResizer imageResizer) {
        this.imageResizer = imageResizer;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
        revalidate();
    }

    public BufferedImage getImage() {

        return image;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size;
        if( image == null) {
            size = new Dimension(640, 480);
        } else {
            int width = image.getWidth();
            int height = image.getWidth();
            size = new Dimension(width, height);
        }
        return size;

    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0, getWidth(), getHeight());
        g.drawImage(image, 0,0,this);
    }
}

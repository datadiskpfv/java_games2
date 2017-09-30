package uk.co.datadisk.imageresizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private static final long serialVersionUID = -4281533083699873039L;

    private ImageResizer imageResizer = null;
    private BufferedImage image = null;

    private int cropX = 0;
    private int cropY = 0;
    private int cropW = 0;
    private int cropH = 0;
    private int startX = 0;
    private int startY = 0;

    public ImagePanel(ImageResizer imageResizer) {
        this.imageResizer = imageResizer;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                imageClicked(x, y);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                cropDragged(x,y);
            }
        });
    }

    private void cropDragged(int x, int y) {
        if (x>startX) {
            cropW = x - startX;
        } else {
            cropW = startX -x;
            cropX = x;
        }
        if (y>startY) {
            cropH = y - startY;
        } else {
            cropH = startY - y;
            cropY = y;
        }
        imageResizer.setCropFields(cropX, cropY, cropW, cropH);
        repaint();
    }

    private void imageClicked(int x, int y) {
        startX = x;
        startY = y;
        cropX = x;
        cropY = y;
        imageResizer.setCropFields(cropX, cropY, cropW, cropH);
        repaint();
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

        g.setColor(Color.BLACK);
        g.setXORMode(Color.white);
        g.drawRect(cropX, cropY, cropW, cropH);
    }

    public void setCropX(int cropX) {
        this.cropX = cropX;
        repaint();
    }

    public void setCropY(int cropX) {
        this.cropY = cropY;
        repaint();
    }

    public void setCropW(int cropW) {
        this.cropW = cropW;
        repaint();
    }

    public void setCropH(int cropH) {
        this.cropH = cropH;
        repaint();
    }

    public void resetCrop() {
        cropX = 0;
        cropY = 0;
        cropW = 0;
        cropH = 0;
        repaint();
    }
}

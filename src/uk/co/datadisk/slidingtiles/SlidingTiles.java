package uk.co.datadisk.slidingtiles;

import uk.co.datadisk.mycomponents.TitleLabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class SlidingTiles extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final String FILENAME = "slidingTilesImage.jpg";

    private static final int IMAGESIZE = 200;

    private static final int UP    = 0;
    private static final int DOWN  = 1;
    private static final int LEFT  = 2;
    private static final int RIGHT = 3;

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

        // menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });
        fileMenu.add(openMenuItem);

        JMenu sizeMenu = new JMenu("Size");
        menuBar.add(sizeMenu);

        JMenuItem size3MenuItem = new JMenuItem("3 x 3");
        size3MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGridSize(3);
            }
        });
        sizeMenu.add(size3MenuItem);

        JMenuItem size4MenuItem = new JMenuItem("4 x 4");
        size4MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGridSize(4);
            }
        });
        sizeMenu.add(size4MenuItem);

        JMenuItem size5MenuItem = new JMenuItem("5 x 5");
        size5MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGridSize(5);
            }
        });
        sizeMenu.add(size5MenuItem);

        // title
        TitleLabel titleLabel = new TitleLabel("Sliding Tiles");
        add(titleLabel, BorderLayout.PAGE_START);

        // Main Panel
        divideImage();

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        add(buttonPanel, BorderLayout.PAGE_END);

        JButton scrambleButton = new JButton("Scramble");
        scrambleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });
        buttonPanel.add(scrambleButton);
    }

    private void setGridSize(int size) {
        gridSize = size;
        tileSize = IMAGESIZE / gridSize;
        TileButton.setTileSizeAndMaxTiles(tileSize, gridSize*gridSize);
        tile = new TileButton[gridSize][gridSize];
        divideImage();
        pack();
    }

    private void open() {
        JFileChooser chooser = new JFileChooser();
        ImageFileFilter fileFilter = new ImageFileFilter();
        chooser.setFileFilter(fileFilter);
        int option = chooser.showOpenDialog(this);
        if( option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                BufferedImage newImage = ImageIO.read(file);
                int width = newImage.getWidth();
                int height = newImage.getHeight();
                if (width < height) {
                    height = width;
                } else {
                    width = height;
                }
                Graphics g = image.getGraphics();

                // drawImage parameters
                //      new image           - the new image obtained from the file chooser
                //      x,y                 - where you want the image drawn on g (in our case 0,0)
                //      height, width       - the height and width on g (in our case 200 x 200)
                //      x, y copying from   - the x, y coordinates of the image you are copying from
                //      width, height       - the width and height you are copying from
                //      imageObserver       - the imageObserver
                g.drawImage(newImage,0, 0, IMAGESIZE, IMAGESIZE, 0, 0, width, height, this);
                g.dispose();
                divideImage();
            } catch (IOException e ) {
                String message = "File could not be opened";
                JOptionPane.showMessageDialog(this, message);
            }
        }
    }

    private void newGame() {
        int imageId = 0;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
               int x = col*tileSize;
               int y = row*tileSize;
               BufferedImage subimage = image.getSubimage(x, y, tileSize, tileSize);
               ImageIcon imageIcon = new ImageIcon(subimage);
               tile[row][col].setImage(imageIcon, imageId);
               imageId++;
            }
        }
        scramble();
    }

    private void divideImage() {
        centerPanel.setLayout(new GridLayout(gridSize, gridSize));
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.removeAll();

        int imageId = 0;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int x = col*tileSize;
                int y = row*tileSize;

                BufferedImage subImage = image.getSubimage(x, y, tileSize, tileSize);
                ImageIcon imageIcon = new ImageIcon(subImage);
                tile[row][col] = new TileButton(imageIcon, imageId, row, col);
                tile[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TileButton button = (TileButton) e.getSource();
                        clickedTile(button);
                    }
                });

                centerPanel.add(tile[row][col]);
                imageId++;
            }
        }
        centerPanel.revalidate();
        scramble();
    }

    private void scramble() {
        int openRow = gridSize-1;
        int openCol = gridSize-1;

        Random rand = new Random();

        //for (int i = 0; i < gridSize*25; i++) {
        for (int i = 0; i < gridSize*3; i++) {
            int direction = rand.nextInt(gridSize);
            switch(direction) {
                case UP:
                    if (openRow > 0 ) {
                        tile[openRow][openCol].swap(tile[openRow-1][openCol]);
                        openRow--;
                    }
                    break;
                case DOWN:
                    if (openRow < gridSize-1 ) {
                        tile[openRow][openCol].swap(tile[openRow+1][openCol]);
                        openRow++;
                    }
                    break;
                case LEFT:
                    if (openCol > 0 ) {
                        tile[openRow][openCol].swap(tile[openRow][openCol-1]);
                        openCol--;
                    }
                    break;
                case RIGHT:
                    if (openCol < gridSize-1 ) {
                        tile[openRow][openCol].swap(tile[openRow][openCol+1]);
                        openCol++;
                    }
                    break;
            }

        }
    }

    private void clickedTile(TileButton clickedTile) {
        int row = clickedTile.getRow();
        int col = clickedTile.getCol();

        if ( row > 0 && tile[row-1][col].hasNoImage()) {
            System.out.println("Move above");
            clickedTile.swap(tile[row-1][col]);
        } else if (row < gridSize-1 && tile[row+1][col].hasNoImage()) {
            System.out.println("Move below");
            clickedTile.swap(tile[row+1][col]);
        } else if (col > 0 && tile[row][col-1].hasNoImage()) {
            System.out.println("Move left");
            clickedTile.swap(tile[row][col-1]);
        } else if (col < gridSize-1 && tile[row][col+1].hasNoImage()) {
            System.out.println("Move right");
            clickedTile.swap(tile[row][col+1]);
        }

        if(imagesInOrder()) {
            tile[gridSize-1][gridSize-1].showImage();
        }
    }

    private boolean imagesInOrder() {
        int id = 0;
        boolean inOrder = true;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int currentId = tile[row][col].getImageId();
                if (currentId != id) {
                    inOrder = false;
                }
                id++;
            }
        }
        return inOrder;
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
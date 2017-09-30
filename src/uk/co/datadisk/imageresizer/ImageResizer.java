package uk.co.datadisk.imageresizer;

import uk.co.datadisk.mycomponents.TitleLabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageResizer extends JFrame {

    private static final long serialVersionUID = 2667769724702178413L;

    private static final String IMAGE_LOAD = "/images/Load16.gif";
    private static final String IMAGE_SAVE = "/images/Save16.gif";
    private static final String IMAGE_SCALE = "/images/Scale16.gif";
    private static final String IMAGE_WIDTH = "/images/Width16.gif";
    private static final String IMAGE_HEIGHT = "/images/Height16.gif";
    private static final String IMAGE_CROP = "/images/Crop16.gif";
    private static final String IMAGE_X = "/images/x16.gif";
    private static final String IMAGE_Y = "/images/y16.gif";

    private ImagePanel imagePanel = new ImagePanel(this);
    private File file = new File("");

    private double ratio = 1.0;
    private JTextField scaleWField = new JTextField("1", 5);
    private JTextField scaleHField = new JTextField("1",5);

    private JTextField cropXField = new JTextField("0",5);
    private JTextField cropYField = new JTextField("0",5);
    private JTextField cropWField = new JTextField("0",5);
    private JTextField cropHField = new JTextField("0",5);


    public ImageResizer() {
        initGUI();

        setTitle("Image Resizer");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        TitleLabel titleLabel = new TitleLabel("Image Resizer");
        add(titleLabel, BorderLayout.PAGE_START);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        //String className = this.getClass().getSimpleName();
        //URL testPath = this.getClass().getResource(className+".class");
        //System.out.println("Current Running Location is :"+testPath);

        // toolbar
        ImageIcon loadIcon = new ImageIcon(getClass().getResource(IMAGE_LOAD));
        ImageIcon saveIcon = new ImageIcon(getClass().getResource(IMAGE_SAVE));
        ImageIcon scaleIcon = new ImageIcon(getClass().getResource(IMAGE_SCALE));
        ImageIcon widthIcon = new ImageIcon(getClass().getResource(IMAGE_WIDTH));
        ImageIcon heightIcon = new ImageIcon(getClass().getResource(IMAGE_HEIGHT));
        ImageIcon cropIcon = new ImageIcon(getClass().getResource(IMAGE_CROP));
        ImageIcon xIcon = new ImageIcon(getClass().getResource(IMAGE_X));
        ImageIcon yIcon = new ImageIcon(getClass().getResource(IMAGE_Y));

        JToolBar toolBar = new JToolBar();
        mainPanel.add(toolBar, BorderLayout.PAGE_START);


        JButton loadButton = new JButton(loadIcon);
        loadButton.setToolTipText("Load Image");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
            }
        });
        toolBar.add(loadButton);

        JButton saveButton = new JButton(saveIcon);
        saveButton.setToolTipText("Save Image");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        toolBar.add(saveButton);

        toolBar.addSeparator();

        // scale options
        JButton scaleButton = new JButton(scaleIcon);
        scaleButton.setToolTipText("Scale Image");
        toolBar.add(scaleButton);

        JLabel scaleWLabel = new JLabel(widthIcon);
        toolBar.add(scaleWLabel);
        toolBar.add(scaleWField);

        JLabel scaleHLabel = new JLabel(heightIcon);
        toolBar.add(scaleHLabel);
        toolBar.add(scaleHField);
        toolBar.addSeparator();

        // crop options
        JButton cropButton = new JButton(cropIcon);
        cropButton.setToolTipText("Crop Image");
        toolBar.add(cropButton);

        JLabel cropXLabel = new JLabel(xIcon);
        toolBar.add(cropXLabel);
        toolBar.add(cropXField);

        JLabel cropYLabel = new JLabel(yIcon);
        toolBar.add(cropYLabel);
        toolBar.add(cropYField);

        JLabel cropWLabel = new JLabel(widthIcon);
        toolBar.add(cropWLabel);
        toolBar.add(cropWField);

        JLabel cropHLabel = new JLabel(heightIcon);
        toolBar.add(cropHLabel);
        toolBar.add(cropHField);



        // image panel

        JScrollPane scrollPane = new JScrollPane(imagePanel);
        mainPanel.add(scrollPane);
    }

    private void save() {
        JFileChooser chooser = new JFileChooser(file);
        chooser.setSelectedFile(file);
        int option = chooser.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            String fileName = file.getPath();
            String f = fileName.toLowerCase();
            if(!f.endsWith(".jpg")) {
                fileName += ".jpg";
                file = new File(fileName);
            }
            if ( file.exists()) {
                String message = "Are you sure you want to replace the file " + file.getPath();
                int replace = JOptionPane.showConfirmDialog(this, message, "Replace Image?", JOptionPane.YES_NO_OPTION);
                if (replace == JOptionPane.YES_OPTION) {
                    writeImage();
                }

            } else {
                writeImage();
            }
        }
    }

    private void writeImage() {
        BufferedImage image = imagePanel.getImage();
        try {
            ImageIO.write(image, "jpg", file);
        }  catch (IOException e) {
            String message = "File " + file.getPath() + " could not be saved";
            JOptionPane.showMessageDialog(this, message);
        }
    }

    private void load() {
        JFileChooser chooser = new JFileChooser(file);
        chooser.setFileFilter(new ImageFileFilter());
        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        }

        try {
            BufferedImage image = ImageIO.read(file);
            imagePanel.setImage(image);

        } catch (IOException e) {
            String message = "File " + file.getPath()+ " could not be opened";
            JOptionPane.showMessageDialog(this, message);
        }
    }

    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
            System.out.println("Error with UIManager");
        }

        EventQueue.invokeLater(ImageResizer::new);
    }
}

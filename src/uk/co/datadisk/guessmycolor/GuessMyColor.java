package uk.co.datadisk.guessmycolor;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//mport java.awt.event.ActionListener;
import java.util.Random;
import uk.co.datadisk.mycomponents.*;

public class GuessMyColor extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel samplePanel = new JPanel();
    private JPanel targetPanel = new JPanel();

    private int targetRed = 0;
    private int targetGreen = 0;
    private int targetBlue = 0;

    private int red = 0;
    private int green = 0;
    private int blue = 0;

    private GuessMyColor() {

        initGUI();

        setTitle("Guess My Color");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setSize(400, 175);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        generateTargetColor();
    }

    private void initGUI() {

        TitleLabel titleLabel = new TitleLabel("Guess My Color");

        // by default the borderlayout is Center
        //               PAGE_START (NORTH)
        // LINE_START (WEST), CENTER, LINE_END (EAST)
        //                PAGE_END (SOUTH)
        add(titleLabel, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        add(centerPanel, BorderLayout.CENTER);

        Dimension size = new Dimension(50,50);

        samplePanel.setBackground(Color.BLACK);
        samplePanel.setPreferredSize(size);
        centerPanel.add(samplePanel);

        targetPanel.setBackground(Color.CYAN);
        targetPanel.setPreferredSize(size);
        centerPanel.add(targetPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        add(buttonPanel, BorderLayout.PAGE_END);

        Font font = new Font(Font.DIALOG, Font.BOLD,18);

        JButton moreRedButton = new JButton("+");
        moreRedButton.setBackground(Color.RED);
        moreRedButton.setFont(font);
        moreRedButton.addActionListener(e -> increaseRed());
        buttonPanel.add(moreRedButton);

        JButton lessRedButton = new JButton("-");
        lessRedButton.setBackground(Color.RED);
        lessRedButton.setFont(font);
        lessRedButton.addActionListener(e -> decreaseRed());
        buttonPanel.add(lessRedButton);

        JButton moreGreenButton = new JButton("+");
        moreGreenButton.setBackground(Color.GREEN);
        moreGreenButton.setFont(font);
        moreGreenButton.addActionListener(e -> increaseGreen());
        buttonPanel.add(moreGreenButton);

        JButton lessGreenButton = new JButton("-");
        lessGreenButton.setBackground(Color.GREEN);
        lessGreenButton.setFont(font);
        lessGreenButton.addActionListener(e -> decreaseGreen());
        buttonPanel.add(lessGreenButton);

        JButton moreBlueButton = new JButton("+");
        moreBlueButton.setBackground(Color.BLUE);
        moreBlueButton.setFont(font);
        moreBlueButton.addActionListener(e -> increaseBlue());
        buttonPanel.add(moreBlueButton);

        JButton lessBlueButton = new JButton("-");
        lessBlueButton.setBackground(Color.BLUE);
        lessBlueButton.setFont(font);
        lessBlueButton.addActionListener(e -> decreaseBlue());
        buttonPanel.add(lessBlueButton);
    }

    private void generateTargetColor() {
        Random rand = new Random();
        targetRed = rand.nextInt(18) * 15;
        targetGreen = rand.nextInt(18) * 15;
        targetBlue = rand.nextInt(18) * 15;

        Color targetColor = new Color(targetRed,targetGreen,targetBlue);
        targetPanel.setBackground(targetColor);
    }

    private void updateColorSample() {
        Color color = new Color(red, green, blue);
        samplePanel.setBackground(color);
        if (red == targetRed && green == targetGreen && blue == targetBlue) {
            String message = "Congratulations you have guessed the color.";
            JOptionPane.showMessageDialog(this, message);
        }
    }

    private void increaseRed() {
        if (red <= 240 ) {
            red += 15;
            updateColorSample();
        }
    }

    private void decreaseRed() {
        if (red >= 15 ) {
            red -= 15;
            updateColorSample();
        }
    }

    private void increaseGreen() {
        if (green <= 240 ) {
            green += 15;
            updateColorSample();
        }
    }

    private void decreaseGreen() {
        if (green >= 15 ) {
            green -= 15;
            updateColorSample();
        }
    }

    private void increaseBlue() {
        if (blue <= 240 ) {
            blue += 15;
            updateColorSample();
        }
    }

    private void decreaseBlue() {
        if (blue >= 15 ) {
            blue -= 15;
            updateColorSample();
        }
    }

    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
            System.out.println("Error with UIManager");
        }

        EventQueue.invokeLater(GuessMyColor::new);
    }
}
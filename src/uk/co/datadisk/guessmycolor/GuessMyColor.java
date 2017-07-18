package uk.co.datadisk.guessmycolor;

import javax.swing.*;
import java.awt.*;

public class GuessMyColor extends JFrame {
    public GuessMyColor() {

        setTitle("Guess My Color");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        //setSize(400, 100);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) { }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessMyColor();
            }
        });
    }
}
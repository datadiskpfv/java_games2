package uk.co.datadisk.wordbuilder;

import uk.co.datadisk.mycomponents.TitleLabel;

import javax.swing.*;
import java.awt.*;

public class WordBuilder extends JFrame {

    private static final long serialVersionUID = 582437348539667314L;

    private JPanel mainPanel = new JPanel();

    public WordBuilder() {
        initGUI();

        setTitle("Word Builder");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        TitleLabel titleLabel = new TitleLabel("Word Builder");
        add(titleLabel, BorderLayout.PAGE_START);

        // main panel
        add(mainPanel, BorderLayout.CENTER);
        LetterPanel letterPanel = new LetterPanel("A", 1);
        mainPanel.add(letterPanel);

        // score panel


        // play panel


        // board panel


        // button panel


        // listeners
    }

    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
            System.out.println("Error with UIManager");
        }

        EventQueue.invokeLater(WordBuilder::new);
    }
}

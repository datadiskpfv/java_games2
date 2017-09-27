package uk.co.datadisk.wordbuilder;

import uk.co.datadisk.mycomponents.TitleLabel;

import javax.swing.*;
import java.awt.*;

public class WordBuilder extends JFrame {

    private static final long serialVersionUID = 582437348539667314L;

    private static final int ROWS = 8;
    private static final int COLS = 12;
    private static final int MAX = 15;

    private LetterPanel board[][] = new LetterPanel[ROWS][COLS];

    private JPanel mainPanel = new JPanel();
    private JPanel boardPanel = new JPanel();

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


        // score panel


        // play panel


        // board panel
        boardPanel.setBackground(Color.BLACK);
        boardPanel.setLayout(new GridLayout(ROWS, COLS));
        mainPanel.add(boardPanel);
        BagOfLetters letters = new BagOfLetters();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                LetterPanel letterPanel = letters.pickALetter();
                board[i][j] = letterPanel;
                boardPanel.add(letterPanel);
            }
        }

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

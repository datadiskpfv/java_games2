package uk.co.datadisk.greedy;

import uk.co.datadisk.mycomponents.TitleLabel;

import javax.swing.*;
import java.awt.*;

public class Greedy extends JFrame {

    private static final long serialVersionUID = 8228581841981263744L;

    private int points = 0;
    private int newPoints = 0;
    private int score = 0;
    private int round = 1;

    private JLabel pointsLabel = new JLabel("0");
    private JLabel scoreLabel = new JLabel("0");
    private JLabel roundLabel = new JLabel("1");

    private Font smallFont = new Font(Font.DIALOG, Font.PLAIN, 12);
    private Font largeFont = new Font(Font.DIALOG, Font.BOLD, 36);

    private JButton rollButton = new JButton("Roll");

    private Die[] dice = new Die[6];

    public Greedy() {
        initGUI();

        setTitle("Greedy");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        TitleLabel titleLabel = new TitleLabel("Greedy");
        add(titleLabel, BorderLayout.PAGE_START);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.GREEN);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);

        // score panel
        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(Color.GREEN);
        mainPanel.add(scorePanel);

        JLabel roundTitleLabel = new JLabel("Round: ");
        roundTitleLabel.setFont(smallFont);
        scorePanel.add(roundTitleLabel);
        roundLabel.setFont(largeFont);
        scorePanel.add(roundLabel);
        JLabel scoreTitleLabel = new JLabel("Score: ");
        scoreTitleLabel.setFont(smallFont);
        scorePanel.add(scoreTitleLabel);
        scoreLabel.setFont(largeFont);
        scorePanel.add(scoreLabel);

        // dice row panel
        JPanel diceRowPanel = new JPanel();
        diceRowPanel.setBackground(Color.GREEN);
        mainPanel.add(diceRowPanel);

        // points panel
        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(Color.GREEN);
        pointsPanel.setLayout(new BoxLayout(pointsPanel, BoxLayout.Y_AXIS));
        Dimension size = new Dimension(70, 70);
        pointsPanel.setPreferredSize(size);
        diceRowPanel.add(pointsPanel);

        JLabel pointsTitleLabel = new JLabel("Points");
        pointsTitleLabel.setFont(smallFont);
        pointsTitleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        pointsPanel.add(pointsTitleLabel);
        pointsLabel.setFont(largeFont);
        pointsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        pointsPanel.add(pointsLabel);


        // dice panel
        JPanel dicePanel = new JPanel();
        dicePanel.setBackground(Color.GREEN);
        diceRowPanel.add(dicePanel);

        dice[0] = new Die(1);
        dice[1] = new Die(2);
        dice[2] = new Die(3);
        dice[3] = new Die(4);
        dice[4] = new Die(5);
        dice[5] = new Die(6);

        for (int i = 0; i < 6; i++) {
            dicePanel.add(dice[i]);
        }

        // high score panel

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        add(buttonPanel, BorderLayout.PAGE_END);

        buttonPanel.add(rollButton);
        JButton endRoundButton = new JButton("End Round");
        buttonPanel.add(endRoundButton);
    }


    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
            System.out.println("Error with UIManager");
        }

        EventQueue.invokeLater(Greedy::new);
    }
}

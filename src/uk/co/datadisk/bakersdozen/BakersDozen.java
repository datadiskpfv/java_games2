package uk.co.datadisk.bakersdozen;

import uk.co.datadisk.mycomponents.TitleLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BakersDozen extends JFrame {

    private static final long serialVersionUID = -5774696217155612241L;

    private TablePanel tablePanel = new TablePanel();

    public BakersDozen() {
        initGUI();

        setTitle("Bakers Dozen");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        TitleLabel titleLabel = new TitleLabel("Bakers Dozen");
        add(titleLabel, BorderLayout.PAGE_START);

        // table panel
        add(tablePanel, BorderLayout.CENTER);

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        add(buttonPanel, BorderLayout.PAGE_END);

        JButton newButton = new JButton("New Game");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.newGame();
            }
        });
        buttonPanel.add(newButton);

        JButton replayButton = new JButton("Replay");
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.replay();
            }
        });
        buttonPanel.add(replayButton);
    }

    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
            System.out.println("Error with UIManager");
        }

        EventQueue.invokeLater(BakersDozen::new);
    }
}

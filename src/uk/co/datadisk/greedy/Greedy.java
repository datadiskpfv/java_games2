package uk.co.datadisk.greedy;

import uk.co.datadisk.mycomponents.TitleLabel;

import javax.swing.*;
import java.awt.*;

public class Greedy extends JFrame {

    private static final long serialVersionUID = 8228581841981263744L;



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
        add(mainPanel, BorderLayout.CENTER);

        Die die = new Die();
        mainPanel.add(die);
        // score panel

        // dice row panel

        // points panel

        // dice panel

        // high score panel

        // button panel
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

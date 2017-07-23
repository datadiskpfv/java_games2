package uk.co.datadisk.wizardofyesno;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import uk.co.datadisk.mycomponents.*;

/**
 * Created by vallep on 16/07/2017.
 *
 *
 */
public class WizardOfYesNo extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final String[] ANSWER = {"Yes",
            "Go for it!",
            "Yes, definitely",
            "For sure",
            "I would say yes",
            "Most, likey yes",
            "No",
            "I wouldn't",
            "In my opinion, no",
            "Definitely not!",
            "Probably not.",
            "It is very doubtful"};

    private WizardOfYesNo() {
        TitleLabel titleLabel = new TitleLabel("Wizard Yes/No");
        add(titleLabel, BorderLayout.PAGE_START);

        Random rand = new Random();
        int numberOfAnswers = ANSWER.length;
        int pick = rand.nextInt(numberOfAnswers);
        String answer = ANSWER[pick];

        JLabel label = new JLabel("");
        label.setText(answer);

        Font font = new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 28);
        label.setFont(font);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        if( pick > 5 ) {
            label.setBackground(Color.RED);
        } else {
            label.setBackground(Color.GREEN);
        }

        add(label, BorderLayout.CENTER);

        String disclaimer = "This is only a suggestion. Use your own good judgement. The Wizard of Yes/No is not responsible for the consequences of your decisions.";
        JTextArea disclaimerTextArea = new JTextArea(disclaimer);
        disclaimerTextArea.setEditable(false);
        disclaimerTextArea.setLineWrap(true);
        disclaimerTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(disclaimerTextArea);
        Dimension size = new Dimension(0, 50);
        scrollPane.setPreferredSize(size);
        add(scrollPane, BorderLayout.PAGE_END);

        setTitle("Wizard of Yes/No");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        //setSize(400, 100);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try {
            String className =UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) { }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WizardOfYesNo();
            }
        });
    }
}
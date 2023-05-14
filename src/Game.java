import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game {

    private JFrame frame;
    private JPanel mainPanel;
    private JTextField inputField;
    private JButton startButton;

    public Game() {
        frame = new JFrame("Game");
        mainPanel = new JPanel();
        inputField = new JTextField(5);
        startButton = new JButton("Start Game");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(inputField.getText());
                startGame(size);
            }
        });

        mainPanel.add(inputField);
        mainPanel.add(startButton);
        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void startGame(int size) {
        frame.getContentPane().removeAll();

        JPanel gamePanel = new JPanel(new GridLayout(size, size));

        for (int i = 0; i < size * size; i++) {
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(50, 50));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);

            Random rand = new Random();
            int randomNum = rand.nextInt(3);
            if (randomNum == 0) {
                label.setIcon(resizeIcon(new ImageIcon("wolf.png"), 50, 50));
            } else if (randomNum == 1) {
                label.setIcon(resizeIcon(new ImageIcon("wolf.png"), 50, 50));
            } else {
                label.setIcon(resizeIcon(new ImageIcon("wolf.png"), 50, 50));
            }

            gamePanel.add(label);
        }

        frame.add(gamePanel);
        frame.pack();
        frame.revalidate();
        frame.repaint();
    }

    private ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
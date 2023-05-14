import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GUI {

    private JFrame frame;
    private JPanel mainPanel;
    private JTextField inputField;
    private JButton startButton;

    private String fontName = "Calibri";
    private int fontSize = 20;
    private Font font = new Font(fontName, Font.PLAIN, fontSize);

    public GUI() {
        frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setSize(new Dimension(frame.getWidth(), frame.getHeight()));

        JPanel buttonPanel = new JPanel(new GridLayout(1,1)); // Add this line

        inputField = new JTextField(5);
        inputField.setFont(font);
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        startButton = new JButton("Start Game");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(inputField.getText());
                startGame(size);
            }
        });

        startButton.setSize(new Dimension(mainPanel.getWidth(), 200));
        startButton.setFont(font);
        buttonPanel.add(startButton);

        mainPanel.add(inputField, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        frame.add(mainPanel);
        //frame.pack();
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

            // icon
            ImageIcon icon = resizeIcon(new ImageIcon("wolf.png"), 50, 50);

            Random rand = new Random();
            int randomNum = rand.nextInt(3);
            if (randomNum == 0) {
                //label.setIcon(resizeIcon(new ImageIcon("wolf.png"), 50, 50));
                label.setIcon(icon);
            } else if (randomNum == 1) {
                //label.setIcon(resizeIcon(new ImageIcon("wolf.png"), 50, 50));
                label.setIcon(icon);
            } else {
                //label.setIcon(resizeIcon(new ImageIcon("wolf.png"), 50, 50));
                label.setIcon(icon);
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
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GUI {
    private static final int TILE_SIZE = 50;
    Game game;

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel gamePanel;
    private JTextField inputField;
    private JButton startButton;

    private String fontName = "Calibri";
    private int fontSize = 20;
    private Font font = new Font(fontName, Font.PLAIN, fontSize);

    ImageIcon humanIcon = resizeIcon(new ImageIcon("icons/human.png"), 50, 50);
    ImageIcon specialAbilityIcon = resizeIcon(new ImageIcon("icons/specialAbility.png"), 50, 50);
    ImageIcon wolfIcon = resizeIcon(new ImageIcon("icons/wolf.png"), 50, 50);
    ImageIcon foxIcon = resizeIcon(new ImageIcon("icons/fox.png"), 50, 50);
    ImageIcon sheepIcon = resizeIcon(new ImageIcon("icons/sheep.png"), 50, 50);
    ImageIcon tortoiseIcon = resizeIcon(new ImageIcon("icons/tortoise.png"), 50, 50);
    ImageIcon antelopeIcon = resizeIcon(new ImageIcon("icons/antelope.png"), 50, 50);
    ImageIcon grassIcon = resizeIcon(new ImageIcon("icons/grass.png"), 50, 50);
    ImageIcon dandelionIcon = resizeIcon(new ImageIcon("icons/dandelion.png"), 50, 50);
    ImageIcon guaranaIcon = resizeIcon(new ImageIcon("icons/guarana.png"), 50, 50);
    ImageIcon nightshadeIcon = resizeIcon(new ImageIcon("icons/nightshade.png"), 50, 50);
    ImageIcon hogweedIcon = resizeIcon(new ImageIcon("icons/hogweed.jpg"), 50, 50);

    private int mapSize = 0;

    public GUI() {
        createStartGameFrame();

        createStartGameInputField();
        createStartGameButton();

        createStartGameMainPanel();

        frame.setVisible(true);
        game = new Game(this);
    }

    private void createStartGameFrame() {
        frame = new JFrame("Game of Life - Igor Józefowicz 193257");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
    }

    private void createStartGameInputField() {
        inputField = new JTextField(5);
        inputField.setFont(font);
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setBorder(new EmptyBorder(10, 10, 10, 10));

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapSize = Integer.parseInt(inputField.getText());
                startGame();
            }
        });
    }

    private void createStartGameButton() {
        buttonPanel = new JPanel(new GridLayout(1,1));

        startButton = new JButton("Start Game");
        startButton.setSize(new Dimension(frame.getWidth(), 200));
        startButton.setFont(font);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapSize = Integer.parseInt(inputField.getText());
                startGame();
            }
        });

        buttonPanel.add(startButton);
    }

    private void createStartGameMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setSize(new Dimension(frame.getWidth(), frame.getHeight()));

        mainPanel.add(inputField, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.add(mainPanel);
    }

    private void startGame() {
        game.initializeGame();

        createGameFrame();
        createKeyPressEventListener();
        createInitialGamePanel();
        prepareGameFrame();
    }

    private void createGameFrame() {
        frame.dispose();

        frame = new JFrame("Game of Life - Igor Józefowicz 193257");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(mapSize * TILE_SIZE, mapSize * TILE_SIZE);
        frame.setLocationRelativeTo(null);
    }

    private void createKeyPressEventListener() {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_LEFT) {
                    System.out.println("Left key pressed");
                    game.movePlayer(Direction.LEFT);
                } else if (key == KeyEvent.VK_RIGHT) {
                    System.out.println("Right key pressed");
                    game.movePlayer(Direction.RIGHT);
                } else if (key == KeyEvent.VK_UP) {
                    System.out.println("Up key pressed");
                    game.movePlayer(Direction.UP);
                } else if (key == KeyEvent.VK_DOWN) {
                    System.out.println("Down key pressed");
                    game.movePlayer(Direction.DOWN);
                }  else if (key == KeyEvent.VK_E) {
                    System.out.println("Special ability");
                    game.activateSpecialAbility();
                }  else if (key == KeyEvent.VK_S) {
                    System.out.println("Save game state");
                    game.saveToFile();
                } else if (key == KeyEvent.VK_L) {
                    System.out.println("Load game state");
                    game.loadFromFile();
                }  else if (key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_Q) {
                    System.out.println("Escape OR 'q' key pressed");
                    game.quitGame();
                }

                refreshFrame();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        frame.setVisible(true);
        frame.setFocusable(true);
    }

    private void createInitialGamePanel() {
        gamePanel = new JPanel(new GridLayout(mapSize, mapSize));

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                JLabel label = new JLabel();
                label.setPreferredSize(new Dimension(50, 50));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);

                Organism organism = game.world.getOrganismAt(new Point(j, i));

                setLabelIcon(label, organism);

                gamePanel.add(label);
            }
        }



        frame.add(gamePanel);

        createGameInfoPanel();
    }





    /*private void createInitialGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        gamePanel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));

        for (int i = 0; i < mapSize; i++) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));

            for (int j = 0; j < mapSize; j++) {
                if ((i % 2 != 0) && (j == mapSize - 1)) {
                    continue; // Skip the last label in every odd row
                }

                JLabel label = createHexagonLabel();
                Organism organism = game.world.getOrganismAt(new Point(j, i));
                setLabelIcon(label, organism);

                rowPanel.add(label);
            }

            gamePanel.add(rowPanel);
        }

        frame.add(gamePanel);

        createGameInfoPanel();
    }

    private JLabel createHexagonLabel() {
        int size = 50; // Size of the hexagon
        Polygon hexagon = new Polygon();
        for (int i = 0; i < 6; i++) {
            hexagon.addPoint((int) (size / 2 + size * Math.cos(i * Math.PI / 3)),
                    (int) (size / 2 + size * Math.sin(i * Math.PI / 3)));
        }

        Icon icon = new ImageIcon(createHexagonImage(hexagon, size));
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(size, size));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        return label;
    }

    private Image createHexagonImage(Polygon hexagon, int size) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fill(hexagon);
        g2.setColor(Color.BLACK);
        g2.draw(hexagon);
        g2.dispose();
        return image;
    }*/






    private void prepareGameFrame() {
        frame.pack();
        frame.revalidate();
        frame.repaint();
    }

    private ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void refreshFrame() {
        frame.getContentPane().removeAll();

        gamePanel = new JPanel(new GridLayout(mapSize, mapSize));

        JLabel[][] labels = new JLabel[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                labels[i][j] = new JLabel();
                labels[i][j].setPreferredSize(new Dimension(50, 50));
                labels[i][j].setHorizontalAlignment(JLabel.CENTER);
                labels[i][j].setVerticalAlignment(JLabel.CENTER);

                /*JLabel label = new JLabel();
                label.setPreferredSize(new Dimension(50, 50));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);*/

                int finalI = i;
                int finalJ = j;
                labels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.isPopupTrigger()) {
                            createContextMenu(finalI, finalJ).show(e.getComponent(), e.getX(), e.getY());
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (e.isPopupTrigger()) {
                            createContextMenu(finalI, finalJ).show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                });

                Organism organism = game.world.getOrganismAt(new Point(j, i));

                setLabelIcon(labels[i][j], organism);

                gamePanel.add(labels[i][j]);
            }
        }

        createGameInfoPanel();

        frame.pack();
        frame.revalidate();
        frame.repaint();
    }

    private JPopupMenu createContextMenu(int x, int y) {
        JPopupMenu contextMenu = new JPopupMenu();

        for (Species species : Species.values()) {
            if (species == Species.HUMAN || species == Species.ORGANISM || species == Species.PLANT) {
                continue;
            }

            JMenuItem menuItem = new JMenuItem(species.name());
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    game.world.createNewOrganismBySpecies(species, new Point(y, x));
                    refreshFrame();
                }
            });

            contextMenu.add(menuItem);
        }


        return contextMenu;
    }

    public void createGameInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Info panel"));

        JLabel turnLabel = new JLabel("Turn: " + String.valueOf(game.getTurn()));
        infoPanel.add(turnLabel);

        JLabel specialAbilityActiveLabel = new JLabel("Special ability cooldown: " + String.valueOf(game.getSpecialAbilityCooldown()));
        infoPanel.add(specialAbilityActiveLabel);

        JLabel specialAbilityLabel = new JLabel("Special ability duration: " + String.valueOf(game.getSpecialAbilityDuration()));
        infoPanel.add(specialAbilityLabel);

        String turnSummaryMessages = "";
        for (String message : game.world.getTurnSummaryMessages()) {
            turnSummaryMessages += message + "\n";
        }

        JTextArea textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        infoPanel.add(scrollPane);

        textArea.setText(turnSummaryMessages);
        game.world.clearTurnSummaryMessages();

        frame.setLayout(new BorderLayout());
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(infoPanel, BorderLayout.EAST);
    }

    public void setLabelIcon(JLabel label, Organism organism) {
        if (organism instanceof Human) {
            label.setIcon(humanIcon);
        } else if (organism instanceof Wolf) {
            label.setIcon(wolfIcon);
        } else if (organism instanceof Fox) {
            label.setIcon(foxIcon);
        } else if (organism instanceof Sheep) {
            label.setIcon(sheepIcon);
        }  else if (organism instanceof Tortoise) {
            label.setIcon(tortoiseIcon);
        } else if (organism instanceof Antelope) {
            label.setIcon(antelopeIcon);
        } else if (organism instanceof Grass) {
            label.setIcon(grassIcon);
        } else if (organism instanceof Dandelion) {
            label.setIcon(dandelionIcon);
        } else if (organism instanceof Guarana) {
            label.setIcon(guaranaIcon);
        } else if (organism instanceof Nightshade) {
            label.setIcon(nightshadeIcon);
        } else if (organism instanceof Hogweed) {
            label.setIcon(hogweedIcon);
        }
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
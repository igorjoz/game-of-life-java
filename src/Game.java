import java.awt.*;

public class Game {
    private GUI gui;
    private int size;


    public World world;

    private char playerInput;
    private int turn;
    private boolean isRunning;

    private int specialAbilityCooldown;
    private boolean isSpecialAbilityActive;

    private static final int SPECIAL_ABILITY_COOLDOWN = 5;

    public Game() {
    }

    public Game(GUI gui) {
        this.gui = gui;
    }

    public void initializeGame() {
        this.size = gui.getMapSize();

        this.world = new World(size);
        this.playerInput = ' ';
        this.isRunning = true;
        this.turn = 0;
        this.specialAbilityCooldown = -1;
        this.isSpecialAbilityActive = false;

        spawnInitialOrganisms();
    }

    public void runGame() {
        System.out.println("Map size: " + size);
        System.out.println("Game loop STARTED");
    }

    public void spawnInitialOrganisms() {
        createHuman();
        spawnWolves();
    }

    public void createHuman() {
        Point position = new Point(0, 0);

        Human human = new Human(position, world);
        world.createHuman(human);
    }

    public void spawnWolves() {
        for (int i = 0; i < Wolf.INITIAL_QUANTITY; i++) {
            Wolf wolf = new Wolf(world);
            world.spawnOrganism(wolf);
        }



        /*for (int i = 0; i < 2; i++) {
            Wolf wolf = new Wolf(new Point(1 + i, i), world);
            world.spawnOrganism(wolf);
        }*/
    }

    public void movePlayer(Direction direction) {
        Human human = world.getHuman();
        Point currentPosition = world.getPlayerPosition();

        Point destination = new Point(currentPosition.x, currentPosition.y);

        if (direction == Direction.UP) {
            destination.y--;
            human.setPlayerAction(PlayerAction.MOVE_UP);
        } else if (direction == Direction.DOWN) {
            destination.y++;
            human.setPlayerAction(PlayerAction.MOVE_DOWN);
        } else if (direction == Direction.LEFT) {
            destination.x--;
            human.setPlayerAction(PlayerAction.MOVE_LEFT);
        } else if (direction == Direction.RIGHT) {
            destination.x++;
            human.setPlayerAction(PlayerAction.MOVE_RIGHT);
        }

        // print destination
        System.out.println("Destination: " + destination.x + ", " + destination.y);


        if (world.canMoveTo(destination)) {
            // print organisms array
            System.out.println("Organisms array:");
            for (int i = 0; i < world.getSize(); i++) {
                for (int j = 0; j < world.getSize(); j++) {
                    if (world.getOrganisms()[j][i] == null) {
                        System.out.print("- ");
                    } else {
                        System.out.print(world.getOrganisms()[j][i].getSymbol() + " ");
                    }
                }
                System.out.println();
            }

            // print that can move
            System.out.println("Can move to destination");
            //world.movePlayer(destination);

            world.takeTurn();
        }
    }

    public void runTurn() {
        /*world.takeTurn();
        turn++;
        System.out.println("Turn: " + turn);*/
    }

    public void quitGame() {
        System.out.println("Game loop ENDED");
        System.exit(0);
    }

    public World getWorld() {
        return world;
    }
}
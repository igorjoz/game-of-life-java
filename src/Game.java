import java.awt.*;

public class Game {
    private GUI gui;
    public World world;
    private int size;
    private int turn;

    private int specialAbilityCooldown;
    private int specialAbilityDuration;
    private static final int SPECIAL_ABILITY_COOLDOWN = 5;

    public Game() {
    }

    public Game(GUI gui) {
        this.gui = gui;
    }

    public void initializeGame() {
        this.size = gui.getMapSize();

        this.world = new World(this, size);
        this.turn = 0;

        this.specialAbilityCooldown = SPECIAL_ABILITY_COOLDOWN;
        this.specialAbilityDuration = 0;

        spawnInitialOrganisms();
    }

    public void spawnInitialOrganisms() {
        createHuman();

        spawnWolves();
        //spawnFoxes();
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
    }

    public void movePlayer(Direction direction) {
        Human human = world.getHuman();

        if (direction == Direction.UP) {
            human.setPlayerAction(PlayerAction.MOVE_UP);
        } else if (direction == Direction.DOWN) {
            human.setPlayerAction(PlayerAction.MOVE_DOWN);
        } else if (direction == Direction.LEFT) {
            human.setPlayerAction(PlayerAction.MOVE_LEFT);
        } else if (direction == Direction.RIGHT) {
            human.setPlayerAction(PlayerAction.MOVE_RIGHT);
        }

        world.takeTurn();
        turn++;

        if (specialAbilityCooldown > 0) {
            specialAbilityCooldown--;
        }

        if (specialAbilityDuration > 0) {
            specialAbilityDuration--;

            if (specialAbilityDuration == 0) {
                specialAbilityCooldown = SPECIAL_ABILITY_COOLDOWN;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (world.getOrganisms()[j][i] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(world.getOrganisms()[j][i].getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }

    public void activateSpecialAbility() {
        if (specialAbilityCooldown > 0) {
            System.out.println("Special ability is on cooldown!");
            return;
        }

        specialAbilityDuration = 5;
        specialAbilityCooldown = 0;
    }

    public void quitGame() {
        System.out.println("Game loop ENDED");
        System.exit(0);
    }

    public int getTurn() {
        return turn;
    }

    public int getSpecialAbilityCooldown() {
        return specialAbilityCooldown;
    }

    public int getSpecialAbilityDuration() {
        return specialAbilityDuration;
    }

    public World getWorld() {
        return world;
    }
}
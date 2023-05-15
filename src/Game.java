import java.awt.*;
import java.util.Scanner;
import java.io.*;

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

    public void runGame() {
        System.out.println("Map size: " + size);
    }

    public void initializeGame(int size) {
        this.size = size;

        this.world = new World(size);
        this.playerInput = ' ';
        this.isRunning = true;
        this.turn = 0;
        this.specialAbilityCooldown = -1;
        this.isSpecialAbilityActive = false;
        this.size = size;

        spawnInitialOrganisms();
    }

    public void spawnInitialOrganisms() {
        createHuman();
        spawnWolves();
    }

    public void createHuman() {
        Human human = new Human(new Point(0, 0), world);
        world.createHuman(human, new Point(0, 0));
    }

    public void spawnWolves() {
        for (int i = 0; i < 2; i++) {
            Wolf wolf = new Wolf(new Point(1 + i, i), world);
            world.spawnOrganism(wolf);
        }
    }

    public Object getWorld() {
        return world;
    }
}
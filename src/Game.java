import java.util.Scanner;
import java.io.*;

public class Game {
    private GUI gui;
    private int size;


    private World world;

    private char playerInput;
    private int turn;
    private boolean isRunning;

    private int specialAbilityCooldown;
    private boolean isSpecialAbilityActive;

    private static final int SPECIAL_ABILITY_COOLDOWN = 5;

    public Game() {
    }

    /*public Game(int size) {
        this.world = new World(size);
        this.playerInput = ' ';
        this.isRunning = true;
        this.turn = 0;
        this.specialAbilityCooldown = -1;
        this.isSpecialAbilityActive = false;
        this.size = size;
    }*/

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

        // create 2D array of size x size
        // fill array with random numbers

        /*for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

            }
        }*/
    }
}
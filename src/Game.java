import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

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
        spawnFoxes();

        spawnSheep();
        spawnTortoises();
        spawnAntelopes();

        spawnGrass();
        spawnDanedelions();
        spawnGuarana();
        spawnNightshades();
        spawnHogweeds();
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

    public void spawnFoxes() {
        for (int i = 0; i < Fox.INITIAL_QUANTITY; i++) {
            Fox fox = new Fox(world);
            world.spawnOrganism(fox);
        }
    }

    public void spawnSheep() {
        for (int i = 0; i < Sheep.INITIAL_QUANTITY; i++) {
            Sheep sheep = new Sheep(world);
            world.spawnOrganism(sheep);
        }
    }

    public void spawnTortoises() {
        for (int i = 0; i < Tortoise.INITIAL_QUANTITY; i++) {
            Tortoise tortoise = new Tortoise(world);
            world.spawnOrganism(tortoise);
        }
    }

    public void spawnAntelopes() {
        for (int i = 0; i < Antelope.INITIAL_QUANTITY; i++) {
            Antelope antelope = new Antelope(world);
            world.spawnOrganism(antelope);
        }
    }

    public void spawnGrass() {
        for (int i = 0; i < Grass.INITIAL_QUANTITY; i++) {
            Grass grass = new Grass(world);
            world.spawnOrganism(grass);
        }
    }

    public void spawnDanedelions() {
        for (int i = 0; i < Dandelion.INITIAL_QUANTITY; i++) {
            Dandelion dandelion = new Dandelion(world);
            world.spawnOrganism(dandelion);
        }
    }

    public void spawnGuarana() {
        for (int i = 0; i < Guarana.INITIAL_QUANTITY; i++) {
            Guarana guarana = new Guarana(world);
            world.spawnOrganism(guarana);
        }
    }

    public void spawnNightshades() {
        for (int i = 0; i < Nightshade.INITIAL_QUANTITY; i++) {
            Nightshade nightshade = new Nightshade(world);
            world.spawnOrganism(nightshade);
        }
    }

    public void spawnHogweeds() {
        for (int i = 0; i < Hogweed.INITIAL_QUANTITY; i++) {
            Hogweed hogweed = new Hogweed(world);
            world.spawnOrganism(hogweed);
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
        incrementOrganismsAge();
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

    public void incrementOrganismsAge() {
        for (Organism organism : world.getOrganismsList()) {
            organism.setAge(organism.getAge() + 1);
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

    public void saveToFile() {
        try {
            PrintWriter file = new PrintWriter(new FileOutputStream("save.txt"));

            file.println(turn);
            file.println(size + " " + world.getOrganismsList().size());
            file.println(specialAbilityCooldown + " " + specialAbilityDuration);
            file.println(world.getPlayerPosition().x + " " + world.getPlayerPosition().y);

            for (Organism organism : world.getOrganismsList()) {
                file.println(organism.getPosition().x + " " + organism.getPosition().y + " "
                        + organism.getStrength() + " " + organism.getAge() + " " + organism.getSpecies());
            }

            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try {
            Scanner file = new Scanner(new File("save.txt"));

            world.clearOrganisms();

            int organismsQuantity;
            int playerX, playerY;

            int x, y;
            int strength, age;

            turn = file.nextInt();
            size = file.nextInt();
            organismsQuantity = file.nextInt();

            specialAbilityCooldown = file.nextInt();
            specialAbilityDuration = file.nextInt();

            playerX = file.nextInt();
            playerY = file.nextInt();

            world.getHuman().setPosition(new Point(playerX, playerY));

            for (int i = 0; i < organismsQuantity; i++) {
                x = file.nextInt();
                y = file.nextInt();
                strength = file.nextInt();
                age = file.nextInt();

                String speciesString = file.next();
                Species species = Species.valueOf(speciesString);

                Organism organism = null;

                if (species == Species.HUMAN) {
                    organism = new Human(new Point(x, y), world);
                    world.setHuman((Human) organism);
                } else if (species == Species.WOLF) {
                    organism = new Wolf(new Point(x, y), world);
                } else if (species == Species.SHEEP) {
                    organism = new Sheep(new Point(x, y), world);
                } else if (species == Species.FOX) {
                    organism = new Fox(new Point(x, y), world);
                } else if (species == Species.TORTOISE) {
                    organism = new Tortoise(new Point(x, y), world);
                } else if (species == Species.ANTELOPE) {
                    organism = new Antelope(new Point(x, y), world);
                } else if (species == Species.GRASS) {
                    organism = new Grass(new Point(x, y), world);
                } else if (species == Species.DANDELION) {
                    organism = new Dandelion(new Point(x, y), world);
                } else if (species == Species.GUARANA) {
                    organism = new Guarana(new Point(x, y), world);
                } else if (species == Species.NIGHTSHADE) {
                    organism = new Nightshade(new Point(x, y), world);
                } else if (species == Species.HOGWEED) {
                    organism = new Hogweed(new Point(x, y), world);
                }

                organism.setStrength(strength);
                organism.setAge(age);

                world.setOrganism(organism, new Point(x, y));
            }

            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: could not open file");
        }
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.awt.Point;

public class World {
    private Organism[][] organisms;
    private List<Organism> organismsList = new ArrayList<>();
    private List<String> turnSummaryMessages = new ArrayList<>();
    private Human human;

    //private Point playerPosition;
    //private PlayerAction playerAction;



    private boolean isPlayerAlive;

    private static int size;

    public World(int size) {
        this.size = size;
        this.isPlayerAlive = true;
        //this.playerAction = PlayerAction.NONE;

        this.organisms = new Organism[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                organisms[i][j] = null;
            }
        }
    }

    public void takeTurn() {
        organismsList.sort(Comparator.comparing(Organism::getInitiative).thenComparing(Organism::getAge).reversed());

        for (Organism organism : organismsList) {
            organism.action();
        }
    }

    public void spawnOrganism(Organism organism) {
        Point position = getRandomPosition();

        if (isOccupied(position)) {
            return;
        }

        organism.setPosition(position);

        organismsList.add(organism);
        organisms[organism.getX()][organism.getY()] = organism;
    }

    public Point getRandomPosition() {
        int x = (int)(Math.random() * size);
        int y = (int)(Math.random() * size);

        return new Point(x, y);
    }

    public void setOrganism(Organism organism, Point position) {
        organismsList.add(organism);
        organisms[position.x][position.y] = organism;
    }

    public void createHuman(Human human) {
        Point position = human.getPosition();

        //playerPosition = position;
        this.human = human;
        organismsList.add(human);
        organisms[position.x][position.y] = human;
    }

    public void move(Point position, Point destination) {
        Organism organism = organisms[position.x][position.y];

        organisms[position.x][position.y] = null;
        organisms[destination.x][destination.y] = organism;
        organism.setPosition(destination);
    }

    /*public void movePlayer(Point destination) {
        organisms[destination.x][destination.y] = organisms[playerPosition.x][playerPosition.y];
        organisms[playerPosition.x][playerPosition.y] = null;
        playerPosition = destination;
    }*/

    public void remove(Point position) {
        // Implement remove logic here.
    }

    public boolean isEmpty(Point position) {
        return organisms[position.x][position.y] == null;
    }

    public boolean isOccupied(Point position) {
        return organisms[position.x][position.y] != null;
    }

    public boolean isWithinBoardBoundaries(Point position) {
        return isWithinBoardBoundaries(position.x, position.y, size);
    }

    public static boolean isWithinBoardBoundaries(int x, int y, int size) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    //public boolean hasFreeSpace(Point position) {
        // Implement hasFreeSpace logic here.
    //}

    //public Point getRandomNeighbour(Point position) {
        // Implement getRandomNeighbour logic here.
    //}

    //public Point getRandomFreeSpaceAround(Point position) {
        // Implement getRandomFreeSpaceAround logic here.
    //}

    public void addTurnSummaryMessage(String message) {
        turnSummaryMessages.add(message);
    }

    public void printTurnSummaryMessages() {
        // Implement printTurnSummaryMessages logic here.
    }

    public void addToOrganismsList(Organism organism) {
        organismsList.add(organism);
    }

    public void clearOrganisms() {
        // Implement clearOrganisms logic here.
    }

    public void printOrganismsInfo() {
        // Implement printOrganismsInfo logic here.
    }

    public void printTurnSummary() {
        // Implement printTurnSummary logic here.
    }

    public void printStatistics() {
        // Implement printStatistics logic here.
    }

    public Point getPlayerPosition() {
        return human.getPosition();
        //return playerPosition;
    }

    public Human getHuman() {
        return human;
    }

    /*public PlayerAction getPlayerAction() {
        return playerAction;
    }*/

    public boolean getIsPlayerAlive() {
        return isPlayerAlive;
    }

    public static int getSize() {
        return size;
    }

    public Organism getOrganismAt(Point destination) {
        return organisms[destination.x][destination.y];
    }

    public Organism[][] getOrganisms() {
        return organisms;
    }

    public boolean hasFreeSpace(Point position) {
        if (isWithinBoardBoundaries(position.x - 1, position.y, size) && organisms[position.x - 1][position.y] == null) {
            return true;
        }

        return false;
    }

    public Point getRandomNeighbour(Point position) {
        int x = position.x;
        int y = position.y;

        int random = (int) (Math.random() * 4);

        switch (random) {
            case 0:
                x--;
                break;
            case 1:
                x++;
                break;
            case 2:
                y--;
                break;
            case 3:
                y++;
                break;
        }

        return new Point(x, y);
    }

    public void setIsPlayerAlive(boolean isPlayerAlive) {
        this.isPlayerAlive = isPlayerAlive;
    }

    /*public void setPlayerPosition(int x, int y) {
        playerPosition = new Point(x, y);
    }*/

    public Point getRandomFreeSpaceAround(Point position) {
        int x = position.x;
        int y = position.y;

        int random = (int) (Math.random() * 4);

        switch (random) {
            case 0:
                x--;
                break;
            case 1:
                x++;
                break;
            case 2:
                y--;
                break;
            case 3:
                y++;
                break;
        }

        return new Point(x, y);
    }

    public boolean canMoveTo(Point destination) {
        // priint that canMoveTo check is being performed
        System.out.println("canMoveTo check is being performed");

        if (isWithinBoardBoundaries(destination)) {
            System.out.println("isWithinBoardBoundaries");
            if (isEmpty(destination)) {
                System.out.println("isEmpty");
                return true;
            } else {
                System.out.println("isOccupied");
                System.out.println("Organism at destination: " + organisms[destination.x][destination.y].getClass().getSimpleName());
                return false;
            }
        }

        return isWithinBoardBoundaries(destination) && isEmpty(destination);
    }
}


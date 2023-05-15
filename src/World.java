import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.awt.Point;

public class World {
    private List<Organism> organismsList = new ArrayList<>();
    private List<String> turnSummaryMessages = new ArrayList<>();

    private Point playerPosition;
    private PlayerAction playerAction;

    private Organism[][] organisms;

    private boolean isPlayerAlive;

    private static int size;

    public World(int size) {
        this.isPlayerAlive = true;
        this.playerAction = PlayerAction.NONE;
        this.size = size;
        this.organisms = new Organism[size][size];
    }

    public static int getSize() {
        return size;
    }

    public void drawWorld() {
        // Implement your drawing logic here. Use System.out.println() for console output.
    }

    public void takeTurn() {
        organismsList.sort(Comparator.comparing(Organism::getInitiative).thenComparing(Organism::getAge).reversed());
        for (Organism organism : organismsList) {
            organism.action();
        }
    }

    public void spawnOrganism(Organism organism) {
        organismsList.add(organism);
        organisms[organism.getPosition().x][organism.getPosition().y] = organism;
    }

    public void setOrganism(Organism organism, Point position) {
        // Implement set organism logic here.
    }

    public void createHuman(Human human, Point position) {
        playerPosition = position;
        organismsList.add(human);
        organisms[position.x][position.y] = human;
    }

    public void move(Point position, Point destination) {
        // Implement move logic here.
    }

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
        return playerPosition;
    }

    public PlayerAction getPlayerAction() {
        return playerAction;
    }

    public boolean getIsPlayerAlive() {
        return isPlayerAlive;
    }

    public Organism getOrganismAt(Point destination) {
        return organisms[destination.x][destination.y];
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

    public void setPlayerPosition(int x, int y) {
        playerPosition = new Point(x, y);
    }

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
}


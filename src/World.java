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

    private int size;

    public World(int size) {
        this.isPlayerAlive = true;
        this.playerAction = PlayerAction.NONE;
        this.size = size;
        this.organisms = new Organism[size][size];
    }

    public void drawWorld() {
        // Implement your drawing logic here. Use System.out.println() for console output.
    }

    public void drawHorizontalBorder() {
        // Implement your drawing logic here.
    }

    public void takeTurn() {
        organismsList.sort(Comparator.comparing(Organism::getInitiative).thenComparing(Organism::getAge).reversed());
        for (Organism organism : organismsList) {
            organism.action();
        }
    }

    public void spawnOrganism(Organism organism) {
        // Implement spawn logic here.
    }

    public void setOrganism(Organism organism, Point position) {
        // Implement set organism logic here.
    }

    public void createHuman(Organism organism, Point position) {
        // Implement create human logic here.
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
}


import java.util.*;

import java.awt.Point;

public class World {
    private Organism[][] organisms;
    private List<Organism> organismsList = new ArrayList<>();
    private List<String> turnSummaryMessages = new ArrayList<>();
    Game game;
    private Human human;
    private int size;

    public World(Game game, int size) {
        this.game = game;
        this.size = size;

        this.organisms = new Organism[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                organisms[i][j] = null;
            }
        }
    }

    public void takeTurn() {
        if (!human.getIsAlive()) {
            System.out.println("You died!");
            System.exit(0);
        }

        organismsList.sort(Comparator.comparing(Organism::getInitiative).thenComparing(Organism::getAge).reversed());

        List<Organism> organismsListCopy = new ArrayList<>(organismsList);
        for (Organism organism : organismsListCopy) {
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
        if (isOccupied(position) || !isWithinBoardBoundaries(position) || organism == null) {
            return;
        }

        organism.setPosition(position);
        organismsList.add(organism);
        organisms[organism.getX()][organism.getY()] = organism;
    }

    public void createHuman(Human human) {
        Point position = human.getPosition();

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

    public void remove(Point position) {
        Iterator<Organism> iterator = organismsList.iterator();

        while(iterator.hasNext()) {
            Organism organism = iterator.next();
            if(organism.getPosition().equals(position)) {
                iterator.remove();
            }
        }

        if (organisms[position.x][position.y] != null) {
            organisms[position.x][position.y] = null;
        }
    }

    public boolean isEmpty(Point position) {
        return organisms[position.x][position.y] == null;
    }

    public boolean isOccupied(Point position) {
        return organisms[position.x][position.y] != null;
    }

    public boolean isWithinBoardBoundaries(Point position) {
        if (position == null) {
            return false;
        }

        return isWithinBoardBoundaries(position.x, position.y);
    }

    public boolean isWithinBoardBoundaries(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

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
    }

    public Human getHuman() {
        return human;
    }

    public boolean getIsPlayerAlive() {
        return human.getIsAlive();
    }

    public int getSize() {
        return size;
    }

    public Organism getOrganismAt(Point destination) {
        return organisms[destination.x][destination.y];
    }

    public Organism[][] getOrganisms() {
        return organisms;
    }

    public boolean hasFreeSpace(Point position) {
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        for (int[] direction : directions) {
            int newX = position.x + direction[0];
            int newY = position.y + direction[1];

            if (isWithinBoardBoundaries(newX, newY) && organisms[newX][newY] == null) {
                return true;
            }
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

    public Point getRandomFreeSpaceAround(Point position) {
        ArrayList<Point> freeSpaces = new ArrayList<>();

        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        for (int[] direction : directions) {
            int newX = position.x + direction[0];
            int newY = position.y + direction[1];

            if (isWithinBoardBoundaries(newX, newY) && organisms[newX][newY] == null) {
                freeSpaces.add(new Point(newX, newY));
            }
        }

        if (freeSpaces.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomNumber = random.nextInt(freeSpaces.size());

        return freeSpaces.get(randomNumber);
    }

    public boolean canMoveTo(Point destination) {
        return isWithinBoardBoundaries(destination) && isEmpty(destination);
    }

    public Game getGame() {
        return game;
    }
}


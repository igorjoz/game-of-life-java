import java.io.ByteArrayOutputStream;
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
        if (position == null) {
            return false;
        }

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

    public void addToOrganismsList(Organism organism) {
        organismsList.add(organism);
    }

    public void clearOrganisms() {
        organismsList.clear();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                organisms[i][j] = null;
            }
        }
    }

    public void createNewOrganismBySpecies(Species species, Point position) {
        Organism newOrganism;
        switch(species) {
            case HUMAN:
                newOrganism = new Human(position, this);
                break;
            case WOLF:
                newOrganism = new Wolf(position, this);
                break;
            case SHEEP:
                newOrganism = new Sheep(position, this);
                break;
            case FOX:
                newOrganism = new Fox(position, this);
                break;
            case TORTOISE:
                newOrganism = new Tortoise(position, this);
                break;
            case ANTELOPE:
                newOrganism = new Antelope(position, this);
                break;
            case GRASS:
                newOrganism = new Grass(position, this);
                break;
            case DANDELION:
                newOrganism = new Dandelion(position, this);
                break;
            case GUARANA:
                newOrganism = new Guarana(position, this);
                break;
            case NIGHTSHADE:
                newOrganism = new Nightshade(position, this);
                break;
            case HOGWEED:
                newOrganism = new Hogweed(position, this);
                break;
            default:
                newOrganism = null;
                break;
        }

        setOrganism(newOrganism, position);
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

    public List<Organism> getOrganismsList() {
        return organismsList;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public List<String> getTurnSummaryMessages() {
        return turnSummaryMessages;
    }

    public void clearTurnSummaryMessages() {
        turnSummaryMessages.clear();
    }
}


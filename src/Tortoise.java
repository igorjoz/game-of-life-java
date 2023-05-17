import java.awt.*;
import java.util.Random;

public class Tortoise extends PreyAnimal {
    public static final int INITIAL_QUANTITY = 2;
    private static final int STRENGTH = 2;
    private static final int INITIATIVE = 1;
    private static final char SYMBOL = 'T';
    private static final Random RANDOM = new Random();

    public Tortoise(Point position, World world) {
        super(STRENGTH, INITIATIVE, SYMBOL, position, world);
        species = Species.TORTOISE;
    }

    public Tortoise(World world) {
        super(STRENGTH, INITIATIVE, SYMBOL, world);
        species = Species.TORTOISE;
    }

    @Override
    public void reproduce(Point position) {
        if (RANDOM.nextInt(3) != 0) {
            return;
        }

        Point freeSpace = world.getRandomFreeSpaceAround(position);

        Tortoise newOrganism = new Tortoise(freeSpace, world);
        world.setOrganism(newOrganism, freeSpace);

        String message = "Organism " + symbol + " reproduced at (" + position.x + ", " + position.y + ")";
        world.addTurnSummaryMessage(message);
    }

    @Override
    public boolean collision(Organism other) {
        if (canReproduce(other, position)) {
            reproduce(position);
            return true;
        }

        if (canKill(other)) {
            kill(other);
            return true;
        } else {
            other.collision(this);

            if (world.getOrganismAt(position) != null) {
                Point destination = world.getRandomNeighbour(position);

                if (world.isWithinBoardBoundaries(destination) && !world.isOccupied(destination)) {
                    move(destination);
                }
            }
            return true;
        }
    }

    @Override
    public void action() {
        if (RANDOM.nextInt(4) == 0) {
            return;
        }

        super.action();
    }

    @Override
    public boolean canBeKilledBy(Organism other) {
        if (this.getClass() == other.getClass()) {
            return false;
        }

        return strength < other.getStrength() && other.getStrength() > 5;
    }
}
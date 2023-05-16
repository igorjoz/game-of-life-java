import java.awt.*;

public class Fox extends PredatorAnimal {
    public static final int INITIAL_QUANTITY = 2;

    private static final int STRENGTH = 3;
    private static final int INITIATIVE = 7;
    private static final char SYMBOL = 'F';

    public Fox(Point position, World world) {
        super(STRENGTH, INITIATIVE, SYMBOL, position, world);
        species = Species.FOX;
    }

    public Fox(World world) {
        super(STRENGTH, INITIATIVE, SYMBOL, world);
        species = Species.FOX;
    }

    @Override
    public void reproduce(Point position) {
        Point freeSpace = world.getRandomFreeSpaceAround(position);

        Fox newOrganism = new Fox(freeSpace, world);
        world.setOrganism(newOrganism, freeSpace);

        String message = "Organism " + SYMBOL + " reproduced at (" + position.x + ", " + position.y + ")";

        world.addTurnSummaryMessage(message);
    }

    @Override
    public boolean collision(Organism other) {
        if (canKill(other)) {
            kill(other);
            return true;
        }

        if (canReproduce(other, position)) {
            reproduce(position);
        }

        return false;
    }
}
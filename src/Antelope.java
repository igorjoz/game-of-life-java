import java.awt.*;
import java.util.Random;

public class Antelope extends PreyAnimal {
    public static final int INITIAL_QUANTITY = 2;
    private static final int STRENGTH = 4;
    private static final int INITIATIVE = 4;
    private static final char SYMBOL = 'A';
    private static final Random RANDOM = new Random();

    public Antelope(Point position, World world) {
        super(STRENGTH, INITIATIVE, SYMBOL, position, world);
        species = Species.ANTELOPE;
    }

    public Antelope(World world) {
        super(STRENGTH, INITIATIVE, SYMBOL, world);
        species = Species.ANTELOPE;
    }

    @Override
    public void reproduce(Point position) {
        Point freeSpace = world.getRandomFreeSpaceAround(position);

        Antelope newOrganism = new Antelope(freeSpace, world);
        world.setOrganism(newOrganism, freeSpace);

        String message = "Organism " + symbol + " reproduced at (" + position.x + ", " + position.y + ")";
        world.addTurnSummaryMessage(message);
    }

    @Override
    public void action() {
        for (int i = 0; i < 2; i++) {
            Point destination = world.getRandomFreeSpaceAround(position);

            if (!destination.equals(position)) {
                move(destination);
            }
        }
    }

    @Override
    public boolean collision(Organism other) {
        if (canReproduce(other, position)) {
            reproduce(position);
            return true;
        }

        if (other.canBeKilledBy(this)) {
            kill(other);
            return true;
        } else if (other.canKill(this)) {
            if (RANDOM.nextInt(2) == 0) {
                Point freeSpace = world.getRandomFreeSpaceAround(position);

                if (!freeSpace.equals(position)) {
                    move(freeSpace);
                }

                String message = "Organism " + symbol + " escaped from fight at (" + position.x + ", " + position.y + ")";
                world.addTurnSummaryMessage(message);
                return true;
            }

            other.collision(this);
            return true;
        }

        return false;
    }

    @Override
    public void die() {
        if (RANDOM.nextInt(2) == 0) {
            Point freeSpace = world.getRandomFreeSpaceAround(position);

            if (freeSpace.equals(position)) {
                world.remove(position);
            }

            move(freeSpace);

            String message = "Organism " + symbol + " escaped from fight at (" + position.x + ", " + position.y + ")";
            world.addTurnSummaryMessage(message);

            return;
        }

        world.remove(position);
    }
}
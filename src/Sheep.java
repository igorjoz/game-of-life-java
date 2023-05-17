import java.awt.*;
import java.util.Random;

public class Sheep extends PreyAnimal {
    public static final int INITIAL_QUANTITY = 4;
    public static final int STRENGTH = 4;
    public static final int INITIATIVE = 4;
    public static final char SYMBOL = 'S';

    private static final Random rand = new Random();

    public Sheep(Point position, World world) {
        super(STRENGTH, INITIATIVE, SYMBOL, position, world);
        species = Species.SHEEP;
    }

    public Sheep(World world) {
        super(STRENGTH, INITIATIVE, SYMBOL, world);
        species = Species.SHEEP;
    }

    @Override
    public void reproduce(Point position) {
        if (rand.nextInt(3) != 0) {
            return;
        }
        Point freeSpace = world.getRandomFreeSpaceAround(position);
        Organism newOrganism = new Sheep(freeSpace, world);
        world.setOrganism(newOrganism, freeSpace);
        String message = "Organism " + symbol + " reproduced at (" + position.getX() + ", " + position.getY() + ")";
        world.addTurnSummaryMessage(message);
    }
}
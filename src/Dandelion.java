import java.awt.*;
import java.util.Random;

public class Dandelion extends Plant {
    public static final int INITIAL_QUANTITY = 2;
    public static final int STRENGTH = 0;
    public static final int INITIATIVE = 0;
    public static final char SYMBOL = 'D';

    private static final Random rand = new Random();

    public Dandelion(Point position, World world) {
        super(STRENGTH, SYMBOL, position, world);
        species = Species.DANDELION;
    }

    public Dandelion(World world) {
        super(STRENGTH, SYMBOL, world);
        species = Species.DANDELION;
    }

    @Override
    public void reproduce(Point position) {
        for (int i = 0; i < 3; i++) {
            Point newPosition = world.getRandomFreeSpaceAround(this.position);
            Plant plant = new Dandelion(newPosition, world);
            world.setOrganism(plant, newPosition);
        }
    }

    @Override
    public boolean canReproduceThisTurn() {
        return rand.nextInt(32) == 0;
    }
}
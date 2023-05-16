import java.awt.*;

public class Wolf extends PredatorAnimal {
    public static final int INITIAL_QUANTITY = 2;
    public static final int STRENGTH = 9;
    public static final int INITIATIVE = 5;
    public static final char SYMBOL = 'W';

    public Wolf(Point position, World world) {
        super(STRENGTH, INITIATIVE, SYMBOL, position, world);
        this.species = Species.WOLF;
    }

    public Wolf(World world) {
        super(STRENGTH, INITIATIVE, SYMBOL, world);
        this.species = Species.WOLF;
    }

    @Override
    public void reproduce(Point position) {
        Point freeSpace = world.getRandomFreeSpaceAround(position);

        if (freeSpace == null) {
            return;
        }

        Wolf newOrganism = new Wolf(freeSpace, world);
        world.setOrganism(newOrganism, freeSpace);

        String message = "Organism " + symbol + " reproduced at (" + position.x + ", " + position.y + ")";
        System.out.println(message);
        world.addTurnSummaryMessage(message);
    }
}
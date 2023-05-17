import java.awt.*;

public class Nightshade extends Plant {
    public static final int INITIAL_QUANTITY = 2;
    public static final int STRENGTH = 99;
    public static final char SYMBOL = 'N';

    public Nightshade(Point position, World world) {
        super(STRENGTH, SYMBOL, position, world);
        species = Species.NIGHTSHADE;
    }

    public Nightshade(World world) {
        super(STRENGTH, SYMBOL, world);
        species = Species.NIGHTSHADE;
    }

    @Override
    public void reproduce(Point position) {
        Point freeSpace = world.getRandomFreeSpaceAround(position);
        Organism newOrganism = new Nightshade(freeSpace, world);
        world.setOrganism(newOrganism, freeSpace);
        String message = "Organism " + symbol + " reproduced at (" + position.getX() + ", " + position.getY() + ")";
        world.addTurnSummaryMessage(message);
    }

    @Override
    public boolean collision(Organism other) {
        other.die();
        String message = "Organism " + other.getSymbol() + " ate nightshade at (" + position.getX() + ", " + position.getY() + ")";
        world.addTurnSummaryMessage(message);
        die();
        return true;
    }

    @Override
    public boolean canReproduceThisTurn() {
        return false;
    }

    @Override
    public boolean canKill(Organism other) {
        return true;
    }
}
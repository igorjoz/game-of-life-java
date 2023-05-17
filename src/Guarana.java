import java.awt.*;

public class Guarana extends Plant {
    public static final int INITIAL_QUANTITY = 2;
    public static final int STRENGTH = 0;
    public static final char SYMBOL = 'U';

    public Guarana(Point position, World world) {
        super(STRENGTH, SYMBOL, position, world);
        species = Species.GUARANA;
    }

    public Guarana(World world) {
        super(STRENGTH, SYMBOL, world);
        species = Species.GUARANA;
    }

    @Override
    public void reproduce(Point position) {
        Point freeSpace = world.getRandomFreeSpaceAround(position);
        Organism newOrganism = new Guarana(freeSpace, world);
        world.setOrganism(newOrganism, freeSpace);
        String message = "Organism " + symbol + " reproduced at (" + position.getX() + ", " + position.getY() + ")";
        world.addTurnSummaryMessage(message);
    }

    @Override
    public boolean collision(Organism other) {
        if (canBeKilledBy(other)) {
            other.setStrength(other.getStrength() + 3);
            String message = "Organism " + other.getSymbol() + " ate guarana at (" + position.getX() + ", " + position.getY() + ")";
            world.addTurnSummaryMessage(message);
            die();
            return true;
        }

        return false;
    }
}
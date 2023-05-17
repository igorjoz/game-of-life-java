import java.awt.*;
import java.util.Random;

public class Plant extends Organism {
    public static final int INITIATIVE = 0;
    private static final Random RANDOM = new Random();

    public Plant(char symbol, Point position, World world) {
        super(0, INITIATIVE, symbol, position, world);
        species = Species.PLANT;
    }

    public Plant(int strength, char symbol, Point position, World world) {
        super(strength, INITIATIVE, symbol, position, world);
        species = Species.PLANT;
    }

    public Plant(int strength, char symbol, World world) {
        super(strength, INITIATIVE, symbol, world);
        species = Species.PLANT;
    }

    @Override
    public void action() {
        if (canReproduceThisTurn() && hasFreeSpace()) {
            Point position = world.getRandomFreeSpaceAround(this.position);
            reproduce(position);
        }
    }

    @Override
    public boolean collision(Organism other) {
        return false;
    }

    @Override
    public void die() {
        world.remove(position);
    }

    @Override
    public void kill(Organism other) {
        other.die();
        String message = "Organism " + other.getSymbol() + " was killed by " + symbol + " at (" + position.x + ", " + position.y + ")";
        world.addTurnSummaryMessage(message);
    }

    @Override
    public boolean canKill(Organism other) {
        return false;
    }

    @Override
    public boolean canBeKilledBy(Organism other) {
        if (this.getClass() == other.getClass()) {
            return false;
        }
        return other.getStrength() >= strength;
    }

    public boolean hasFreeSpace() {
        return world.hasFreeSpace(position);
    }

    public boolean canReproduceThisTurn() {
        return (RANDOM.nextInt(12) == 0);
    }

    @Override
    public void reproduce(Point position) {
    }
}
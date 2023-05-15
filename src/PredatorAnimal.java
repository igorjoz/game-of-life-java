import java.awt.*;
import java.util.Random;

public abstract class PredatorAnimal extends Animal {

    public PredatorAnimal(int strength, int initiative, char symbol, Point position, World world) {
        super(strength, initiative, symbol, position, world);
    }

    public PredatorAnimal(int strength, int initiative, char symbol, World world) {
        super(strength, initiative, symbol, world);
    }

    @Override
    public void action() {
        Point destination = world.getRandomNeighbour(position);

        if (!world.isWithinBoardBoundaries(destination)) {
            return;
        }

        if (world.isOccupied(destination)) {
            Organism other = world.getOrganismAt(destination);

            collision(other);
        }

        if (!world.isOccupied(destination) && canMoveTo(destination)) {
            move(destination);
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
            other.kill(this);
        }

        return false;
    }

    @Override
    public void reproduce(Point position) {
        Random rand = new Random();

        if (rand.nextInt(2) == 0) {
            return;
        }

        super.reproduce(position);
    }
}
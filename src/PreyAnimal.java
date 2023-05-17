import java.awt.*;

public abstract class PreyAnimal extends Animal {
    public PreyAnimal(int strength, int initiative, char symbol, Point position, World world) {
        super(strength, initiative, symbol, position, world);
    }

    public PreyAnimal(int strength, int initiative, char symbol, World world) {
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

            return;
        }

        if (canMoveTo(destination)) {
            move(destination);

            return;
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
            other.collision(this);

            return true;
        }

        return false;
    }
}
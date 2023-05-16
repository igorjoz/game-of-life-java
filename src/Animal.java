import java.awt.*;
import java.util.*;

public class Animal extends Organism {

    public Animal(int strength, int initiative, char symbol, Point position, World world) {
        super(strength, initiative, symbol, position, world);
        this.isAnimal = true;
    }

    public Animal(int strength, int initiative, char symbol, World world) {
        super(strength, initiative, symbol, world);

        this.isAnimal = true;
    }

    public void action() {
        Point destination = world.getRandomNeighbour(position);

        if (!world.isWithinBoardBoundaries(destination)) {
            return;
        }

        if (canMoveTo(destination)) {
            move(destination);
            return;
        }

        if (world.isOccupied(destination)) {
            Organism other = world.getOrganismAt(destination);
            collision(other);
        }
    }

    public boolean collision(Organism other) {
        if (canKill(other)) {
            kill(other);
            return true;
        }

        //else {
        //    other.collision(this);
        //}

        if (canReproduce(other, position)) {
            reproduce(position);
        }

        return false;
    }

    protected void move(Point destination) {
        world.move(position, destination);
        position = destination;
    }

    public void kill(Organism other) {
        other.die();

        String message = "Organism " + other.getSymbol() + " was killed by " + symbol + " at (" + position.getX() + ", " + position.getY() + ")";
        world.addTurnSummaryMessage(message);
    }

    public void reproduce(Point position) {
        String message = "Organism " + symbol + " reproduced at (" + position.getX() + ", " + position.getY() + ")";
        world.addTurnSummaryMessage(message);
    }

    public void die() {
        world.remove(position);
    }

    protected boolean canMoveTo(Point destination) {
        boolean canMoveTo = world.isEmpty(destination);

        if (destination.getX() < 0 || destination.getX() >= world.getSize() || destination.getY() < 0 || destination.getY() >= world.getSize()) {
            canMoveTo = false;
        }

        return canMoveTo;
    }

    public boolean canKill(Organism other) {
        if (this.getClass() == other.getClass()) {
            return false;
        }

        return other.canBeKilledBy(this);
    }

    public boolean canBeKilledBy(Organism other) {
        if (this.getClass() == other.getClass()) {
            return false;
        }

        return strength < other.getStrength();
    }

    protected boolean canReproduce(Organism other, Point position) {
        if (this.getClass() == other.getClass() && world.hasFreeSpace(position)) {
            return true;
        }

        return false;
    }
}
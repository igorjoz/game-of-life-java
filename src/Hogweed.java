import java.awt.*;

public class Hogweed extends Plant {
    public static final int INITIAL_QUANTITY = 2;
    public static final int STRENGTH = 0;
    public static final char SYMBOL = 'P';

    public Hogweed(Point position, World world) {
        super(STRENGTH, SYMBOL, position, world);
        species = Species.HOGWEED;
    }

    public Hogweed(World world) {
        super(STRENGTH, SYMBOL, world);
        species = Species.HOGWEED;
    }

    @Override
    public boolean canKill(Organism other) {
        return true;
    }

    @Override
    public boolean canReproduceThisTurn() {
        return false;
    }

    @Override
    public void action() {
        for(int dx = -1; dx <= 1; dx++) {
            for(int dy = -1; dy <= 1; dy++) {
                if(dx == 0 && dy == 0)
                    continue;

                Point destination = new Point(position.x + dx, position.y + dy);

                if(world.isWithinBoardBoundaries(destination) && world.isOccupied(destination)) {
                    Organism organism = world.getOrganismAt(destination);
                    if (organism != null) {
                        kill(organism);
                    }
                }
            }
        }
    }

    @Override
    public boolean collision(Organism other) {
        kill(other);
        return true;
    }
}
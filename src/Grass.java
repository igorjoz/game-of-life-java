import java.awt.*;

public class Grass extends Plant {
    public static final int INITIAL_QUANTITY = 3;
    public static final int STRENGTH = 0;
    public static final char SYMBOL = 'G';

    public Grass(World world) {
        super(STRENGTH, SYMBOL, world);
        species = Species.GRASS;
    }

    public Grass(Point position, World world) {
        super(STRENGTH, SYMBOL, position, world);
        species = Species.GRASS;
    }

    @Override
    public void reproduce(Point position) {
        Grass child = new Grass(position, world);
        world.setOrganism(child, position);
    }
}
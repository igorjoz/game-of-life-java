import java.awt.*;

public class Human extends Animal {

    private PlayerAction playerAction;

    public static final int STRENGTH = 5;
    public static final int INITIATIVE = 4;
    public static final char SYMBOL = 'H';

    public Human(Point position, World world) {
        super(STRENGTH, INITIATIVE, SYMBOL, position, world);
        this.playerAction = PlayerAction.NONE;
        this.species = Species.HUMAN;
    }

    @Override
    public void action() {
        int x = position.x;
        int y = position.y;

        if (playerAction == PlayerAction.MOVE_UP) {
            y -= 1;
        } else if (playerAction == PlayerAction.MOVE_DOWN) {
            y += 1;
        } else if (playerAction == PlayerAction.MOVE_LEFT) {
            x -= 1;
        } else if (playerAction == PlayerAction.MOVE_RIGHT) {
            x += 1;
        }

        Point destination = new Point(x, y);

        if (!world.isWithinBoardBoundaries(destination)) {
            return;
        }

        if (world.isOccupied(destination)) {
            Organism other = world.getOrganismAt(destination);
            collision(other);
        }

        if (canMoveTo(destination)) {
            move(destination);
            //world.setPlayerPosition(x, y);
        }
    }

    @Override
    public boolean collision(Organism other) {
        if (canKill(other)) {
            kill(other);
            return true;
        }

        return false;
    }

    @Override
    public void draw() {
        // implement draw logic here
    }

    @Override
    public void die() {
        world.setIsPlayerAlive(false);
        super.die();
    }

    @Override
    public void reproduce(Point position) {
        // reproduction logic here
    }

    public PlayerAction getPlayerAction() {
        return playerAction;
    }

    public void setPlayerAction(PlayerAction playerAction) {
        this.playerAction = playerAction;
    }
}
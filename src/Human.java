import java.awt.*;

public class Human extends Animal {

    private PlayerAction playerAction;
    private boolean isAlive;

    public static final int STRENGTH = 5;
    public static final int INITIATIVE = 4;
    public static final char SYMBOL = 'H';

    public Human(Point position, World world) {
        super(STRENGTH, INITIATIVE, SYMBOL, position, world);

        this.playerAction = PlayerAction.NONE;
        this.species = Species.HUMAN;
        this.isAlive = true;
    }

    @Override
    public void action() {
        int x = position.x;
        int y = position.y;

        int specialAbilityDuration = world.getGame().getSpecialAbilityDuration();
        int random = (int) (Math.random() * 2);

        if (specialAbilityDuration > 2 || (specialAbilityDuration > 0 && random == 1)) {
            if (playerAction == PlayerAction.MOVE_UP) {
                y -= 2;
            } else if (playerAction == PlayerAction.MOVE_DOWN) {
                y += 2;
            } else if (playerAction == PlayerAction.MOVE_LEFT) {
                x -= 2;
            } else if (playerAction == PlayerAction.MOVE_RIGHT) {
                x += 2;
            }
        } else {
            if (playerAction == PlayerAction.MOVE_UP) {
                y--;
            } else if (playerAction == PlayerAction.MOVE_DOWN) {
                y++;
            } else if (playerAction == PlayerAction.MOVE_LEFT) {
                x--;
            } else if (playerAction == PlayerAction.MOVE_RIGHT) {
                x++;
            }
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
    public void die() {
        isAlive = false;
        super.die();
    }

    @Override
    public void reproduce(Point position) {}

    public PlayerAction getPlayerAction() {
        return playerAction;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setPlayerAction(PlayerAction playerAction) {
        this.playerAction = playerAction;
    }
}
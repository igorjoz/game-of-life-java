import java.awt.Point;

public abstract class Organism {

    protected int strength;
    protected int initiative;
    protected int age;
    protected char symbol;
    protected boolean isAnimal;
    protected Point position;
    protected World world;
    protected Species species;

    public Organism(World world) {
        this(0, 0, 'N', new Point(0, 0), world);
    }

    public Organism(int strength, int initiative, char symbol, Point position, World world) {
        this(strength, initiative, 0, symbol, position, world);
    }

    public Organism(int strength, int initiative, int age, char symbol, Point position, World world) {
        this.strength = strength;
        this.initiative = initiative;
        this.age = age;
        this.symbol = symbol;
        this.isAnimal = false;
        this.position = position;
        this.world = world;
        this.species = Species.ORGANISM;
    }

    public abstract void action();
    public abstract boolean collision(Organism other);
    public abstract void reproduce(Point position);
    public abstract void draw();
    public abstract void die();
    public abstract void kill(Organism other);
    public abstract boolean canKill(Organism other);
    public abstract boolean canBeKilledBy(Organism other);

    public void printInfo() {
        System.out.println("Symbol: " + symbol);
        System.out.println("Strength: " + strength);
        System.out.println("Initiative: " + initiative);
        System.out.println("Age: " + age);
        System.out.println("Position: (" + position.x + ", " + position.y + ")");
    }

    public void printShortInfo() {
        System.out.println(symbol + " (" + position.x + ", " + position.y + ")");
    }

    public int getStrength() {
        return strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getAge() {
        return age;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isAnimal() {
        return isAnimal;
    }

    public Point getPosition() {
        return position;
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public Species getSpecies() {
        return species;
    }

    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIsAnimal(boolean isAnimal) {
        this.isAnimal = isAnimal;
    }
}
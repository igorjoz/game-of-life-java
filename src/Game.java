public class Game {
    private GUI gui;
    private int size;

    public Game() {
    }

    public Game(GUI gui) {
        this.gui = gui;
        //this.size = gui.getMapSize();
    }

    public void runGame() {
        System.out.println("Map size: " + size);
    }

    public void initializeGame(int size) {
        this.size = size;

        // create 2D array of size x size
        // fill array with random numbers

        /*for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

            }
        }*/
    }
}
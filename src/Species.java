public enum Species {
    HUMAN(1),
    WOLF(2),
    FOX(3),
    TORTOISE(4),
    ANTELOPE(5),
    SHEEP(6),
    GRASS(7),
    GUARANA(8),
    DANDELION(9),
    NIGHTSHADE(10),
    HOGWEED(11),
    ORGANISM(12);
    private int value;

    private Species(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static Species fromInteger(int x) {
        switch(x) {
            case 1:
                return HUMAN;
            case 2:
                return WOLF;
            case 3:
                return FOX;
            case 4:
                return TORTOISE;
            case 5:
                return ANTELOPE;
            case 6:
                return SHEEP;
            case 7:
                return GRASS;
            case 8:
                return GUARANA;
            case 9:
                return DANDELION;
            case 10:
                return NIGHTSHADE;
            case 11:
                return HOGWEED;
            }
            
        return null;
    }
}

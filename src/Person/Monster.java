package Person;

public abstract class Monster extends Person implements Fight {
    protected static int experience = 3;
    protected static int power =  1;

    public Monster(String name) {
        super(name);
        hp = 5;
        skill = 5;
    }

}

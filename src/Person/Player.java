package Person;

public class Player extends Person {

    public Player(String name, int hp, int power, int skill, int experience, int gold) {
        super(name, hp, power, skill, experience, gold);
        Player.getLevel();
    }

}

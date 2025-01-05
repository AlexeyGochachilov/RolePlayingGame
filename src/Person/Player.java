package Person;

import java.util.Random;

public class Player extends Person implements Fight{

    public Player(String name) {
        super(name);
        hp = 10;
        skill = 10;
        experience = 10;
        power = 3;
        System.out.println("Player: " + name + " was created");
    }

    @Override
    public void attack(Person monster) {
        monster.damageFromPlayer();
    }

}

package Person;

public class Goblin extends Monster {

    public Goblin(String name) {
        super(name + " The Goblin");
    }

    @Override
    public void attack(Person player) {
        player.damageFromMonster();
    }
}

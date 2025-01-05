package Person;

public class Skeleton extends Monster {

    public Skeleton(String name) {
        super(name + " The Skeleton");
    }

    @Override
    public void attack(Person player) {
        player.damageFromMonster();
    }
}

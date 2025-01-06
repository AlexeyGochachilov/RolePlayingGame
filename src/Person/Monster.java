package Person;

public abstract class Monster extends Person {

    private int level = Player.getLevel();

    public Monster(String name, int hp, int power, int skill, int experience, int gold) {
        super(name, hp, power, skill, experience, gold);
        hp *= level;
        power *= level;
        skill *= level;
        experience *= level;
        gold *= level;
    }

    private int levelUpMonster() {
        if (level >= 2) {
            int random = (int) (Math.random() * 10);
            level = (level + random) > 3 ? level + 1 : level - 1;
        }
        return level;
    }
}


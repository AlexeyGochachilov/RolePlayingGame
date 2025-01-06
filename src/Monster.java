public abstract class Monster extends Person {

    public Monster(String name, int hp, int power, double skill, int experience, int gold, int level) {
        super(name, hp, power, skill, experience, gold, level);
    }

//    private int levelUpMonster() {
//        if (level >= 2) {
//            int random = (int) (Math.random() * 10);
//            level = (level + random) > 3 ? level + 1 : level - 1;
//        }
//        return level;
//    }
}


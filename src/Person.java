abstract public class Person implements Fight {

    private String name;
    private int hp;
    private int power;
    private double skill;
    private int experience;
    private int gold;
    private int level;
    private Items [] clothesItems = new Items[6];
    private Items [] backpack = new Items[20];

    public Person(String name, int hp, int power, double skill, int experience, int gold, int level) {
        this.name = name;
        this.hp = hp;
        this.power = power;
        this.skill = skill;
        this.experience = experience;
        this.gold = gold;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public double getSkill() {
        return skill;
    }

    public void setSkill(double skill) {
        this.skill = skill;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void checkExperience() {

        if (getExperience() / getLevel() >= 100) {
            System.out.println("Ты можешь увеличить уровень!");
        } else {
            System.out.println("У тебя не достаточно опыта :(");
        }
    }

    public void levelUp(World.PlayerLevelUp playerLevelUp) {

        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (getExperience() / getLevel() >= 100) {
                playerLevelUp.levelUpTrue();
                setLevel(level += 1);
                setExperience(0);
                setHp(getLevel() * getHp());
                setPower(getLevel() * getPower());
                setSkill(getLevel() / 2 * getSkill());
            } else {
                playerLevelUp.levelUpFalse();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Items[] getClothesItems() {
        return clothesItems;
    }

    public void setClothesItems(Items[] clothesItems) {
        this.clothesItems = clothesItems;
    }

    public Items[] getBackpack() {
        return backpack;
    }

    public void setBackpack(Items[] backpack) {
        this.backpack = backpack;
    }

    @Override
    public String toString() {
        return String.format("%s здоровье:%d", name, hp);
    }

    @Override
    public int attack() {

        if (skill < 30) {
            if (skill * 3 > getRandomValue()) return power;
            else return 0;
        } else if (skill >= 30) {
            if (skill > getRandomValue()) return power;
            else return 0;
        }else return 0;
    }

}
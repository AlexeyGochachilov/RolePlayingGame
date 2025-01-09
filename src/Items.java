import java.util.Objects;

abstract public class Items {

    private String name;
    private int hp;
    private int power;
    private double skill;
    private int experience;
    private int gold;

    public Items(String name, int hp, int power, double skill, int experience, int gold) {
        this.name = name;
        this.hp = hp;
        this.power = power;
        this.skill = skill;
        this.experience = experience;
        this.gold = gold;
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

    @Override
    public String toString() {
        return name + " " + gold + " gold";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return hp == items.hp && power == items.power && Double.compare(skill, items.skill) == 0
                && experience == items.experience && gold == items.gold && Objects.equals(name, items.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hp, power, skill, experience, gold);
    }
}

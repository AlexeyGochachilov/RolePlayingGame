package Person;

import java.util.Random;

abstract public class Person implements Fight{

    private String name;
    private int hp;
    private int power;
    private int skill;
    private int experience;
    private int gold;

    public Person(String name, int hp, int power, int skill, int experience, int gold) {
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

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
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

    @Override
    public String toString() {
        return String.format("%s здоровье:%d", name, hp);
    }

    @Override
    public int attack() {
         if (skill * 3 > getRandomValue()) return power;
        else return  0;
    }
//    protected boolean damageFromPlayer() {
//        int damage = 0;
//        int result = new Random().nextInt(100) + 1;
//        if (Player.getExperience() * 3 > result) {
//            if ((Player.getExperience() * 3) / result > 2) {
//                damage = Player.getPower() * 2;
//                hp -= damage;
//                if (hp <= 0) {
//                    destroyed = true;
//                    System.out.println("Monster " + name + " was destroyed");
//                    return true;
//                }
//                return false;
//            } else {
//                damage = Player.getPower();
//                hp -= damage;
//                if (hp <= 0) {
//                    destroyed = true;
//                    System.out.println("Monster " + name + " was destroyed");
//                    return true;
//                }
//            }
//            System.out.println("HP " + name + " = " + hp);
//        } else {
//            System.out.println("Player miss");
//        }
//        return false;
//    }
//
//    protected boolean damageFromMonster() {
//        int damage = 0;
//        int result = new Random().nextInt(100) + 1;
//        if (result < Monster.getExperience() * 3) {
//            damage = Monster.getPower();
//            hp -= damage;
//            if (hp <= 0) {
//                destroyed = true;
//                System.out.println("Player " + name + " was destroyed");
//                return true;
//            }
//            System.out.println("HP " + name + " = " + hp);
//        } else {
//            System.out.println("Monster miss");
//        }
//        return false;
//    }

}
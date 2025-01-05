package Person;

import java.util.Random;

abstract public class Person {

    private String name;
    private int hp;
    private int skill;
    private static int experience;
    private static int power;
    private boolean destroyed = false;

    public static void setPower(int power) {
        Person.power = power;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public static void setExperience(int experience) {
        Person.experience = experience;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getSkill() {
        return skill;
    }

    public static int getExperience() {
        return experience;
    }

    public static int getPower() {
        return power;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    protected boolean damageFromPlayer() {
        int damage = 0;
        int result = new Random().nextInt(100) + 1;
        if (Player.getExperience() * 3 > result) {
            if ((Player.getExperience() * 3) / result > 2) {
                damage = Player.getPower() * 2;
                hp -= damage;
                if (hp <= 0) {
                    destroyed = true;
                    System.out.println("Monster " + name + " was destroyed");
                    return true;
                }
                return false;
            } else {
                damage = Player.getPower();
                hp -= damage;
                if (hp <= 0) {
                    destroyed = true;
                    System.out.println("Monster " + name + " was destroyed");
                    return true;
                }
            }
            System.out.println("HP " + name + " = " + hp);
        } else {
            System.out.println("Player miss");
        }
        return false;
    }

    protected boolean damageFromMonster() {
        int damage = 0;
        int result = new Random().nextInt(100) + 1;
        if (result < Monster.experience * 3) {
            damage = Monster.power;
            hp -= damage;
            if (hp <= 0) {
                destroyed = true;
                System.out.println("Player " + name + " was destroyed");
                return true;
            }
            System.out.println("HP " + name + " = " + hp);
        } else {
            System.out.println("Monster miss");
        }
        return false;
    }

}
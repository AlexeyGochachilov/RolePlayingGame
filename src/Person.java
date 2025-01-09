import java.util.ArrayList;

abstract public class Person implements Fight {

    private String name;
    private int hp;
    private int power;
    private double skill;
    private int experience;
    private int gold;
    private int level;
    private ArrayList<Items> clothesAndWeaponsItems = new ArrayList<>(9);
    private ArrayList<Items> armour = new ArrayList<>();
    private ArrayList<Items> clothes = new ArrayList<>();
    private ArrayList<Items> rings = new ArrayList<>();
    private ArrayList<Items> weapons = new ArrayList<>();
    private ArrayList<Items> backpack = new ArrayList<>(20);

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
        if (experience > 100) {
            experience = 100;
        }
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

    public ArrayList<Items> getClothesAndWeaponsItems() {
        return clothesAndWeaponsItems;
    }

    public void setClothesAndWeaponsItems(ArrayList<Items> clothesAndWeaponsItems) {
        this.clothesAndWeaponsItems = clothesAndWeaponsItems;
    }

    public ArrayList<Items> getBackpack() {
        return backpack;
    }

    public void setBackpack(ArrayList<Items> backpack) {
        this.backpack = backpack;
    }

    public ArrayList<Items> getArmour() {
        return armour;
    }

    public void setArmour(ArrayList<Items> armour) {
        this.armour = armour;
    }

    public ArrayList<Items> getClothes() {
        return clothes;
    }

    public void setClothes(ArrayList<Items> clothes) {
        this.clothes = clothes;
    }

    public ArrayList<Items> getRings() {
        return rings;
    }

    public void setRings(ArrayList<Items> rings) {
        this.rings = rings;
    }

    public ArrayList<Items> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Items> weapons) {
        this.weapons = weapons;
    }

    public void itemsInBackpack() {
        int counter = 1;
        for (Items it : backpack) {
            System.out.println(counter + ": " + it.toString());
            counter++;
        }
    }

    public void clothesAndArmorOnPlayer() {
        int counter = 1;
        for (Items it : clothesAndWeaponsItems) {
            System.out.println(counter + ": " + it.getName());
            counter++;
        }
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

    public void getDressAndArmour(Items items, World.GetDress getDress) {

        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (backpack.contains(items)) {
                if (items instanceof Armour) {
                    armour.add(items);
                    if (armour.size() > 4) {
                        getArmour().remove(4);
                        getDress.GetDressFalse();
                    } else {
                        getDress.GetDressTrue();
                    }
                    backpack.remove(items);
                    clothesAndWeaponsItems.addAll(armour);
                    addPokazateli();
                } else if (items instanceof Clothes) {
                    clothes.add(items);
                    if (clothes.size() > 2) {
                        getClothes().remove(2);
                        getDress.GetDressFalse();
                    } else {
                        getDress.GetDressTrue();
                    }
                    backpack.remove(items);
                    clothesAndWeaponsItems.addAll(clothes);
                    addPokazateli();
                } else if (items instanceof Rings) {
                    rings.add(items);
                    if (rings.size() > 2) {
                        getRings().remove(2);
                        getDress.GetDressFalse();
                    } else {
                        getDress.GetDressTrue();
                    }
                    backpack.remove(items);
                    clothesAndWeaponsItems.addAll(rings);
                    addPokazateli();
                } else if (items instanceof Weapons) {
                    weapons.add(items);
                    if (weapons.size() > 1) {
                        getWeapons().remove(1);
                        getDress.GetDressFalse();
                    } else {
                        getDress.GetDressTrue();
                    }
                    backpack.remove(items);
                    clothesAndWeaponsItems.addAll(weapons);
                    addPokazateli();
                } else if (clothesAndWeaponsItems.size() > 9) {
                    getClothesAndWeaponsItems().remove(9);
                    getDress.GetDressFalse();
                }
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

    public void UnDress(Items items, World.UnDress unDress) {

        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (clothesAndWeaponsItems.contains(items)) {
                getClothesAndWeaponsItems().remove(items);
                if (backpack.size() <= 20) {
                    getBackpack().add(items);
                }
                addPokazateli();
                unDress.UnDressTrue();
            } else {
                unDress.UnDressFalse();
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

    public void addPokazateli() {
        for (Items it : getClothesAndWeaponsItems()) {
            setHp(getHp() + it.getHp());
            setPower(getPower() + it.getPower());
            setSkill(getSkill() + it.getSkill());
            setExperience(getExperience() + it.getExperience());
        }
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
        } else return 0;
    }

}
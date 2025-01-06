import Person.*;

import java.util.Random;

public class Battle {

    public void fight(Person player, Person monster, World.FightCallback fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("----Ход: " + turn + "----");
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, player, fightCallback);
                } else {
                    isFightEnded = makeHit(player, monster, fightCallback);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Boolean makeHit(Person defender, Person attacker, World.FightCallback fightCallback) {
        int hit = attacker.attack();
        if (attacker instanceof Player && hit != 0) {
            int xTwo = new Random().nextInt(100) + 1;
            if ((hit * 3) / xTwo >= 2) {
                hit = hit * 2;
            }
        }
        int defenderHealth = defender.getHp() - hit;
        if (hit != 0) {
            System.out.println(String.format("%s Нанес удар в %d единиц!", attacker.getName(), hit));
            System.out.println(String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth));
        } else {
            System.out.println(String.format("%s промахнулся!", attacker.getName()));
        }
        if (defenderHealth <= 0 && defender instanceof Player) {
            System.out.println("Извините, вы пали в бою...");
            fightCallback.fightLost();
            return true;
        } else if (defenderHealth <= 0) {
            System.out.println(String.format("Враг повержен! Вы получаете %d опыт и %d золота",
                    defender.getExperience(), defender.getGold()));
            attacker.setExperience(attacker.getExperience() + defender.getExperience());
            attacker.setGold(attacker.getGold() + defender.getGold());
            fightCallback.fightWin();
            return true;
        } else {
            defender.setHp(defenderHealth);
            return false;
        }
    }


//    String[][] nameOfMonster = new String[][]{{"Hoblin", "Voblin", "Doblin", "Blin", "Erick"},
//            {"Bonye", "WithOutSkin", "Dead", "Eleton", "Boris"}};
//
//    public void run() {
//        int name = new Random().nextInt(5);
//        int oneOfTwo = new Random().nextInt(2) + 1;
//        Goblin goblin = new Goblin(nameOfMonster[0][name]);
//        Skeleton skeleton = new Skeleton(nameOfMonster[1][name]);
//        Monster[] monsters = new Monster[]{goblin, skeleton};
//    }
}

import java.util.Random;

public class Battle {

    private static int turn;

    public void fight(Person player, Person monster, World.TrueOreFalse fightCallback) {

        turn = 1;
        boolean isFightEnded = false;
        while (!isFightEnded) {
            System.out.println("----Ход: " + turn + "----");
            if (turn++ % 2 != 0) {
                isFightEnded = makeHit(monster, player, fightCallback);
            } else {
                isFightEnded = makeHit(player, monster, fightCallback);
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean makeHit(Person defender, Person attacker, World.TrueOreFalse fightCallback) {
        int hit = attacker.attack(turn);
        if (turn > 5){
            hit = attacker.attack(turn * 2);
        }
        if (turn > 10){
            hit = attacker.attack(turn * 5);
        }
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
            System.out.println("Извините, вы пали в бою... ");
            fightCallback.falseFalse();
            return true;
        } else if (defenderHealth <= 0) {
            System.out.println(String.format("Враг повержен! Вы получаете %d опыт и %d золота",
                    defender.getExperience(), defender.getGold()));
            attacker.setExperience(attacker.getExperience() + defender.getExperience());
            attacker.setGold(attacker.getGold() + defender.getGold());
            if (attacker.getExperience() / attacker.getLevel() >= 100) {
                System.out.println();
                System.out.println("Ты можешь увеличить уровень!");
                System.out.println();
            }
            fightCallback.trueTrue();
            return true;
        } else {
            defender.setHp(defenderHealth);
            return false;
        }
    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class World {

    private static BufferedReader br;
    private static Person player = null;
    private static Battle battleScene = null;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battleScene = new Battle();
        System.out.println("Введите имя персонажа:");
        try {
            command(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printNavigation() {
        System.out.println("Управление происходит при помощи цифр, а так же слов да и нет:");
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Увеличить уровень");
        System.out.println("4. Выход");
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Player(string, 10, 5, 10, 90, 0, 1);
            System.out.println(String.format("Добро пожаловать в наш мир %s! Да пребудет с тобой удача!", player.getName()));
            printNavigation();
        }
        switch (string) {
            case "1": {
                System.out.println("Торговец еще не приехал");
                command(br.readLine());
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                commitLevelUp();
                printNavigation();
                command(br.readLine());
            case "4":
                System.out.println("Возвращайся за новыми сражениями...!");
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет": {
                printNavigation();
                command(br.readLine());
            }
        }
        command(br.readLine());
    }

    private static Person createMonster() {
        String[][] nameOfMonster = new String[][]{{"Hoblin", "Voblin", "Doblin", "Blin", "Erick"},
                {"Bonye", "WithOutSkin", "Dead", "Eleton", "Boris"}};
        int name = new Random().nextInt(5);
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin(nameOfMonster[0][name], 5 * player.getLevel(),
                1 * player.getLevel(), 10 * player.getLevel(),
                10 * player.getLevel(), 10 * player.getLevel(), player.getLevel());
        else return new Skeleton(nameOfMonster[1][name], 3 * player.getLevel(),
                1 * player.getLevel(), 5 * player.getLevel(),
                5 * player.getLevel(), 5 * player.getLevel(), player.getLevel());
    }

    private static void commitLevelUp() {
        player.checkExperience();
        player.levelUp(new PlayerLevelUp() {

            @Override
            public void levelUpTrue() {
                System.out.println("Твой Уровень увеличился, и стал:  " + +(player.getLevel() + 1) + " !");
            }

            @Override
            public void levelUpFalse() {
                System.out.println("Продолжай сражаться и копить опыт");
            }
        });
    }

    private static void commitFight() {
        battleScene.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось" +
                        " %d едениц здоровья.", player.getName(), player.getExperience(), player.getGold(), player.getHp()));
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {
                System.out.println("Возвращайся за новыми сражениями...!");
                System.exit(1);
            }
        });
    }

    interface FightCallback {
        void fightWin();

        void fightLost();
    }

    interface PlayerLevelUp {
        void levelUpTrue();

        void levelUpFalse();
    }
}

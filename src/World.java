import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class World {

    private static BufferedReader br;
    private static Person player = null;
    private static Battle battleScene = null;
    private static MerchantsShop merchantsShop = null;

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
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Увеличить уровень");
        System.out.println("4. Надеть предметы");
        System.out.println("5. Снять предметы");
        System.out.println("6. Использовать зелье");
        System.out.println("7. Испытания в подземелье с боссом");
        System.out.println("8. Выход");
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Player(string, 10, 5, 10, 0, 0, 1);
            System.out.println(String.format("Добро пожаловать в наш мир %s! Да пребудет с тобой удача!", player.getName()));
            System.out.println("Управление происходит при помощи цифр, а так же слов: да, нет, купить, продать:");
            printNavigation();
        }
        switch (string) {
            case "1":
                System.out.println("Взгляни на товары:");
                creatMerchantsShop().lookAtItems();
                System.out.println("Желаете что-то купить?");
                command(br.readLine());
                break;
            case "regbnm":
            case "купить":
                commitMerchantsShopBuy();
                printNavigation();
                break;
            case "ghjlfnm":
            case "продать":
                player.itemsInBackpack();
                commitMerchantsShopSell();
                printNavigation();
                break;
            case "2":
                commitFight();
                break;
            case "3":
                commitLevelUp();
                printNavigation();
                break;
            case "4":
                player.itemsInBackpack();
                commitGetDress();
                printNavigation();
                break;
            case "5":
                player.clothesAndArmorOnPlayer();
                commitUnDress();
                printNavigation();
                break;
            case "6":
                player.potionsInBackPack();
                commitUsePotion();
                printNavigation();
                break;
            case "7":
                commitFightWithBoss();
                break;
            case "8":
                System.out.println("Возвращайся за новыми сражениями...!");
                System.exit(1);
                break;
            case "lf":
            case "да":
                command("2");
                break;
            case "ytn":
            case "нет": {
                printNavigation();
                command(br.readLine());
            }
            default:
                if (string.equals(player.getName())) {
                    break;
                } else {
                    System.out.println(player.getName() + " Вы ввели не подходящий символ");
                }
        }
        command(br.readLine());
    }

    private static Person createBoss() {
        Person boss = createMonster();
        boss.setSkill(player.getSkill());
        boss.setHp(100 * player.getLevel());
        boss.setExperience(player.getExperience());
        boss.setGold(100 * player.getLevel());
        boss.setPower(player.getPower());
        boss.setName("Повелитель подземелья " + boss.getName());
        return boss;
    }

    private static Person createMonster() {
        String[][] nameOfMonster = new String[][]{{"Hoblin", "Voblin", "Doblin", "Blin", "Erick"},
                {"Bonye", "WithOutSkin", "Dead", "Eleton", "Boris"}, {"Fire", "Sunshine", "Extinguisher", "Fly", "Zed"}};
        int name = new Random().nextInt(5);
        int random = (int) (Math.random() * 100);
        if (random % 2 == 0) return new Goblin("Goblin " + nameOfMonster[0][name], 5 * player.getLevel(),
                1 * player.getLevel(), 10 * player.getLevel(),
                10 * player.getLevel(), 10 * player.getLevel(), player.getLevel());
        else if (random % 3 == 0) return new Dragon("Dragon " + nameOfMonster[2][name], player.getHp(),
                4 * player.getLevel(), 10 * player.getLevel(),
                10 * player.getLevel(), 15 * player.getLevel(), player.getLevel());
        else return new Skeleton("Skeleton " + nameOfMonster[1][name], 3 * player.getLevel(),
                    1 * player.getLevel(), 5 * player.getLevel(),
                    5 * player.getLevel(), 5 * player.getLevel(), player.getLevel());
    }

    private static MerchantsShop creatMerchantsShop() {

        merchantsShop = new MerchantsShop();
        merchantsShop.getItemsMerchantsShop().add(new Clothes("Pants", 1, 1, 1, 0, 1));
        merchantsShop.getItemsMerchantsShop().add(new Clothes("Shirt", 1, 1, 1, 0, 1));
        merchantsShop.getItemsMerchantsShop().add(new Rings("RingOfpower", 0, 1, 0, 0, 1));
        merchantsShop.getItemsMerchantsShop().add(new Rings("RingOfhp", 1, 0, 0, 0, 1));
        merchantsShop.getItemsMerchantsShop().add(new Armour("Helmet", 1, 1, 1, 0, 3));
        merchantsShop.getItemsMerchantsShop().add(new Armour("Gloves", 1, 0, 0, 0, 1));
        merchantsShop.getItemsMerchantsShop().add(new Armour("Cuirass", 1, 1, 1, 0, 3));
        merchantsShop.getItemsMerchantsShop().add(new Armour("Boot", 1, 1, 0, 0, 2));
        merchantsShop.getItemsMerchantsShop().add(new Weapons("SwordAndShield", 1, 1, 0, 0, 1));
        merchantsShop.getItemsMerchantsShop().add(new Weapons("BigSword", 1, 2, 0, 0, 1));
        merchantsShop.getItemsMerchantsShop().add(new Weapons("BowAndArrows", 0, 1, 2, 0, 1));
        merchantsShop.getItemsMerchantsShop().add(new Potions("PotionHP", 1, 0, 0, 0, 1));
        merchantsShop.getItemsMerchantsShop().add(new Potions("PotionMaxHP", 10, 0, 0, 0, 10));
        merchantsShop.getItemsMerchantsShop().add(new Potions("PotionPower", 0, 1, 0, 0, 1));
        merchantsShop.getItemsMerchantsShop().add(new Potions("PotionMaxPower", 0, 10, 0, 0, 10));
        merchantsShop.getItemsMerchantsShop().add(new Potions("PotionSkill", 0, 0, 1, 0, 1));
        merchantsShop.getItemsMerchantsShop().add(new Potions("PotionMaxSkill", 0, 0, 10, 0, 10));

        return merchantsShop;
    }

    private static void commitMerchantsShopBuy() {

        System.out.println("Введи номер товара:");
        try {
            creatMerchantsShop().buyItems(player, creatMerchantsShop().getItemsMerchantsShop().get(Integer.valueOf(br.readLine()) - 1),
                    new TrueOreFalse() {
                        @Override
                        public void trueTrue() {
                            System.out.println(player.getName() + " ты удачно купил " +
                                    player.getBackpack().get(player.getBackpack().size() - 1).getName());
                            System.out.println(player.getName() + " у тебя осталось золота " + player.getGold());
                        }

                        @Override
                        public void falseFalse() {
                            System.out.println("Покупка не возможна :(");
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void commitMerchantsShopSell() {

        if (!player.getBackpack().isEmpty()) {
            System.out.println("Введи номер предмета:");
            try {
                player.sellItems(player.getBackpack().get(Integer.valueOf(br.readLine()) - 1),
                        new TrueOreFalse() {

                            @Override
                            public void trueTrue() {
                                System.out.println(player.getName() + " ты удачно продал свою вещь...");
                            }

                            @Override
                            public void falseFalse() {
                                System.out.println(player.getName() + " у тебя нет этого предмета ;)");
                            }
                        });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else System.out.println(player.getName() + " тебе нечего продавать :(");
    }

    private static void commitLevelUp() {

        player.levelUp(new TrueOreFalse() {

            @Override
            public void trueTrue() {
                System.out.println("Твой Уровень увеличился, и стал:  " + +(player.getLevel() + 1) + " !");
            }

            @Override
            public void falseFalse() {
                System.out.println("Продолжай сражаться и копить опыт");
            }
        });
    }

    private static void commitFightWithBoss() {

        battleScene.fight(player, createBoss(), new TrueOreFalse() {

            @Override
            public void trueTrue() {
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
            public void falseFalse() {
                System.out.println("Возвращайся за новыми сражениями...!");
                System.exit(1);
            }
        });
    }

    private static void commitFight() {

        battleScene.fight(player, createMonster(), new TrueOreFalse() {

            @Override
            public void trueTrue() {
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
            public void falseFalse() {
                System.out.println("Возвращайся за новыми сражениями...!");
                System.exit(1);
            }
        });
    }

    private static void commitGetDress() {

        if (!player.getBackpack().isEmpty()) {
            System.out.println("Введи номер предмета:");
            try {
                player.getDressAndArmour(player.getBackpack().get(Integer.valueOf(br.readLine()) - 1), new TrueOreFalse() {
                    @Override
                    public void trueTrue() {
                        System.out.println(player.getName() + " ты удачно надел " + player.getClothesAndWeaponsItems().
                                get(player.getClothesAndWeaponsItems().size() - 1).getName());
                    }

                    @Override
                    public void falseFalse() {
                        System.out.println("Попытка надеть предмет не увенчалась успехом");
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else System.out.println("Попытка надеть предмет не увенчалась успехом");
    }

    private static void commitUnDress() {

        if (!player.getClothesAndWeaponsItems().isEmpty()) {
            System.out.println("Введи номер предмета:");
            try {
                player.UnDress(player.getClothesAndWeaponsItems().get(Integer.valueOf(br.readLine()) - 1), new TrueOreFalse() {
                    @Override
                    public void trueTrue() {
                        System.out.println(player.getName() + ", ты удачно снял " + player.getBackpack().
                                get(player.getBackpack().size() - 1).getName());
                    }

                    @Override
                    public void falseFalse() {
                        System.out.println("Ты потерял этот предмет... :(");
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else System.out.println(player.getName() + " тебе нечего снимать");
    }

    private static void commitUsePotion() {

        if (!player.getBackpack().isEmpty()) {
            try {
                player.usePotions(player.getBackpack().get(Integer.valueOf(br.readLine()) - 1), new TrueOreFalse() {
                    @Override
                    public void trueTrue() {
                        System.out.println(String.format("%s, ты удачно использовал зелье!", player.getName()));
                    }

                    @Override
                    public void falseFalse() {
                        System.out.println("зелье не использовано :(");
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else System.out.println(String.format("%s, у тебя нет зелий", player.getName()));
    }

    interface TrueOreFalse {

        void trueTrue();

        void falseFalse();
    }

}

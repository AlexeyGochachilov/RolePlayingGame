import Person.Goblin;
import Person.Monster;
import Person.Player;
import Person.Skeleton;

import java.util.ArrayList;
import java.util.Random;

public class Battle extends Thread{

    String[][] nameOfMonster = new String[][]{{"Hoblin", "Voblin", "Doblin", "Blin", "Erick"},
            {"Bonye", "WithOutSkin", "Dead", "Eleton", "Boris"}};

    @Override
    public void run(){
        int name = new Random().nextInt(5);
        int oneOfTwo = new Random().nextInt(2) + 1;
        Goblin goblin = new Goblin(nameOfMonster[0][name]);
        Skeleton skeleton = new Skeleton(nameOfMonster[1][name]);
        Monster[] monsters = new Monster[]{goblin, skeleton};
    }
}

import Person.*;

import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String name = scanner.nextLine();
            Player player = new Player(name);
        }
    }
}
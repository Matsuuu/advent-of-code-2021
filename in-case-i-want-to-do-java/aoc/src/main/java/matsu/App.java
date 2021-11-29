package matsu;

import static matsu.Util.*;

public class App {
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("It works?");

        System.out.println(readFile("input1.txt"));
    }
}

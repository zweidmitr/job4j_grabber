package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {

    private static DirFileCache dirFile;

    public static DirFileCache dirCash(String dir) {
        return new DirFileCache(dir);
    }

    public static void main(String[] args) {
        boolean menu = true;
        while (menu) {
            Scanner console = new Scanner(System.in);
            System.out.println("1. write dir");
            System.out.println("2. load file");
            System.out.println("3. show file");
            System.out.println("4. exit");
            String answer = console.nextLine();

            if (answer.equals("1")) {
                System.out.println("write dir, please");
                String dir = console.nextLine();
                dirFile = dirCash(dir);
                continue;
            }
            if (answer.equals("2")) {
                System.out.println("write file name, please");
                String name = console.nextLine();
                dirFile.put(name, dirFile.load(name));
                continue;
            }
            if (answer.equals("3")) {
                System.out.println("write file name, please");
                String name = console.nextLine();
                System.out.println(dirFile.get(name));
                continue;
            }
            if (answer.equals("4")) {
                System.out.println("good day");
                menu = false;
            }
        }
     }
}

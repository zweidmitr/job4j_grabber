package ru.job4j.cache;

public class TestFile {
    public static void main(String[] args) {
        DirFileCache dir = new DirFileCache("Z:\\JAVA");
        dir.load("GIT.txt");
        System.out.println(dir.get("GIT.txt"));
    }
}

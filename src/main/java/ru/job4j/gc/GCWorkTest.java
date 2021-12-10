package ru.job4j.gc;

public class GCWorkTest {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        User zero = null;
        User ein = new User("one", 1);

        for (int i = 0; i < 5000; i++) {
            System.out.println(new User("N" + i, i));
        }
        info();

        int size = 8 * ((((3) * 2) + 45) / 8);
        System.out.println(size);
        /*
         * в х64 пустой объект занимает 8b
         *
         * string = 40 + 2*n (где n длина строки) = 46 не кратно 8, следовательно = 48
         * int = 4b
         *  = 56 (52 -> 56)
         * */

    }

}

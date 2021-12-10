package ru.job4j.gc;

public class User {
    private String password;
    private int countLimit;

    public User(String password, int countLimit) {
        this.password = password;
        this.countLimit = countLimit;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %s %d%n", password, countLimit);
    }

    @Override
    public String toString() {
        return "User{"
                + "password='" + password + '\''
                + ", countLimit=" + countLimit
                + '}';
    }
}

package models;

import java.util.Random;

public class Cat {
    private static final Random RANDOM = new Random();

    private String name;
    private int age;
    private int satiety;
    private int mood;
    private int health;
    private boolean actionPerformedToday;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        this.satiety = RANDOM.nextInt(61) + 20;
        this.mood = RANDOM.nextInt(61) + 20;
        this.health = RANDOM.nextInt(61) + 20;
        this.actionPerformedToday = false;
    }

    public Cat(String name, int age, int satiety, int mood, int health) {
        this.name = name;
        this.age = age;
        this.satiety = satiety;
        this.mood = mood;
        this.health = health;
        this.actionPerformedToday = false;
    }

    public void nextDay() {
        changeSatiety(-(RANDOM.nextInt(5) + 1));
        changeMood(RANDOM.nextInt(7) - 3);
        changeHealth(RANDOM.nextInt(7) - 3);
        this.actionPerformedToday = false;
    }

    public int getIncreaseStep() {
        if (age >= 1 && age <= 5) return 7;
        if (age >= 6 && age <= 10) return 5;
        return 4;
    }

    public int getDecreaseStep() {
        if (age >= 1 && age <= 5) return 3;
        if (age >= 6 && age <= 10) return 5;
        return 6;
    }

    public void changeSatiety(int delta) {
        this.satiety = Math.max(0, Math.min(100, this.satiety + delta));
    }

    public void changeMood(int delta) {
        this.mood = Math.max(0, Math.min(100, this.mood + delta));
    }

    public void changeHealth(int delta) {
        this.health = Math.max(0, Math.min(100, this.health + delta));
    }

    public void markActionDone() {
        this.actionPerformedToday = true;
    }

    public boolean hasActedToday() {
        return actionPerformedToday;
    }

    public int getAverageLevel() {
        return (satiety + mood + health) / 3;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public int getSatiety() { return satiety; }
    public int getMood() { return mood; }
    public int getHealth() { return health; }
}
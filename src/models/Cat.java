package models;

import strategies.age.*;
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
        return getAgeStrategy().getIncreaseStep();
    }

    public int getDecreaseStep() {
        return getAgeStrategy().getDecreaseStep();
    }

    private AgeStrategy getAgeStrategy() {
        if (age >= 1 && age <= 5) return new YoungCatStrategy();
        if (age >= 6 && age <= 10) return new AdultCatStrategy();
        return new SeniorCatStrategy();
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
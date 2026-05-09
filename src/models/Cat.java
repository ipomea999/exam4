package models;

import java.util.Random;

public class Cat {
    private String name;
    private int age;
    private int satiety;
    private int mood;
    private int health;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;

        Random random = new Random();
        this.satiety = random.nextInt(61) + 20;
        this.mood = random.nextInt(61) + 20;
        this.health = random.nextInt(61) + 20;
    }

    public Cat(String name, int age, int satiety, int mood, int health) {
        this.name = name;
        this.age = age;
        this.satiety = satiety;
        this.mood = mood;
        this.health = health;
    }

    public int getAverageLevel() {
        return (satiety + mood + health) / 3;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public int getSatiety() { return satiety; }
    public int getMood() { return mood; }
    public int getHealth() { return health; }

    public void setSatiety(int satiety) { this.satiety = satiety; }
    public void setMood(int mood) { this.mood = mood; }
    public void setHealth(int health) { this.health = health; }
}
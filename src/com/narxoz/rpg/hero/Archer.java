package com.narxoz.rpg.hero;

public class Archer implements Hero {
    private String name;
    private int health;
    private int power;

    public Archer(String name) {
        this.name = name;
        this.health = 90;
        this.power = 18;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void receiveDamage(int amount) {
        health -= amount;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}
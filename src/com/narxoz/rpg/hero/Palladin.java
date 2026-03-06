package com.narxoz.rpg.hero;

public class Palladin implements Hero {
    private String name;
    private int health = 110;
    private int power = 17;

    public Palladin(String name) {
        this.name = name;
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
        if (health < 0) {
            health = 0;
        }
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}

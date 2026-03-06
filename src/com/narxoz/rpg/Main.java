package com.narxoz.rpg;

import com.narxoz.rpg.adapter.EnemyCombatantAdapter;
import com.narxoz.rpg.adapter.HeroCombatantAdapter;
import com.narxoz.rpg.battle.BattleEngine;
import com.narxoz.rpg.battle.Combatant;
import com.narxoz.rpg.battle.EncounterResult;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.enemy.Orc;
import com.narxoz.rpg.enemy.Skeeleton;
import com.narxoz.rpg.enemy.Troll;
import com.narxoz.rpg.hero.Archer;
import com.narxoz.rpg.hero.Mage;
import com.narxoz.rpg.hero.Palladin;
import com.narxoz.rpg.hero.Warrior;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG Battle Engine Demo ===\n");


        Warrior warrior = new Warrior("Arthas");
        Mage mage = new Mage("Jaina");
        Archer archer = new Archer("Luchnicsa");
        Palladin palladin =new Palladin("Qwer");


        Goblin goblin = new Goblin();
        Orc orc = new Orc();
        Skeeleton skeeleton =new Skeeleton();
        Troll troll=new Troll();



        List<Combatant> heroes = new ArrayList<>();
        heroes.add(new HeroCombatantAdapter(warrior));
        heroes.add(new HeroCombatantAdapter(mage));
        heroes.add(new HeroCombatantAdapter(archer));
        heroes.add(new HeroCombatantAdapter(palladin));

        List<Combatant> enemies = new ArrayList<>();
        enemies.add(new EnemyCombatantAdapter(goblin));
        enemies.add(new EnemyCombatantAdapter(orc));
        enemies.add(new EnemyCombatantAdapter(skeeleton));
        enemies.add(new EnemyCombatantAdapter(troll));


        BattleEngine engineA = BattleEngine.getInstance();
        BattleEngine engineB = BattleEngine.getInstance();
        System.out.println("Same instance? " + (engineA == engineB));
        System.out.println();


        engineA.setRandomSeed(42L);
        EncounterResult result = engineA.runEncounter(heroes, enemies);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        for (String line : result.getBattleLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== Demo Complete ===");
    }
}

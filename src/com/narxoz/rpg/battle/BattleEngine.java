package com.narxoz.rpg.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class BattleEngine {
    private static BattleEngine instance;
    private Random random = new Random(1L);

    private BattleEngine() {
    }

    public static BattleEngine getInstance() {
        if (instance == null) {
            instance = new BattleEngine();
        }
        return instance;
    }

    public BattleEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public void reset() {
        instance = null;
    }

    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        EncounterResult result = new EncounterResult();

        List<Combatant> aTeam = new ArrayList<>(teamA);
        List<Combatant> bTeam = new ArrayList<>(teamB);

        int round = 0;

        while (!aTeam.isEmpty() && !bTeam.isEmpty()) {
            round++;
            result.addLog("\n=== Round " + round + " ===");

            result.addLog("Team A attacks:");
            for (Combatant attacker : new ArrayList<>(aTeam)) {
                if (bTeam.isEmpty()) break;

                Combatant target = bTeam.get(0);
                int damage = attacker.getAttackPower();
                target.takeDamage(damage);

                result.addLog("  " + attacker.getName() + " hits " + target.getName() +
                        " for " + damage + " damage");

                if (!target.isAlive()) {
                    result.addLog("  X " + target.getName() + " died");
                    bTeam.remove(0);
                }
            }

            if (!bTeam.isEmpty()) {
                result.addLog("Team B attacks:");
                for (Combatant attacker : new ArrayList<>(bTeam)) {
                    if (aTeam.isEmpty()) break;

                    Combatant target = aTeam.get(0);
                    int damage = attacker.getAttackPower();
                    target.takeDamage(damage);

                    result.addLog("  " + attacker.getName() + " hits " + target.getName() +
                            " for " + damage + " damage");

                    if (!target.isAlive()) {
                        result.addLog("  X " + target.getName() + " died");
                        aTeam.remove(0);
                    }
                }
            }

            result.addLog("Status: Heroes (" + aTeam.size() + "), Enemies (" + bTeam.size() + ")");
        }

        if (aTeam.isEmpty() && bTeam.isEmpty()) {
            result.setWinner("Draw - everyone died");
        } else if (aTeam.isEmpty()) {
            result.setWinner("Enemies");
        } else {
            result.setWinner("Heroes");
        }

        result.setRounds(round);
        result.addLog("=== Battle finished ===");
        result.addLog("Winner: " + result.getWinner());

        return result;
    }
}

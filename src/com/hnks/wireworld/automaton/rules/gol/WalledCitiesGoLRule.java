package com.hnks.wireworld.automaton.rules.gol;

public class WalledCitiesGoLRule extends BaseGoLRule {
    @Override
    public String getID() {
        return "walledcities";
    }

    @Override
    public String getName() {
        return "Walled Cities";
    }

    protected boolean willSpawn(int friends) {
        return friends >= 4;
    }

    protected boolean willSurvive(int friends) {
        return friends >= 2 && friends <= 5;
    }
}

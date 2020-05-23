package com.hnks.wireworld.automaton.rules.gol;

public class TwoByTwoGoLRule extends BaseGoLRule {
    @Override
    public String getID() {
        return "2x2";
    }

    @Override
    public String getName() {
        return "2x2 (GoL)";
    }

    protected boolean willSpawn(int friends) {
        return friends == 3 || friends == 6;
    }

    protected boolean willSurvive(int friends) {
        return friends <= 2 || friends == 5;
    }
}

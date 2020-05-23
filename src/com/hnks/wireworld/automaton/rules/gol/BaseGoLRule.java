package com.hnks.wireworld.automaton.rules.gol;

import com.hnks.wireworld.automaton.AutomatonCell;
import com.hnks.wireworld.automaton.AutomatonSimulation;
import com.hnks.wireworld.automaton.rules.IAutomatonRule;

public class BaseGoLRule implements IAutomatonRule {
    @Override
    public String getID() {
        return "gol";
    }

    @Override
    public String getName() {
        return "Game of Life";
    }

    @Override
    public String toString() {
        return getName();
    }

    protected boolean willSpawn(int friends) {
        return friends == 3;
    }

    protected boolean willSurvive(int friends) {
        return friends == 2 || friends == 3;
    }

    @Override
    public void evolve(AutomatonSimulation sim, int x, int y, AutomatonCell[][] target) {
        AutomatonCell cell = sim.getCell(x, y);
        AutomatonCell targetCell = cell;

        int friends = sim.countNEmpty(x, y);

        if (cell == AutomatonCell.BLANK) {
            if (willSpawn(friends)) {
                targetCell = AutomatonCell.CABLE;
            }
        } else {
            if (!willSurvive(friends)) {
                targetCell = AutomatonCell.BLANK;
            }
        }

        target[x][y] = targetCell;
    }
}

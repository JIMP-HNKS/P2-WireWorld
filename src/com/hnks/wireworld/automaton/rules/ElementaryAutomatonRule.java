package com.hnks.wireworld.automaton.rules;

import com.hnks.wireworld.automaton.AutomatonCell;
import com.hnks.wireworld.automaton.AutomatonSimulation;
import com.hnks.wireworld.automaton.rules.IAutomatonRule;

public class ElementaryAutomatonRule implements IAutomatonRule {
    private int rule;

    public ElementaryAutomatonRule(int rule) {
        this.rule = rule;
    }

    @Override
    public String getName() {
        return "Rule " + rule;
    }

    @Override
    public String getID() {
        return "eca" + rule;
    }

    @Override
    public String toString() {
        return getName();
    }

    private int getBinaryCell(AutomatonSimulation sim, int x, int y) {
        return sim.getCell(x, y) == AutomatonCell.BLANK ? 0 : 1;
    }

    @Override
    public void evolve(AutomatonSimulation sim, int x, int y, AutomatonCell[][] target) {
        // Leave the top row alone
        if (y == 0) {
            target[x][y] = sim.getCell(x, y);
            return;
        }

        int currentPattern = getBinaryCell(sim, x + 1, y - 1) |
                             getBinaryCell(sim, x, y - 1) * 2 |
                             getBinaryCell(sim, x - 1, y - 1) * 4;
        int newState = (rule >> currentPattern) & 1;

        target[x][y] = newState == 1 ? AutomatonCell.CABLE : AutomatonCell.BLANK;
    }
}

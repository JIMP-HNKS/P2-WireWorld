package com.hnks.wireworld.rules;

import com.hnks.wireworld.automaton.AutomatonCell;
import com.hnks.wireworld.automaton.AutomatonSimulation;

public class WireWorldRule implements IAutomatonRule {
    @Override
    public String getID() {
        return "wireworld";
    }

    @Override
    public String getName() {
        return "WireWorld";
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public void evolve(AutomatonSimulation sim, int x, int y, AutomatonCell[][] target) {
        AutomatonCell cell = sim.getCell(x, y);
        AutomatonCell targetCell = AutomatonCell.BLANK;

        switch (cell) {
            case BLANK:
                break;
            case TAIL:
                targetCell = AutomatonCell.CABLE;
                break;
            case HEAD:
                targetCell = AutomatonCell.TAIL;
                break;
            default:
                int heads = sim.countHeads(x, y);

                if (heads == 1 || heads == 2)
                    targetCell = AutomatonCell.HEAD;
                else
                    targetCell = AutomatonCell.CABLE;

                break;
        }

        target[x][y] = targetCell;
    }
}

package com.hnks.wireworld.automaton.rules;

import com.hnks.wireworld.automaton.AutomatonCell;
import com.hnks.wireworld.automaton.AutomatonSimulation;

import java.util.Random;

public class PowderRule implements IAutomatonRule {
    private final Random rng = new Random();

    @Override
    public String getID() {
        return "powder";
    }

    @Override
    public String getName() {
        return "Powder";
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public void evolve(AutomatonSimulation sim, int x, int y, AutomatonCell[][] target) {
        AutomatonCell cell = sim.getCell(x, y);

        switch (cell) {
            case CABLE:
                target[x][y] = AutomatonCell.CABLE;
                break;
            case HEAD:
            case TAIL:
                if (y == sim.getHeight() - 1) { // Oops! Time to fall into the void!
                    target[x][y] = AutomatonCell.BLANK;
                } else if (sim.getCell(x, y + 1) == AutomatonCell.BLANK) {
                    target[x][y] = AutomatonCell.BLANK;
                    target[x][y + 1] = cell;
                } else {
                    if (
                            x > 1 && x < sim.getWidth() - 1 &&
                            sim.getCell(x - 1, y + 1) == AutomatonCell.BLANK &&
                            sim.getCell(x + 1, y + 1) == AutomatonCell.BLANK
                    ) {
                        int newX = x + rng.nextInt(2) * 2 - 1; // either to the left or to the right
                        target[x][y] = AutomatonCell.BLANK;
                        target[newX][y + 1] = cell;
                    } else if (
                            x > 1 &&
                            sim.getCell(x - 1, y + 1) == AutomatonCell.BLANK
                    ) {
                        target[x][y] = AutomatonCell.BLANK;
                        target[x - 1][y + 1] = cell;
                    } else if (
                            x < sim.getWidth() - 1 &&
                            sim.getCell(x + 1, y + 1) == AutomatonCell.BLANK
                    ) {
                        target[x][y] = AutomatonCell.BLANK;
                        target[x + 1][y + 1] = cell;
                    } else {
                        target[x][y] = cell;
                    }
                }
                break;
            default:
                target[x][y] = AutomatonCell.BLANK;
        }
    }
}

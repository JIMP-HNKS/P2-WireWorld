package com.hnks.wireworld.rules;

import com.hnks.wireworld.WireWorldCell;
import com.hnks.wireworld.WireWorldSimulation;

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
    public void evolve(WireWorldSimulation sim, int x, int y, WireWorldCell[][] target) {
        WireWorldCell cell = sim.getCell(x, y);
        WireWorldCell targetCell = WireWorldCell.BLANK;

        switch (cell) {
            case BLANK:
                break;
            case TAIL:
                targetCell = WireWorldCell.CABLE;
                break;
            case HEAD:
                targetCell = WireWorldCell.TAIL;
                break;
            default:
                int heads = sim.countHeads(x, y);

                if (heads == 1 || heads == 2)
                    targetCell = WireWorldCell.HEAD;
                else
                    targetCell = WireWorldCell.CABLE;

                break;
        }

        target[x][y] = targetCell;
    }
}

package com.hnks.wireworld;

import com.hnks.wireworld.rules.IAutomatonRule;
import com.hnks.wireworld.rules.WireWorldRule;

public class WireWorldState {
    private WireWorldSimulation sim;

    private boolean running;
    private int simCount;

    private boolean drawMode = true;
    private WireWorldCell drawCell = WireWorldCell.CABLE;

    private IAutomatonRule rule = new WireWorldRule();

    public WireWorldState(
            WireWorldSimulation sim,
            int simCount
    ) {
        this.sim = sim;
        this.simCount = simCount;
    }

    public void generateNext() {
        sim.generateNext(rule);
    }

    public int getSimCount() {
        return simCount;
    }

    public void setSimCount(int simCount) {
        this.simCount = simCount;
    }

    public boolean isDrawMode() {
        return drawMode;
    }

    public void setDrawMode(boolean drawMode) {
        this.drawMode = drawMode;
    }

    public WireWorldCell getDrawCell() {
        return drawCell;
    }

    public void setDrawCell(WireWorldCell drawCell) {
        this.drawCell = drawCell;
    }

    public IAutomatonRule getRule() {
        return rule;
    }

    public void setRule(IAutomatonRule rule) {
        this.rule = rule;
    }
}

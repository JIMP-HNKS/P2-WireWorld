package com.hnks.wireworld.gui;

import com.hnks.wireworld.automaton.AutomatonCell;
import com.hnks.wireworld.automaton.AutomatonSimulation;
import com.hnks.wireworld.rules.IAutomatonRule;
import com.hnks.wireworld.rules.WireWorldRule;

public class AppState {
    private AutomatonSimulation sim;

    private boolean running;
    private int simCount;

    private boolean drawMode = true;
    private AutomatonCell drawCell = AutomatonCell.CABLE;

    private IAutomatonRule rule = new WireWorldRule();

    public AppState(
            AutomatonSimulation sim,
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

    public AutomatonCell getDrawCell() {
        return drawCell;
    }

    public void setDrawCell(AutomatonCell drawCell) {
        this.drawCell = drawCell;
    }

    public IAutomatonRule getRule() {
        return rule;
    }

    public void setRule(IAutomatonRule rule) {
        this.rule = rule;
    }
}

package com.hnks.wireworld.gui;

import com.hnks.wireworld.automaton.AutomatonCell;
import com.hnks.wireworld.automaton.AutomatonSimulation;
import com.hnks.wireworld.rules.IAutomatonRule;
import com.hnks.wireworld.rules.WireWorldRule;

public class AppState {
    private AutomatonSimulation sim;

    private boolean running = false;
    private int simCount;

    private boolean drawMode = true;
    private AutomatonCell drawCell = AutomatonCell.CABLE;

    private IAutomatonRule rule;

    public AppState(
            AutomatonSimulation sim,
            int simCount,
            IAutomatonRule rule
    ) {
        this.sim = sim;
        this.simCount = simCount;
        this.rule = rule;
    }

    public AutomatonSimulation getSim(){
        return sim;
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

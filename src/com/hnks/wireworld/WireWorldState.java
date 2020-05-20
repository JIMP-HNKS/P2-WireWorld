package com.hnks.wireworld;

public class WireWorldState {
    private WireWorldSimulation sim;

    private boolean running;
    private int simCount;

    private boolean drawMode = true;
    private WireWorldCell drawCell = WireWorldCell.CABLE;

    public WireWorldState(
            WireWorldSimulation sim,
            int simCount
    ) {
        this.sim = sim;
        this.simCount = simCount;
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
}

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
}

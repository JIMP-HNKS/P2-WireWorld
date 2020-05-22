package com.hnks.wireworld.rules;

import com.hnks.wireworld.WireWorldCell;
import com.hnks.wireworld.WireWorldSimulation;

public interface IAutomatonRule {
    String getName();
    String getID();
    void evolve(WireWorldSimulation sim, int x, int y, WireWorldCell[][] target);
}

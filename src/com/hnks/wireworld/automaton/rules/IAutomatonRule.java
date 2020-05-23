package com.hnks.wireworld.automaton.rules;

import com.hnks.wireworld.automaton.AutomatonCell;
import com.hnks.wireworld.automaton.AutomatonSimulation;

public interface IAutomatonRule {
    String getName();
    String getID();
    void evolve(AutomatonSimulation sim, int x, int y, AutomatonCell[][] target);
}

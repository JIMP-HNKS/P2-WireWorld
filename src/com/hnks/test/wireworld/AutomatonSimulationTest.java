package com.hnks.test.wireworld;

import com.hnks.wireworld.automaton.AutomatonCell;
import com.hnks.wireworld.automaton.AutomatonSimulation;
import com.hnks.wireworld.automaton.prefabs.AutomatonPrefab;
import com.hnks.wireworld.automaton.prefabs.wwld.DiodeWWLDPrefab;
import com.hnks.wireworld.automaton.rules.WireWorldRule;

import static org.junit.jupiter.api.Assertions.*;

class AutomatonSimulationTest {

    @org.junit.jupiter.api.Test
    void getCell() {
        AutomatonSimulation sim = new AutomatonSimulation(10, 10);
        
        sim.setCell(AutomatonCell.CABLE, 4, 7);
        sim.setCell(AutomatonCell.HEAD, 0, 4);
        sim.setCell(AutomatonCell.TAIL, 9, 0);
        
        assertEquals(AutomatonCell.CABLE, sim.getCell(4, 7));
        assertEquals(AutomatonCell.HEAD, sim.getCell(0, 4));
        assertEquals(AutomatonCell.TAIL, sim.getCell(9, 0));
        assertEquals(AutomatonCell.BLANK, sim.getCell(0, 0));
    }

    @org.junit.jupiter.api.Test
    void countNEmpty() {
        AutomatonSimulation sim = new AutomatonSimulation(3, 3);

        sim.setCell(AutomatonCell.CABLE, 0, 0);
        sim.setCell(AutomatonCell.TAIL, 0, 1);
        sim.setCell(AutomatonCell.HEAD, 2, 2);
        sim.setCell(AutomatonCell.HEAD, 1, 2);

        assertEquals(4, sim.countNEmpty(1, 1));
    }

    @org.junit.jupiter.api.Test
    void countHeads() {
        AutomatonSimulation sim = new AutomatonSimulation(3, 3);

        sim.setCell(AutomatonCell.CABLE, 0, 0);
        sim.setCell(AutomatonCell.TAIL, 0, 1);
        sim.setCell(AutomatonCell.HEAD, 2, 2);
        sim.setCell(AutomatonCell.HEAD, 1, 2);

        assertEquals(2, sim.countHeads(1, 1));
    }

    @org.junit.jupiter.api.Test
    void generateNext() {
        AutomatonSimulation sim = new AutomatonSimulation(5, 5);
        AutomatonPrefab diode = new DiodeWWLDPrefab();
        WireWorldRule rule = new WireWorldRule();

        diode.place(sim, 1, 2);
        sim.setCell(AutomatonCell.HEAD, 0, 2);
        sim.setCell(AutomatonCell.CABLE, 4, 2);

        sim.generateNext(rule);
        assertEquals(AutomatonCell.TAIL, sim.getCell(0, 2));
        assertEquals(AutomatonCell.HEAD, sim.getCell(1, 2));
        sim.generateNext(rule);
        assertEquals(AutomatonCell.TAIL, sim.getCell(1, 2));
        assertEquals(AutomatonCell.HEAD, sim.getCell(2, 1));
        assertEquals(AutomatonCell.HEAD, sim.getCell(2, 2));
        assertEquals(AutomatonCell.HEAD, sim.getCell(2, 3));
    }
}
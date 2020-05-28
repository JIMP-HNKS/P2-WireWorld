package com.hnks.test.wireworld;

import com.hnks.wireworld.automaton.AutomatonCell;
import com.hnks.wireworld.automaton.AutomatonSimulation;
import com.hnks.wireworld.automaton.prefabs.AutomatonPrefab;
import com.hnks.wireworld.automaton.prefabs.gol.GliderGoLPrefab;

import static org.junit.jupiter.api.Assertions.*;

class AutomatonPrefabTest {

    @org.junit.jupiter.api.Test
    void place() {
        AutomatonPrefab glider = new GliderGoLPrefab();
        AutomatonSimulation sim = new AutomatonSimulation(5, 5);

        glider.place(sim, 2, 2);

        AutomatonCell[][] prefabCells = glider.getCells();

        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                assertEquals(prefabCells[x - 1][y - 1], sim.getCell(x, y));
            }
        }
    }
}
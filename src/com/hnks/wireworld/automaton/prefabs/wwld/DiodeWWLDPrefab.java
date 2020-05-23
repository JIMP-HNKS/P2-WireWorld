package com.hnks.wireworld.automaton.prefabs.wwld;

import com.hnks.wireworld.automaton.prefabs.AutomatonPrefab;

public class DiodeWWLDPrefab extends AutomatonPrefab {
    public DiodeWWLDPrefab() {
        super(
                "diode", 4, 3,
                new int[][]{
                        {0, 1, 1, 0},
                        {1, 1, 0, 1},
                        {0, 1, 1, 0},
                },
                0, 1
        );
    }
}

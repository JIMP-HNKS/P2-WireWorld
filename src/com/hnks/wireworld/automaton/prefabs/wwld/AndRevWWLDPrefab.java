package com.hnks.wireworld.automaton.prefabs.wwld;

import com.hnks.wireworld.automaton.prefabs.AutomatonPrefab;

public class AndRevWWLDPrefab extends AutomatonPrefab {
    public AndRevWWLDPrefab() {
        super(
                "-AND", 15, 8,
                new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
                        {1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                        {0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                },
                0, 0
        );
    }
}

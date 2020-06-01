package com.hnks.wireworld.automaton.prefabs.wwld;

import com.hnks.wireworld.automaton.prefabs.AutomatonPrefab;

public class AndWWLDPrefab extends AutomatonPrefab {
    public AndWWLDPrefab() {
        super(
                "AND", 15, 8,
                new int[][]{
                        {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                        {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
                        {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1},
                        {0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0},
                        {0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0},
                        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                },
                0, 0
        );
    }
}

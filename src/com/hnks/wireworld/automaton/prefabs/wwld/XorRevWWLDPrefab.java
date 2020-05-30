package com.hnks.wireworld.automaton.prefabs.wwld;

import com.hnks.wireworld.automaton.prefabs.AutomatonPrefab;

public class XorRevWWLDPrefab extends AutomatonPrefab {
    public XorRevWWLDPrefab() {
        super(
                "-XOR", 8, 7,
                new int[][]{
                        {0, 0, 0, 0, 1, 1, 0, 0},
                        {0, 0, 0, 1, 0, 0, 1, 0},
                        {0, 1, 1, 1, 1, 0, 0, 1},
                        {1, 1, 1, 0, 1, 0, 0, 0},
                        {0, 1, 1, 1, 1, 0, 0, 1},
                        {0, 0, 0, 1, 0, 0, 1, 0},
                        {0, 0, 0, 0, 1, 1, 0, 0},
                },
                0, 3
        );
    }
}

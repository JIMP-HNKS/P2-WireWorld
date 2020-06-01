package com.hnks.wireworld.automaton.prefabs.wwld;

import com.hnks.wireworld.automaton.prefabs.AutomatonPrefab;

public class XorWWLDPrefab extends AutomatonPrefab {

    public XorWWLDPrefab() {
        super(
                "XOR", 8, 7,
                new int[][]{
                        {0, 0, 1, 1, 0, 0, 0, 0},
                        {0, 1, 0, 0, 1, 0, 0, 0},
                        {1, 0, 0, 1, 1, 1, 1, 0},
                        {0, 0, 0, 1, 0, 1, 1, 1},
                        {1, 0, 0, 1, 1, 1, 1, 0},
                        {0, 1, 0, 0, 1, 0, 0, 0},
                        {0, 0, 1, 1, 0, 0, 0, 0},
                },
                0, 3
        );
    }
}

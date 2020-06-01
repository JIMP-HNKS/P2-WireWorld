package com.hnks.wireworld.automaton.prefabs.wwld;

import com.hnks.wireworld.automaton.prefabs.AutomatonPrefab;

public class OrRevWWLDPrefab extends AutomatonPrefab {

    public OrRevWWLDPrefab() {
        super(
                "-OR", 5, 5,
                new int[][]{
                        {0, 0, 1, 1, 0},
                        {0, 1, 0, 0, 1},
                        {1, 1, 1, 0, 0},
                        {0, 1, 0, 0, 1},
                        {0, 0, 1, 1, 0},
                },
                0, 2
        );
    }
}
package com.hnks.wireworld.automaton.prefabs.gol;

import com.hnks.wireworld.automaton.prefabs.AutomatonPrefab;

public class BlinkerGoLPrefab extends AutomatonPrefab {
    public BlinkerGoLPrefab() {
        super(
                "blinker", 3, 3,
                new int[][]{
                        {0, 0, 0},
                        {1, 1, 1},
                        {0, 0, 0},
                },
                1, 1
        );
    }
}

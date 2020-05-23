package com.hnks.wireworld.automaton.prefabs.gol;

import com.hnks.wireworld.automaton.prefabs.AutomatonPrefab;

public class GliderGoLPrefab extends AutomatonPrefab {
    public GliderGoLPrefab() {
        super(
                "glider", 3, 3,
                new int[][]{
                        {0, 1, 0},
                        {1, 0, 0},
                        {1, 1, 1}
                },
                1, 1
        );
    }
}

package com.hnks.wireworld.automaton.prefabs;

import com.hnks.wireworld.automaton.AutomatonCell;
import com.hnks.wireworld.automaton.AutomatonSimulation;

public class AutomatonPrefab {
    private final int width;
    private final int height;
    private AutomatonCell[][] cells;

    private final int baseX;
    private final int baseY;

    private String id;

    public AutomatonPrefab(String id, int width, int height, int data[][], int baseX, int baseY) {
        this.id = id;

        this.width = width;
        this.height = height;
        this.baseX = baseX;
        this.baseY = baseY;

        this.cells = new AutomatonCell[width][height];
        // 0 - blank, 1 - cable
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.cells[x][y] = data[y][x] == 1 ? AutomatonCell.CABLE : AutomatonCell.BLANK;
            }
        }
    }

    public String getId() {
        return id;
    }

    public void place(AutomatonSimulation sim, int offsetX, int offsetY) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                sim.setCell(
                        cells[x][y], x + offsetX - baseX, y + offsetY - baseY
                );
            }
        }
    }
}

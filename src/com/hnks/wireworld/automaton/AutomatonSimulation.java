package com.hnks.wireworld.automaton;

import com.hnks.wireworld.automaton.rules.IAutomatonRule;

import java.io.IOException;

public class AutomatonSimulation {
    private final int width;
    private final int height;
    private AutomatonCell[][] cells;

    public AutomatonSimulation(int width, int height) {
        this.width = width;
        this.height = height;

        this.cells = new AutomatonCell[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[x][y] = AutomatonCell.BLANK;
            }
        }

    }

    public int getHeight() {
        return height;
    }

    public AutomatonCell getCell(int x, int y) {
        if (x >= width) x = x % width;
        if (y >= height) y = y % height;
        if (x < 0) x += width;
        if (y < 0) y += height;

        return cells[x][y];
    }

    public void setCell(AutomatonCell cell, int x, int y) {
        if (x >= width) x = x % width;
        if (y >= height) y = y % height;
        if (x < 0) x += width;
        if (y < 0) y += height;

        cells[x][y] = cell;
    }

    public int countNEmpty(int x, int y) {
        int count = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                AutomatonCell cell = getCell(x + dx, y + dy);

                if (cell != AutomatonCell.BLANK) count++;
            }
        }

        return count;
    }

    public int countHeads(int x, int y) {
        int count = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                AutomatonCell cell = getCell(x + dx, y + dy);

                if (cell == AutomatonCell.HEAD) count++;
            }
        }

        return count;
    }


    public void evolveCell(int x, int y, AutomatonCell[][] target) {
        AutomatonCell cell = getCell(x, y);
        AutomatonCell targetCell = AutomatonCell.BLANK;

        switch (cell) {
            case BLANK:
                break;
            case TAIL:
                targetCell = AutomatonCell.CABLE;
                break;
            case HEAD:
                targetCell = AutomatonCell.TAIL;
                break;
            default:
                int heads = countHeads(x, y);

                if (heads == 1 || heads == 2)
                    targetCell = AutomatonCell.HEAD;
                else
                    targetCell = AutomatonCell.CABLE;

                break;
        }

        target[x][y] = targetCell;
    }

    public void generateNext(IAutomatonRule rule) {
        AutomatonCell[][] target = new AutomatonCell[width][height];

        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                rule.evolve(this, x, y, target);
            }
        }

        cells = target;
    }

    public void debugPrint() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String outChar = " ";

                switch (cells[x][y]) {
                    case CABLE:
                        outChar = "=";
                        break;
                    case HEAD:
                        outChar = "O";
                        break;
                    case TAIL:
                        outChar = "X";
                        break;
                    default:
                }

                System.out.print(outChar);
            }
            System.out.println("\n");
        }
    }
}

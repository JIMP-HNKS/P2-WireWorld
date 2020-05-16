import java.io.IOException;

class WireWorldSimulation {
    private int width, height;
    private WireWorldCell[][] cells;

    public WireWorldSimulation(int width, int height) {
        this.width = width;
        this.height = height;

        this.cells = new WireWorldCell[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[x][y] = WireWorldCell.BLANK;
            }
        }

    }

    public WireWorldCell getCell(int x, int y) {
        if (x >= width) x = x % width;
        if (y >= height) y = y % height;
        if (x < 0) x += width;
        if (y < 0) y += height;

        return cells[x][y];
    }

    public void setCell(WireWorldCell cell, int x, int y) {
        cells[x][y] = cell;
    }

    public int countHeads(int x, int y) {
        int count = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                WireWorldCell cell = getCell(x + dx, y + dy);

                if (cell == WireWorldCell.HEAD) count++;
            }
        }

        return count;
    }

    public static WireWorldSimulation loadFromFile(String path) throws IOException {
        // sad
        return null;
    }

    public void evolveCell(int x, int y, WireWorldCell[][] target) {
        WireWorldCell cell = getCell(x, y);
        WireWorldCell targetCell = WireWorldCell.BLANK;

        switch (cell) {
            case BLANK:
                break;
            case TAIL:
                targetCell = WireWorldCell.CABLE;
                break;
            case HEAD:
                targetCell = WireWorldCell.TAIL;
                break;
            default:
                int heads = countHeads(x, y);

                if (heads == 1 || heads == 2)
                    targetCell = WireWorldCell.HEAD;
                else
                    targetCell = WireWorldCell.CABLE;
                
                break;
        }

        target[x][y] = targetCell;
    }

    public void generateNext() {
        /*
            le rules

            sąsiedztwo:
            X X X
            X O X
            X X X

            pusta -> pusta
            głowa -> ogon
            ogon -> kabel
            1 lub 2 sąsiady są głowami -> głowa
            reszta -> kabel
        */
        WireWorldCell[][] target = new WireWorldCell[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                evolveCell(x, y, target);
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
            System.out.println("");
        }
    }

    public void saveToFile(String path) throws IOException {
        // sad
    }
}
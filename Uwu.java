enum WireWorldCell {
    blank, cable, head, tail
}

class WireWorldSimulation {
    private int width, height;
    private WireWorldCell[][] cells;

    public static Simulation loadFromFile(String path) throws IOException {}

    public void generateNext() {}
    public void saveToFile(String path) throws IOException {}
}

class WireWorldState {
    private Simulation sim;

    private boolean running;
    private int simCount;

    private boolean drawMode;
    private WireWorldCell drawCell;
}

class WireWorldCanvas extends JPanel {
    private WireWorldState state;
}

class WireWorldFrame extends JFrame {
    private WireWorldState state;
}
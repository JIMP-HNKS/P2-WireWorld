public class Main {
    public static void main(String[] args) {
        WireWorldSimulation sim = new WireWorldSimulation(7, 7);

        sim.setCell(WireWorldCell.TAIL, 0, 3);
        sim.setCell(WireWorldCell.HEAD, 1, 3);
        sim.setCell(WireWorldCell.CABLE, 2, 3);
        sim.setCell(WireWorldCell.CABLE, 5, 3);
        sim.setCell(WireWorldCell.CABLE, 6, 3);

        sim.setCell(WireWorldCell.CABLE, 3, 2);
        sim.setCell(WireWorldCell.CABLE, 4, 2);
        sim.setCell(WireWorldCell.CABLE, 3, 3);
        sim.setCell(WireWorldCell.CABLE, 3, 4);
        sim.setCell(WireWorldCell.CABLE, 4, 4);

        sim.debugPrint();
        sim.generateNext();
        sim.debugPrint();
        sim.generateNext();
        sim.debugPrint();
        sim.generateNext();
        sim.debugPrint();
        sim.generateNext();
        sim.debugPrint();
    }
}
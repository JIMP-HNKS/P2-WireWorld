package com.hnks.wireworld.rules.gol;

public class MazeGoLRule extends BaseGoLRule {
    @Override
    public String getID() {
        return "maze";
    }

    @Override
    public String getName() {
        return "Maze (GoL)";
    }

    protected boolean willSpawn(int friends) {
        return friends == 3;
    }

    protected boolean willSurvive(int friends) {
        return friends <= 5;
    }
}

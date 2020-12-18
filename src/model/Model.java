package model;

import model.AstroObject;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static List<AstroObject> AstroObjects = new ArrayList<>();
    private boolean paused;
    private double startAngle;
    private double startAngleMoon;
    private int moonLaps;




    public static List<AstroObject> getAstroObjects() {
        return AstroObjects;
    }

    public void setPaused() {
        paused = !paused;
    }
}

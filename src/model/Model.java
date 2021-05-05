package model;

import App.Application;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private List<IAstroObject> AstroObjects = new ArrayList<>();

    private IAstroObject Sun;
    private IAstroObject Earth;
    private IAstroObject Moon;

    private double startAngle;
    private double startAngleMoon;
    private int moonLaps;

    private static boolean paused;

    public Model() {
        Sun = AstroFactory.createSun();
        Earth = AstroFactory.createEarth();
        Moon = AstroFactory.createMoon();
        AstroObjects.add(Sun);
        AstroObjects.add(Earth);
        AstroObjects.add(Moon);

        startAngle = Math.atan2((Sun.getY()) - (Earth.getY()), (Sun.getX()) - (Earth.getX()));
        startAngleMoon = Math.atan2((Moon.getY()-Earth.getY()), (Moon.getX()-Earth.getX()));
        moonLaps = 0;

        paused = false;
    }

    public void update()
    {
        if (!paused) {
            for (IAstroObject a: AstroObjects) {
                a.move(AstroObjects);
            }
            controlAngle();
        }
    }

    public static void pause() { paused = !paused; }

    // Works best with timefactor =~ 1k due to Eulers step method.
    private void controlAngle() {
        double newAngle = Math.atan2((Sun.getY()) - (Earth.getY()), (Sun.getX()) - (Earth.getX()));
        double newAngleMoon = Math.atan2((Moon.getY() - Earth.getY()), (Moon.getX() - Earth.getX()));

        if (newAngle <= startAngle+0.0005 && newAngle >= startAngle-0.0005) {
            //pause();
            System.out.println("model.Earth has done a lap after "+ Application.getTimeCount()/86400+" days");
        }

        if (newAngleMoon <= startAngleMoon+0.0020 && newAngleMoon >= startAngleMoon-0.0020) {
            moonLaps++;
            System.out.println("Moon has done "+moonLaps+" laps after "+Application.getTimeCount()/86400+" days");
        }

    }

    public List<IAstroObject> getAstroObjects() {
        return AstroObjects;
    }

}

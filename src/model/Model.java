package model;

import App.Application;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private List<IAstroObject> AstroObjects = new ArrayList<>();
    private boolean paused;

    private IAstroObject Sun;
    private IAstroObject Earth;
    private IAstroObject Moon;

    private double startAngle;
    private double startAngleMoon;
    private int moonLaps;

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
    }

    public void update()
    {
        for (IAstroObject a: AstroObjects) {
            a.move(AstroObjects);
            //System.out.println(Astromath.toPixels(a.getX()));
        }
        controlAngle();
    }

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

    public void setPaused() {
        paused = !paused;
    }
}

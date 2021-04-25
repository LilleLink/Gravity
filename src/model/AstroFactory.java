package model;

import java.awt.*;

public class AstroFactory {

    private static double sunX = Astromath.toMeters(1080/2-16);
    private static double sunY = Astromath.toMeters(1920/2-30);
    private static double earthX = sunX - Astromath.ES_periphelion;
    private static double earthY = sunY;
    private static double moonX = earthX - Astromath.EM_aphelion;
    private static double moonY = earthY;


    static IAstroObject createSun() {
        return new AstroObject(Astromath.S_mass, sunX, sunY,
                Astromath.S_radius, new Color(255,255,0,0), 10);
    }

    static IAstroObject createEarth() {
        return new AstroObject(Astromath.E_mass, earthX, earthY, Astromath.E_radius,
                new Color(0,0,255,0), 5);
    }

    static IAstroObject createMoon() {
        return new AstroObject(Astromath.M_mass, moonX, moonY, Astromath.M_radius,
                new Color(255,255,255,0), 2);
    }
}

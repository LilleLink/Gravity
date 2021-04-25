package model;

import java.awt.*;
import java.util.List;

public interface IAstroObject {
    void move(List<IAstroObject> AstroObjects);
    void paint(Graphics g);
    void paintMe(Graphics g);
    double getY();
    double getX();
    double getMass();
}

package model;

import java.awt.*;

public interface IAstroObject {
    void move(List<IAstroObject> AstroObjects, Graphics g);
    void paint(Graphics g);
    void paintMe(Graphics g);
}

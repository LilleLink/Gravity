package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AstroObject implements Astromath, IAstroObject{
	
	protected double mass;
	protected double radius;

	private Color color;
	private int pixelSize;

	protected PVector position;
	protected PVector velocity;
	protected PVector acceleration;

	private List<Double> prevPositionsX = new ArrayList<>();
	private List<Double> prevPositionsY = new ArrayList<>();
	
	//Mainly used for custom objects
	public AstroObject(double mass, double x, double y, double radius, Color color, int pixelSize) {
		this.mass = mass;
		this.position = new PVector(x,y);
		this.velocity = new PVector(0,0);
		this.acceleration = new PVector(0,0);
		this.radius = radius;
		this.color = color;
		this.pixelSize = pixelSize;
	}

	
	public void trace(Graphics g) {
		if (view.GravitySim.getPaintFrame().getTimeCount() % 100000 == 0) {
			prevPositionsX.add(position.x);
			prevPositionsY.add(position.y);
		}

		g.setColor(color);

		for (double p : prevPositionsX) {
			int index = prevPositionsX.indexOf(p);
			g.fillRect((int) (p / Astromath.factor), (int) (prevPositionsY.get(index) / Astromath.factor), pixelSize, pixelSize);
		}
	}
	@Override
	public void move(List<AstroObject> AstroObjects, Graphics g) {
		for (AstroObject o : AstroObjects) {
			if (o != this) {

				double distance = Astromath.getDistance(this, o);
				double gForce = Astromath.getGForce(this.getMass(), o.getMass(), distance);
				double accelerationNoAngle = gForce / this.getMass();
				double angle = Math.atan2((o.getY()-this.getY()), (o.getX()-this.getX()));
				//double angle = Math.atan2((o.getCenterY()) - (this.getCenterY()),
				//(o.getCenterX()) - (this.getCenterX()));

				acceleration.x = accelerationNoAngle * Math.cos(angle);
				acceleration.y = accelerationNoAngle * Math.sin(angle);

				velocity.x += acceleration.x * Astromath.timeFactor;
				velocity.y += acceleration.y * Astromath.timeFactor;

			}
		}
		position.x += velocity.x * Astromath.timeFactor;
		position.y += velocity.y * Astromath.timeFactor;

	}

	@Override
	public void paint(Graphics g) {
		paintMe(g);
		trace(g);
	}

	@Override
	public void paintMe(Graphics g) {
		g.setColor(color);
		g.fillOval((int) (this.getCenterX() / Astromath.factor),
				(int) (this.getCenterY() / Astromath.factor), pixelSize, pixelSize);
		g.setColor(Color.white);
	}

	public double getMass() {
		return mass;
	}

	public double getRadius() {
		return radius;
	}

	public double getX() {
		return position.x;
	}

	public double getY() {
		return position.y;
	}

	public double getCenterY() {
		return position.y+this.getRadius();
	}

	public double getCenterX() {
		return position.x+this.getRadius();
	}

}

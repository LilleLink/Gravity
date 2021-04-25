package model;

import model.AstroObject;
import model.Astromath;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Earth extends AstroObject implements Astromath {

	private List<Double> prevPositionsX = new ArrayList<>();
	private List<Double> prevPositionsY = new ArrayList<>();

	public Earth(double realx, double realy) {
		super(Astromath.E_mass, realx, realy, Astromath.E_radius);
		this.velocity.y = Astromath.getVelocity(Astromath.ES_periphelion, Astromath.S_mass, Astromath.ES_SM_axis);
		
	}

	@Override
	public void updatePosition(List<AstroObject> AstroObjects, Graphics g) {
		super.move(AstroObjects, g);

		g.setColor(Color.red);
		g.fillOval((int) (this.getCenterX() / Astromath.factor),
				(int) (this.getCenterY() / Astromath.factor), 5, 5);
		g.setColor(Color.white);

	}

	public void trace(Graphics g) {
		if (GravitySim.getPaintFrame().getTimeCount() % 100000 == 0) {
			prevPositionsX.add(position.x);
			prevPositionsY.add(position.y);
		}

		g.setColor(Color.red);

		for (double p : prevPositionsX) {
			int index = prevPositionsX.indexOf(p);
			g.fillRect((int) (p / Astromath.factor), (int) (prevPositionsY.get(index) / Astromath.factor), 1, 1);
		}

	}

	@Override
	public double getMass() {
		return mass;
	}

	@Override
	public void setMass(double mass) {
		this.mass = mass;
	}

	public void print() {
		System.out.println(this.getClass().getSimpleName() + "\nPosition: (" + position.x + ";" + position.y + ")");
	}

	@Override
	public double getXVelocity() {
		return velocity.x;
	}

	@Override
	public double getYVelocity() {
		return velocity.y;
	}

	@Override
	public void setVelocity(double xv, double vy) {
		velocity.x = xv;
		velocity.y = vy;
	}

	@Override
	public double getXAcceleration() {
		return acceleration.x;
	}

	@Override
	public double getRadius() {
		return radius;
	}

	@Override
	public double getX() {
		return position.x;
	}

	@Override
	public double getY() {
		return position.y;
	}
	
	@Override
	public double getCenterY() {
		return position.y+this.getRadius();
	}
	
	@Override
	public double getCenterX() {
		return position.x+this.getRadius();
	}


}

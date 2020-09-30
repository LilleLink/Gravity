import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Custombody extends AstroObject implements Astromath {
	
	public Custombody(double realX, double realY, double radius, double mass) {
		super(realX, realY, radius, mass);
	}

	@Override
	public void trace(Graphics g) {
		
	}

	@Override
	public void updatePosition(LinkedList<AstroObject> AstroObjects, Graphics g) {
		for (AstroObject o : AstroObjects) {
			if (o != this) {
				double distance = Astromath.getDistance(this, o);
				double gForce = Astromath.getGForce(this.getMass(), o.getMass(), distance);
				double accelerationNoAngle = gForce / this.getMass();
				double angle = Math.atan2((o.getCenterY()) - (this.getCenterY()),
						(o.getCenterX()) - (this.getCenterX()));

				acceleration.x = accelerationNoAngle * Math.cos(angle);
				acceleration.y = accelerationNoAngle * Math.sin(angle);
				
				velocity.x += acceleration.x * Astromath.timeFactor;
				velocity.y += acceleration.y * Astromath.timeFactor;

			}
		}
		position.x += velocity.x * Astromath.timeFactor;
		position.y += velocity.y * Astromath.timeFactor;
		
		//trace(g);
		
		g.setColor(Color.red);
		g.fillOval((int) ((position.x) / Astromath.factor - 5 / 2),
				(int) ((position.y) / Astromath.factor - 5 / 2), 5, 5);
		g.setColor(Color.white);
	}

	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return this.mass;
	}

	@Override
	public void setMass(double mass) {
		this.mass = mass;
	}

	@Override
	public double getXVelocity() {
		return this.velocity.x;
	}

	@Override
	public double getYVelocity() {
		return this.velocity.y;
	}

	@Override
	public void setVelocity(double vx, double vy) {
		this.velocity.x = vx;
		this.velocity.y = vy;
	}

	@Override
	public double getXAcceleration() {
		return this.acceleration.x;
	}

	@Override
	public double getYAcceleration() {
		return this.acceleration.y;
	}

	@Override
	public void setAcceleration(double ax, double ay) {
		this.acceleration.x = ax;
		this.acceleration.y = ay;
	}

	@Override
	public double getRadius() {
		return this.radius;
	}

	@Override
	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public void setBounds(double x, double y, double radius) {
		this.position.x = x;
		this.position.y = y;
	}

	@Override
	public double getX() {
		return this.position.x;
	}

	@Override
	public double getY() {
		return this.position.y;
	}
	
	@Override
	public double getCenterY() {
		return position.y+this.getRadius();
	}
	
	@Override
	public double getCenterX() {
		return position.x+this.getRadius();
	}

	@Override
	public double getVelocity() {
		return Math.sqrt(Math.pow(this.velocity.x, 2)+Math.pow(this.velocity.y, 2));
	}

}

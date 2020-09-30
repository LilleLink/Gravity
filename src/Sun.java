import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Sun extends AstroObject implements Astromath {

	public Sun(double realx, double realy) {
		super(Astromath.S_mass, realx, realy, Astromath.S_radius);
		
	}

	@Override
	public void updatePosition(LinkedList<AstroObject> AstroObjects, Graphics g) {
		for (AstroObject o : AstroObjects) {
			if (o != this) {
				double distance = Astromath.getDistance(this, o);
				double gForce = Astromath.getGForce(this.getMass(), o.getMass(), distance);
				double accelerationNoAngle = gForce / this.getMass();
				double angle = Math.atan2((o.getY()-this.getY()), (o.getX()-this.getX()));
				//double angle = Math.atan2((o.getCenterY()) - (this.getCenterY()),
						//(o.getCenterX()) - (this.getCenterX()));
				// g.drawLine((int)(this.position.x/factor),(int)(this.position.y/factor),(int)(o.position.x/factor),
				// (int)(o.position.y/factor));
				acceleration.x = accelerationNoAngle * Math.cos(angle);
				acceleration.y = accelerationNoAngle * Math.sin(angle);

				velocity.x += acceleration.x * Astromath.timeFactor;
				velocity.y += acceleration.y * Astromath.timeFactor;
				
			}
		}

		position.x += velocity.x * Astromath.timeFactor;
		position.y += velocity.y * Astromath.timeFactor;

		g.setColor(Color.orange);
		g.fillOval((int) (this.getCenterX() / Astromath.factor),
				(int) (this.getCenterY() / Astromath.factor), 10, 10);
		// System.out.println(position.x);
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
	public void setVelocity(double vx, double vy) {
		velocity.x = vx;
		velocity.y = vy;
	}

	@Override
	public void setAcceleration(double ax, double ay) {
		acceleration.x = ax;
		acceleration.y = ay;
	}

	@Override
	public double getRadius() {
		return radius;
	}

	@Override
	public void setRadius(int radius) {
		this.radius = radius;
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
	public double getXAcceleration() {
		return acceleration.x;
	}

	@Override
	public double getYAcceleration() {
		return acceleration.y;
	}

	@Override
	public void setBounds(double x, double y, double radius) {
		position.x = x;
		position.y = y;
		this.radius = radius;

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

	@Override
	public double getVelocity() {
		return Math.sqrt(Math.pow(this.velocity.x, 2) + Math.pow(this.velocity.y, 2));
	}

	@Override
	public void trace(Graphics g) {
		// TODO Auto-generated method stub

	}

}

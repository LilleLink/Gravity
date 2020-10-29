import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Moon extends AstroObject implements Astromath {
	
	private LinkedList<Double> prevPositionsX = new LinkedList<Double>();
	private LinkedList<Double> prevPositionsY = new LinkedList<Double>();
	//https://in-the-sky.org/news.php?id=20170610_08_100
	public Moon(double realX, double realY) {
		super(Astromath.M_mass, realX, realY);
		//this.velocity.y = Astromath.getVelocity(Astromath.ES_aphelion+Astromath.EM_aphelion, Astromath.S_mass, Astromath.EM_SM_axis+Astromath.ES_SM_axis);
		this.velocity.y = Astromath.getVelocity(Astromath.EM_aphelion, Astromath.E_mass, Astromath.EM_SM_axis)+Astromath.getVelocity(Astromath.ES_periphelion, Astromath.S_mass, Astromath.ES_SM_axis);
		this.radius = Astromath.M_radius;
	}

	@Override
	public void updatePosition(LinkedList<AstroObject> AstroObjects, Graphics g) {
		super.updatePosition(AstroObjects, g);

		g.setColor(Color.white);
		g.fillOval((int) ((this.getCenterX()) / Astromath.factor),
				(int) ((this.getCenterY()) / Astromath.factor), 3, 3);
		
	}
	
	public void trace(Graphics g) {
		if (GravitySim.getPaintFrame().getTimeCount() % 100000 == 0) {
			prevPositionsX.addFirst(position.x);
			prevPositionsY.addFirst(position.y);
		}
		
		g.setColor(Color.white);
		for (double p : prevPositionsX) {
			int index = prevPositionsX.indexOf(p);
			g.fillRect((int)(p/Astromath.factor), (int)(prevPositionsY.get(index)/Astromath.factor), 1, 1);
		}
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
		return velocity.x;
	}
	
	@Override
	public double getYVelocity() {
		return velocity.y;
	}
	
	@Override
	public void setVelocity(double vx, double vy) {
		this.velocity.x = vx;
		this.velocity.y = vy;
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
		this.radius = radius;
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
	
	public double getVelocity() {
		return Math.sqrt(Math.pow(this.velocity.x, 2)+Math.pow(this.velocity.y, 2));
	}

}

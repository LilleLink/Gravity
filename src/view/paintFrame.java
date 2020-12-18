package view;

import model.AstroObject;
import model.Astromath;
import model.Earth;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class paintFrame extends Canvas implements Runnable, KeyListener{

	private Thread thread;
	public static LinkedList<AstroObject> AstroObjects = new LinkedList<AstroObject>();
	private double timeCount = 0;
	
	public boolean paused = false;
	
	AstroObject Earth;
	AstroObject Moon;
	AstroObject Sun;
	
	private double startAngle;
	private double startAngleMoon;
	private int moonLaps;

	public paintFrame(int width, int height) {
		
		this.setBounds(0, 0, width, height);
		
		//Listener
		addKeyListener(this);
		
		// Timer for framecount
		Timer t = new Timer();
		
		TimerTask updateFPS = new TimerTask() {
		    public void run() {
		    	//System.out.println(timeCount);
		        //timeCount = 0;
		    }
		};
		t.scheduleAtFixedRate(updateFPS, 1000, 1000);
		
		//Skapar himlakropparna
		Sun = new Sun(Astromath.toMeters(this.getWidth()/2-16),Astromath.toMeters(this.getHeight()/2-30));
		AstroObjects.add(Sun);
		
		Earth =  new Earth(Sun.getX()-Astromath.ES_periphelion, Sun.getY());
		AstroObjects.add(Earth);

		/*model.Earth =  new model.Earth(model.Astromath.toMeters(this.getWidth()/2-16),model.Astromath.toMeters(this.getHeight()/2-30));
		AstroObjects.add(model.Earth);*/
		
		//System.out.println(model.Earth.getCenterY()-Sun.getCenterY());
		System.out.println(startAngle);
		Moon = new Moon(Earth.getX()-Astromath.EM_aphelion, Earth.getY());
		AstroObjects.add(Moon);
		
		startAngle = Math.atan2((Sun.getY()) - (Earth.getY()), (Sun.getX()) - (Earth.getX()));
		startAngleMoon = Math.atan2((Moon.getY()-Earth.getY()), (Moon.getX()-Earth.getX()));
		moonLaps = 0;
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g) {
		Image img = createImage(this.getWidth(), this.getHeight());
		if (!paused) {
			Graphics gfx = img.getGraphics();
			gfx.setColor(Color.BLACK);
			gfx.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			//Grid
			gfx.setColor(Color.WHITE);
			gfx.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
			gfx.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
			
			//System.out.println(model.Astromath.getGForce(model.Astromath.M_mass, model.Astromath.E_mass, model.Astromath.getDistance(model.Earth, Moon)));
			//System.out.println();
			for (AstroObject a : AstroObjects) {
				a.updatePosition(AstroObjects, gfx);
			}
			timeCount = timeCount+ Astromath.timeFactor;
			controlAngle();
			g.drawImage(img, 0, 0, this);
		}
		//System.out.println(model.Astromath.getAngularVelocity(model.Astromath.getDistance(model.Earth, Moon), model.Earth.getMass()));
		
	}
	
	public void update(Graphics gfx) {
		paint(gfx);
	}
	
	@Override
	public void run() {
		
		while (true) {
			repaint();			
			//System.out.println(timeCount/86400+" days");
			try {
				thread.sleep(10);
			} catch (Exception e) {
				
			}
		}
	}
	
	private void controlAngle() {
		double newAngle = Math.atan2((Sun.getY()) - (Earth.getY()), (Sun.getX()) - (Earth.getX()));
		double newAngleMoon = Math.atan2((Moon.getY() - Earth.getY()), (Moon.getX() - Earth.getX()));

		if (newAngle <= startAngle+0.0005 && newAngle >= startAngle-0.0005) {
			pause();
			System.out.println("model.Earth has done a lap after "+timeCount/86400+" days");
		}
		
		if (newAngleMoon <= startAngleMoon+0.0020 && newAngleMoon >= startAngleMoon-0.0020) {
			moonLaps++;
			System.out.println("Moon has done "+moonLaps+" laps after "+timeCount/86400+" days");
		}

	}

	public LinkedList<AstroObject> getAstroObjects() {
		return AstroObjects;
	}

	public double getTimeCount() {
		return timeCount;
	}

	private void pause() {
		if (paused) {
			paused = false;
		} else {
			paused = true;
		}
		System.out.println(paused);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 80) {
			pause();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
}

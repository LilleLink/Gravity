package view;

import model.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class paintFrame extends Canvas implements KeyListener{

	private Model model;
	public boolean paused = false;

	public paintFrame(int width, int height, Model model) {
		this.model = model;
		this.setBounds(0, 0, width, height);
		
		//Listener
		addKeyListener(this);

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

			for (IAstroObject a: model.getAstroObjects()) {
				a.paint(g);
			}

			g.drawImage(img, 0, 0, this);
		}
	}
	
	public void update(Graphics gfx) {
		paint(gfx);
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

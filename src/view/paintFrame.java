package view;

import model.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//TODO: Move keylistener to controller
public class paintFrame extends Canvas {

	private Model model;

	public paintFrame(int width, int height, Model model) {
		this.model = model;
		this.setBounds(0, 0, width, height);

	}
	
	public void paint(Graphics g) {
		Image img = createImage(this.getWidth(), this.getHeight());

		Graphics gfx = img.getGraphics();
		gfx.setColor(Color.BLACK);
		gfx.fillRect(0, 0, this.getWidth(), this.getHeight());

		for (IAstroObject a: model.getAstroObjects()) {
			a.paint(gfx);
		}
		g.drawImage(img, 0, 0, this);

	}
	
	public void update(Graphics gfx) {
		paint(gfx);
	}

}

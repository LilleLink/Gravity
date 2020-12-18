package view;

import model.Astromath;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GravitySim extends JFrame implements Astromath {
	
	private static paintFrame pf;
	
	public GravitySim(String Title) {
		super(Title);
		this.setPreferredSize(new Dimension(1920, 1080));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();

		pf = new paintFrame(this.getWidth(), this.getHeight());
		this.add(pf);
		
		this.setVisible(true);
		
	}

	public static void main(String[] args) {
		new GravitySim("Gravity");
	}
	
	public static paintFrame getPaintFrame() {
		return pf;
	}

}

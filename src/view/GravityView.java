package view;

import model.Astromath;
import model.Model;

import java.awt.*;

import javax.swing.JFrame;

public class GravityView extends JFrame implements Astromath {
	
	private paintFrame pf;
	private Model model;

	public GravityView(String Title, Model model) {
		super(Title);

		this.model = model;

		this.setPreferredSize(new Dimension(1920, 1080));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();

		pf = new paintFrame(this.getWidth(), this.getHeight(), model);
		this.add(pf);
		
		this.setVisible(true);
		
	}

	public void update()
	{
		pf.repaint();
	}

	/*public static void main(String[] args) {
		new GravityView("Gravity");
	}*/

	/*public static paintFrame getPaintFrame() {
		return pf;
	}*/

}

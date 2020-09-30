import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GravitySim extends JFrame implements Astromath {
	
	private static paintFrame pf;
	
	public GravitySim(String Title) {
		super(Title);
		this.setPreferredSize(new Dimension(1920, 1080));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);
		this.pack();

		pf = new paintFrame(this.getWidth(), this.getHeight());
		this.add(pf);
		
		this.setVisible(true);
		
	}

	public static void main(String[] args) {
		new GravitySim("Gravity");
		//System.out.println((Astromath.GravityConstant*Astromath.E_mass*Astromath.S_mass/Math.pow(Astromath.ES_aphelion, 2)));
	}
	
	public static paintFrame getPaintFrame() {
		return pf;
	}

}

public interface Astromath {
	
	/*Earth to Sun variables*/
	public double ES_SM_axis = (1.495979*Math.pow(10, 11));
	public double ES_ecentricity = 0.01671022;
	public double ES_aphelion= ES_SM_axis*(1+ES_ecentricity);
	public double ES_periphelion= ES_SM_axis*(1-ES_ecentricity);
	
	/*Earth to Moon variables*/
	public double EM_SM_axis = 384748000;
	public double EM_ecentricity = 0.0549006;
	public double EM_aphelion = EM_SM_axis*(1+EM_ecentricity);
	public double EM_periphelon = EM_SM_axis*(1-EM_ecentricity);
	
	/*Masses*/ 
	public double S_mass = 1.98843*Math.pow(10, 30);
	public double E_mass = 5.97223*Math.pow(10, 24);
	public double M_mass = 7.34767309*Math.pow(10, 22);
	
	/*Radiuses*/
	public double E_radius = 6371.0088*1000;
	public double M_radius = 1737.1 * 1000;
	public double S_radius = 6.95700*Math.pow(10, 8);
	
	/*Universal constants*/
	public double GravityConstant = 6.6739*Math.pow(10, -11);
	public static final double factor = ES_aphelion/500;
	
	/*Time factor*/
	public double timeFactor = 10000;
	
	public static double getGForce(double mass1, double mass2, double distance) {
		return (GravityConstant*mass1*mass2/(Math.pow(distance, 2)));
	}
	
	public static double getCircularVelocity(double distance, double referenceMass, double referenceAxis) {
		return Math.sqrt(GravityConstant*referenceMass/distance);
	}
	
	public static double getVelocity(double distance, double referenceMass, double referenceAxis) {
		return Math.sqrt(GravityConstant*referenceMass*((2/distance)-(1/referenceAxis)));
	}
	
	public static double getDistance(AstroObject a, AstroObject b) {

		return Math.sqrt(Math.pow((a.getX()-b.getX()),2)+Math.pow((a.getY()-b.getY()),2));
	}
	
	public static double getAngularVelocity(double distance, double referenceMass) {
		return Math.sqrt((GravityConstant*referenceMass)/(Math.pow(distance, 3)));
	}
	
	public static double toMeters(int pixles) {
		return pixles*factor;
	}
	
	public static double toPixels(double meters) {
		return meters/factor;
	}
	
}

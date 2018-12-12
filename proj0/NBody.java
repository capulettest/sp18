public class NBody{

	public static double readRadius(String planetsTxtPath){
		In in = new In(planetsTxtPath);
		int dismissedLine = in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String planetsTxtPath){
		In in = new In(planetsTxtPath);
		int planetCount = in.readInt();
		double dismissedLineTwo = in.readDouble();

		Planet[] planets = new Planet[planetCount];
		Planet planet;
		
		for(int i = 0; i < planetCount; i++) {
			double initialPositionX = in.readDouble();
			double initialPositionY = in.readDouble();
			double initialVelocityX = in.readDouble();
			double initialVelocityY = in.readDouble();
			double mass = in.readDouble();
			String nameOfImageFile = "images/" + in.readString();
			planet = new Planet(initialPositionX, initialPositionY,
								initialVelocityX, initialVelocityY,
								mass, 
								nameOfImageFile);
			planets[i] = planet;
		}
		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radiousOfUniverse = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		
		StdDraw.setScale(-radiousOfUniverse, radiousOfUniverse);
		StdDraw.clear();

		String starField = "images/starfield.jpg";
		StdDraw.picture(0, 0, starField);

		for(Planet p : planets)
			p.draw();
	}
}
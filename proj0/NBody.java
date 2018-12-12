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
			String nameOfImageFile = in.readString();
			planet = new Planet(initialPositionX, initialPositionY,
								initialVelocityX, initialVelocityY,
								mass, 
								nameOfImageFile);
			planets[i] = planet;
		}

		return planets;
	}
}
public class NBody{

	private static int readPlanetCount(String planetsTxtPath){
		In in = new In(planetsTxtPath);
		int pC = in.readInt();
		return pC;
	}

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

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		int planetCount = readPlanetCount(filename);
		double radiousOfUniverse = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		
		StdDraw.setScale(-radiousOfUniverse, radiousOfUniverse);
		StdDraw.clear();

		String starField = "images/starfield.jpg";
		StdDraw.picture(0, 0, starField);

		for(Planet p : planets)
			p.draw();

		StdDraw.enableDoubleBuffering();

		int time = 0;
		while(time < T){
			double[] xForces = new double[planetCount];
			double[] yForces = new double[planetCount];
			int i = 0;
			for(Planet p : planets){
				xForces[i] = p.calcNetForceExertedByX(planets);
				yForces[i] = p.calcNetForceExertedByY(planets);
				i++;
			}
			i = 0;

			StdDraw.picture(0, 0, starField);

			for(Planet p : planets){
				p.update(dt, xForces[i], yForces[i]);
				p.draw();
				i++;
			}

			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radiousOfUniverse);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}
public class NBody {

	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String fileName) {

		In in = new In(fileName);
		int num = in.readInt();
		Body[] bodies = new Body[num];

		double radius = in.readDouble();

		for (int i = 0; i < num; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String name = in.readString();
			bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, name);
		}

		return bodies;

	}

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Please input at least 3 arguments representing T, dt and a txt filename, respectively.");
		} else {
			double t = Double.valueOf(args[0]);
			double dt = Double.valueOf(args[1]);
			String fileName = args[2];

			Body[] bodies = readBodies(fileName);
			double radius = readRadius(fileName);

			StdDraw.enableDoubleBuffering();
			StdDraw.setScale((-1)*radius, radius);
			StdDraw.clear();
			

			double current = 0.0;

			while (current < t) {

				double[] xForces = new double[bodies.length];
				double[] yForces = new double[bodies.length];

				for (int i = 0; i < bodies.length; i++) {
					xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
					yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
				}

				for (int i = 0; i < bodies.length; i++) {
					bodies[i].update(dt, xForces[i], yForces[i]);
				}

				StdDraw.picture(0, 0, "images/starfield.jpg");
	
				for (Body i: bodies) {
					StdDraw.picture(i.xxPos, i.yyPos, "images/" + i.imgFileName);
				}
	
				StdDraw.show();
				StdDraw.pause(10);
				current += dt;

			}
			
			
		}
	}

}
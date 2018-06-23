public class NBody {

    public static double readRadius(String fileName) {
		/* Given a filename, this method returns
		the radius of the corresponding universe
		in that file */

        // read file as an In object
        In in = new In(fileName);
        in.readInt(); // don't need to store it
        return in.readDouble();

    }

    public static Planet[] readPlanets(String fileName) {
		/* Return an array of the planets contained in the
		file provided */

        In in = new In(fileName);
        // number of planets to expect
        int n = in.readInt();

        // discard the radius by not storing it
        in.readDouble();

        Planet[] planets = new Planet[n];

        for (int i = 0; i < n; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();

            planets[i] = new Planet(xP, yP, xV, yV, mass, img);
        }

        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // set the scale to the radius of the universe
        StdDraw.setScale(-1*radius, radius);

        // draw background picture
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for (Planet p : planets) {
            p.draw();
        }

        StdDraw.enableDoubleBuffering();

        int time = 0;

        while (time < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);

            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }

        // print the final state of the universe at time T
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }

}
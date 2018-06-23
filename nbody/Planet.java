public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    static double g = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
		/* Create a new planet with given attributes */

        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet (Planet p) {
		/* Create a copy of planet P */
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;

    }

    public double calcDistance(Planet p) {
		/* Return the distance between this planet
		and the supplied planet P */

        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        return Math.pow((dx*dx + dy*dy), 0.5);
    }

    public double calcForceExertedBy(Planet p) {
		/* Return the force exerted on this planet
		by planet P */
        double r = calcDistance(p);
        return g * (mass * p.mass) / (r*r);

    }

    public double calcForceExertedByX(Planet p) {
		/*  Return the force in the x direction
		exerted on this planet by planet P */

        double force = calcForceExertedBy(p);
        return force * (p.xxPos - xxPos)/calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
		/*  Return the force in the y direction
		exerted on this planet by planet P */

        double force = calcForceExertedBy(p);
        return force * (p.yyPos - yyPos)/calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] ps) {
		/* Return the net force in the x direction exerted
		on this planet by all the planets in the given
		array of planets PS */

        double totalForce = 0;

        for (Planet p : ps) {
            if (!this.equals(p)) {
                totalForce += calcForceExertedByX(p);
            }
        }
        return totalForce;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
		/* Return the net force in the y direction exerted
		on this planet by all the planets in the given
		array of planets PS */

        double totalForce = 0;

        for (Planet p : ps) {
            if (!this.equals(p)) {
                totalForce += calcForceExertedByY(p);
            }
        }
        return totalForce;
    }

    public void update(double t, double xForce, double yForce) {
		/* Update the planet's position and velocity based on
		the forces xForce and yForce exerted on it for T seconds. */

        // acceleration in x and y direction
        double aX = xForce/mass;
        double aY = yForce/mass;

        // calculate new x and y velocity
        // v_new = v_old + a*t
        xxVel += aX * t;
        yyVel += aY * t;

        // update x and y position using new velocity
        xxPos += xxVel*t;
        yyPos += yyVel*t;

    }

    public void draw() {
        String fileAddress = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, fileAddress);
    }
}
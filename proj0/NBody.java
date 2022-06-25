import java.awt.geom.Arc2D;

public class NBody {


    public static double readRadius(String filename){
        In in = new In(filename);
        in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[num];
        for (int i = 0; i < num; i++) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xp, yp, xv, yv, mass, img);
        }
        return planets;
    }


    public static void main(String[] args) {
        //set inital data
        double T = new Double(args[0]);
        double dt = new Double(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // draw
        StdDraw.setScale(-r, r);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet p : planets) {
            p.draw();
        }

        // Animation
        StdDraw.enableDoubleBuffering();
        double time = 0;
        while(time <= T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i=0; i<planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for(int i=0; i<planets.length; i++) {
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

        /*
		   Printing the Universe
		   When reached time T, print out the final state of the universe.
		*/
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);

        }

    }

}

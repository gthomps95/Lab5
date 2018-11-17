import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class NBodySimulation
{
	private Body[] bodies;    //stores all the bodies in the simulation
	private int    numBodies; //number of bodies in this simulation
	private double uRadius;   //radius of the universe
	private String fileName;  //file providing the input

	public NBodySimulation(String nameOfFile) throws IOException
	{
		fileName = nameOfFile;
		File file = new File(fileName);
		Scanner fScan = new Scanner(file);
        numBodies = fScan.nextInt();
        uRadius = fScan.nextDouble();
        bodies = new Body[numBodies];
        Body b;
        for (int i = 0;  i < bodies.length; i++){
            b = new Body(fScan.nextDouble(), fScan.nextDouble(), fScan.nextDouble(), fScan.nextDouble(), fScan.nextDouble(), fScan.next());
            bodies[i] = b;
        }
		initCanvas(); //don't move, should be the last line of the constructor
	}

	/** initialize the drawing canvas */
	private void initCanvas()
	{
		StdDraw.setScale(-uRadius, uRadius); //set canvas size / scale
		StdDraw.picture(0, 0, "starfield.jpg"); //paint background

		//below is a for-each loop, which we will cover in the next lab
		//more info is available in the powerpoints, for the curious
		for (Body body : bodies)
			body.draw();
	}

	/**
	 * run the n-bodies simulation
	 * @param T total time to run the simulation, in seconds
	 * @param dt time step, in seconds
	 */
	public void run(double T, double dt)
	{
		double time = 0;
		//StdDraw.picture(0, 0, "starfield.jpg");
		for (time = 0; time < T; time += dt){

			for (int i = 0; i < bodies.length; i++){
				bodies[i].setNetForce(bodies);
				bodies[i].update(dt);
			}
			StdDraw.picture(0, 0, "starfield.jpg");
			for (int i = 0; i < bodies.length; i++){
				bodies[i].draw();
			}
			StdDraw.show(1000);
		}
	}
}

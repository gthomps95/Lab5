public class Body
{
	public static final double G = 6.67E-11;  //Newtons' gravitational constant

	private double x, y; //planet's x and y coordinate position
	private double xVelocity, yVelocity;
	private double mass;
	private double xNetForce, yNetForce; //net forces action on this planet
	private double xAccel, yAccel;
	private String image; //image of this planet (for drawing)


	public Body(double x1, double y1, double xVelocity1, double yVelocity1, double mass1, String image1){
	    x = x1;
	    y = y1;
	    xVelocity = xVelocity1;
	    yVelocity = yVelocity1;
	    mass = mass1;
	    image = image1;
    }

    private double  getDistance(Body  other){
	    return Math.sqrt(Math.pow(Math.abs(x - other.x), 2) + Math.pow(Math.abs(y - other.y), 2));
    }

    double getPairwiseForceX(Body other){
        return ((mass * other.mass)/(other.x - x)) * (6.67 * Math.pow(10, -11));
    }

    double getPairwiseForceY(Body other){
        return ((mass * other.mass)/(other.y - y)) * (6.67 * Math.pow(10, -11));
    }

    private double getPairwiseForce(Body other){

        return Math.sqrt(Math.pow(getPairwiseForceX(other), 2) + Math.pow(getPairwiseForceY(other), 2));
    }
    public void setNetForce(Body[] bodies){
        double xVal = 0;
        double yVal = 0;
        for (int i = 0; i < bodies.length; i++){
            if (this == bodies[i])
                continue;
            xVal += getPairwiseForceX(bodies[i]);
            yVal += getPairwiseForceY(bodies[i]);
        }
        xNetForce = xVal;
        yNetForce = yVal;
    }

	/** calculates / sets the net force exerted on this body by all the (input) bodies */


	/** updates this body's accel, vel, and position, given the time step */
	public void update(double dt)
	{
		xAccel = xNetForce / mass;
        yAccel = yNetForce / mass;
        xVelocity = xVelocity + (xAccel * dt);
        yVelocity = yVelocity + (yAccel * dt);
        x = x + (xVelocity * dt);
        y = y + (yVelocity * dt);
	}


	/** Draws the body using the StdDraw library file's drawing method */
	public void draw()
	{
		StdDraw.picture(x, y, image);
	}
}

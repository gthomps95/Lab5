import java.io.IOException;

public class Runner 
{
	public static void main(String[] args) throws IOException
	{
System.out.println("test");
		final double T = 1_000_000_000; //simulation time in seconds
		
		final double dt = 25_000; //time step in seconds, one day
		
		final String fileName = "planets.txt";
		
		NBodySimulation sim = new NBodySimulation(fileName);
		
		//StdAudio.play("2001.wav"); //uncomment to have it play music during the simulation
		
		sim.run(T, dt);
	}
}

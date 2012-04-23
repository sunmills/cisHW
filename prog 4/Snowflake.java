
public class Snowflake {

	public static void main(String[] args){
		
		// Initialize...
		
		// I've made it possible to define what letter our language is based around.
		char lineIs = 'F';
		
		String initiator = "F+F+F+F";
		String generator = "F-F++F-F";
		int generations = 4;
		
		Generator gen1 = new Generator(lineIs);
		String generatedString = gen1.generate(initiator,generator,generations);
				
		// Print the specifications and the generated string.
		System.out.println("gen1 uses "+lineIs+" to represent lines. It replaces all "+lineIs+"'s " +
				"with "+generator+". The initial string is "+initiator+". The string is proceessed recursively "+generations+" times.");
		System.out.println(generatedString);
		
		
		
		// Extra Credit?
		
		// Create an island.
		Island island = new Island(1000,1000);
		
		// Create some turtles who draw different stages of a fractal.
		InterpreterTurtle john1 = new InterpreterTurtle(lineIs,0);
		InterpreterTurtle john2 = new InterpreterTurtle(lineIs,1);
		InterpreterTurtle john3 = new InterpreterTurtle(lineIs,2);
		InterpreterTurtle john4 = new InterpreterTurtle(lineIs,3);
		InterpreterTurtle john5 = new InterpreterTurtle(lineIs,4);
		InterpreterTurtle john6 = new InterpreterTurtle(lineIs,5);
		
		// Prepare them for their work today.
		john1.getReady(island);
		john2.getReady(island);
		john3.getReady(island);
		john4.getReady(island);
		john5.getReady(island);
		john6.getReady(island);
		
		// Begin working.
		john1.drawTriangle(generator, 780);
		john2.drawTriangle(generator, 780);
		john3.drawTriangle(generator, 780);
		john4.drawTriangle(generator, 780);
		john5.drawTriangle(generator, 780);
		john6.drawTriangle(generator, 780);
		
		// Clean up!
		john1.tailUp();
		john2.tailUp();
		john3.tailUp();
		john4.tailUp();
		john5.tailUp();
		
	}
	
	
}

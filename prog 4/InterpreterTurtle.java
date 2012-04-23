
public class InterpreterTurtle extends Turtle {
	// Interpreter Turtle draws fractal triangles!
	
	
	// Initialization
	
	// Since we can set what letter we use in our language in Generator, why not here?

	private	char key;

	private int generations;
	
	
	// Constructor ahead
	
	public InterpreterTurtle(char lineChar, int generations){
		// We must set the turtle's favorite character and how hard he works.
		
		if(lineChar!='+'&&lineChar!='-'){
			setKey(lineChar);
		}
		else System.out.println("YOU CANNOT USE + or - AS KEYS!");		setGenerations(generations);
	}

	
	// Getters and Setters.
	

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}

	public int getGenerations(){
		return generations;
	}

	public void setGenerations(int generations){
		this.generations = generations;
	}
	
	
	// The public drawTriangle() method...
	public void drawTriangle(String instruction, double faceLength){
		// Main method for drawing triangles. It uses helper functions to draw stuff.
		// Think of it as the customer service/marketing department; all it knows how to do
		// is tell other people to make triangles with customer specifications. It gathers those specifications.
		
		triangleDrawer(instruction, faceLength, 1);	
	}
	private void triangleDrawer(String instruction, double faceLength, int side){
		// Helper function that manages the overall shape of the triangle. These are the project managers
		// of the triangle world. They make sure everything is on track, and that we've actually made a shape.
		// It checks to make sure all of the sides have been drawn, and if they haven't, we keep drawing
		// sides until we have a closed shape. It has the ability to make incomplete triangles for some reason.
		
		if(side>3){
			// We must be done drawing! Triangles don't have more than 3 sides, silly.
		}
		else{
			drawSide(instruction,faceLength);
			this.turnLeft(120);
			triangleDrawer(instruction, faceLength, side+1);
		}
	}
	private void drawSide(String instruction, double faceLength){
		// drawSide is lower level management. He looks after a specific side of the triangle, but doesn't do
		// the hard work of drawing. All it does is make an object with the right key, and then generate a new
		// string representing the modified side, according to how many generations we recurse it. It assumes that
		// a side of an unmodified triangle is just a line, represented by the key. It the passes this information
		// for interpretation.
		
		Generator gen = new Generator(this.getKey());
		String side = ""+this.getKey();
		String newSide = gen.generate(side, instruction, this.getGenerations());
		this.interpret(newSide, faceLength);
	}
	private void interpret(String side, double faceLength){
		// This function does all of the hard work. First, it scales the length of each line its going to draw
		// so that no matter how many times the side of the string was processed, the shape will always be about
		// the same size. It then checks to see what the first character of the string representing the side is,
		// and moves the turtle accordingly, ultimately drawing the side. It acts recursively, addressing each
		// segment of the shape one at a time until its done.
		
		double newFaceLength = scale(faceLength,this.getGenerations());
		
		if(side.length()==0){
			// We're done here...
		}
		else{
			if(side.charAt(0)==getKey()){
				this.move(newFaceLength);
			}
			if(side.charAt(0)=='+'){
				this.turnLeft(60);
			}
			if(side.charAt(0)=='-'){
				this.turnRight(60);
			}
			this.interpret(side.substring(1,side.length()), faceLength);
		}
	}
	
	public void getReady(Island island){
		// This function prepares a turtle to draw. It positions the turtle, then puts its tail down.
		
		island.putTurtleAtCenter(this);
		this.moveTo(100, 700);
		this.tailDown();
		
	}
	private static double scale(double faceLength, int generations) {
		//  This method scales the line lengths based on how many times the string is processed.
		
		double newFaceLength = faceLength/((Math.pow(3,generations)));
		return (newFaceLength);
	}


	
}


public class GeometricTurtle extends Turtle {

	// on the first day, God says,
	// "Let there be Initialization!",
	// and state appears...
	
	// States inherited from Turtle. Nothing here.
	
	
	
	// Behaviors.
	
	private void drawSide (double length, double angle) {
	// private, since GeometricTurtle only likes to make polygons
		this.move(length);
		this.turnLeft(angle);
	}
	

	public void triangle(double length) {
	// taken from assignment page, modified for uniformity.
		int angle = 120;
		this.tailDown(); // Writing device initialized
		drawSide(length, angle); 
		drawSide(length, angle); 
		drawSide(length, angle); 
		this.tailUp(); // Done with this polygon!
	 }
	
	public void rectangle (double length, double breadth) {
		int angle = 90; // all rectangles have 90 degree angles
		this.tailDown(); // Writing device initialized
		this.drawSide(length, angle);
		this.drawSide(breadth, angle); // going up!
		this.drawSide(length, angle);
		this.drawSide(breadth, angle);
		this.tailUp(); // Done with this polygon!
	}
	
	public void pentagon (double length) {
		int angle = 72; // 360 degrees divided by 5 angles = 72 internal degrees per turn.
		this.tailDown(); // Writing device initialized
		this.drawSide(length, angle); // getting to work -_-
		this.drawSide(length, angle);
		this.drawSide(length, angle);
		this.drawSide(length, angle);
		this.drawSide(length, angle);
		this.tailUp(); // Done with this polygon!
	}
	
	public void hexagon (double length){
		int angle = 60; // 360 degrees divided by 6 angles = 60 degrees per turn.
		this.tailDown(); // Writing device initialized
		this.drawSide(length, angle); // getting to work -_-
		this.drawSide(length, angle);
		this.drawSide(length, angle);
		this.drawSide(length, angle);
		this.drawSide(length, angle);
		this.drawSide(length, angle);
		this.tailUp(); // Done with this polygon!
	}
	public void octagon (double length){
		int angle = 45; // 360 degrees divided by 8 angles = 45 degrees per turn.
		this.tailDown(); // Writing device initialized
		this.drawSide(length, angle); // getting to work -_-
		this.drawSide(length, angle);
		this.drawSide(length, angle);
		this.drawSide(length, angle);
		this.drawSide(length, angle);
		this.drawSide(length, angle);
		this.drawSide(length, angle);
		this.drawSide(length, angle);
		this.tailUp(); // Done with this polygon!
	}
	public void square (double length){
		this.rectangle(length, length);
	}
    
}
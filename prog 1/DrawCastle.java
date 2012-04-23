public class DrawCastle {
	public static void main(String[] args) {
		
		// INITIALIZATION...
		// on the second day, God says,
		// "Let there be turtles and islands"
		// and there were objects.
		
		GeometricTurtle nathan = new GeometricTurtle(); // I am a turtle now.
		Island java = new Island(460,400); // Create java Island. Arbitrary height.
		java.putTurtle(nathan);
		nathan.moveTo(10,400); 		// Starting near the bottom left corner.
									// 	All moveTo arguments account for odd coordinate system.
									// 	i.e., values represent distance from ceiling, not
									// 	distance from floor.
		nathan.faceEast();			// faceEast just so we know we're oriented properly.
									//	This causes all polygons to face east upon completion, thus
									//	it is always facing east when we give it commands.
		
		
		// Time to get to work.
		
		// Lets start with the rectangles...
		
		nathan.rectangle(60,200);   // Tower left
		nathan.move(60); 			// Get to the castle body's vertex
		nathan.rectangle(320,140);  // Make castle body
		nathan.move(120); 			// Door is centered, (320-80)/2 = 120 margins left and right
		nathan.rectangle(80,70); 	// Make door
		nathan.move(200); 			// Onwards to the next tower
		nathan.rectangle(60, 200); 	// Tower right
		
		nathan.moveTo(90,260);  	// Getting into position for roof blocks.
		nathan.rectangle(40, 20);	// Roof blocking...
		nathan.move(60);
		nathan.rectangle(40, 20);	
		nathan.move(60);
		nathan.rectangle(40, 20);
		nathan.move(60);
		nathan.rectangle(40, 20);
		nathan.move(60);
		nathan.rectangle(40, 20);
		
		nathan.moveTo(30,300); 	// Getting into position for tower portals.
		nathan.square(20);			// Building windows...
		nathan.move(380);
		nathan.square(20);
		
		// No more wrocktongles!
		// Lets finish the windows.
		
		nathan.moveTo(32,240); 	// 240 = 400-100-20-40; 142 = 120+20+2.
									// distance from top; distance from left.
		nathan.pentagon(16);
		nathan.move(380);			// Move across just like we did with the other windows.
		nathan.pentagon(16);
		
		// Almost done... Lets do triangles.
		
		nathan.moveTo(0,200); 	// Position again.
		nathan.triangle(80);		// Building triangles...
		nathan.move(380);
		nathan.triangle(80);
		
		// I work too hard -_________________-

	}
}
class Working
{
  public static void main( String[] arg )
  {
    //
    // Initialize the system.
    //
    Island maui = new Island( 200, 200 );
    Turtle tina = new Turtle();
    maui.putTurtleAtCenter( tina );            

    //
    // Make the Turtle draw a triangular spiral
    //
    tina.tailDown();

    // Draw the first side of the spiral
    tina.move( 75 );

    // Draw the second side.
    tina.turnLeft( 120 );
    tina.move ( 65 );

    // Draw the third side.
    tina.turnLeft( 120 );
    tina.move ( 55 );

    // Draw the fourth side.
    tina.turnLeft( 120 );
    tina.move ( 45 );

    // Draw the fifth side.
    tina.turnLeft( 120 );
    tina.move ( 35 );

    // Draw the sixth side.
    tina.turnLeft( 120 );
    tina.move ( 25 );

    // Draw the seventh side.
    tina.turnLeft( 120 );
    tina.move ( 15 );

    // Draw the eigth side.
    tina.turnLeft( 120 );
    tina.move ( 5 );
    

  } // end main()
} // end class Working


public class Generator {
	
	// Initialization
	
	// Set language basics; what letter will represent a line?
	// key represents whatever letter is chosen.

	private char key;

	// Constructor lets user determine what the key will be.
	public Generator(char lineChar){
		if(lineChar!='+'&&lineChar!='-'){
			setKey(lineChar);
		}
		else System.out.println("YOU CANNOT USE + or - AS KEYS!");
	}
	
	// Getters and setters
	public char getKey(){
		return key;
	}
	public void setKey(char F){
		this.key = F;
	}
	
	
	// Lets make a new string that modifies the old string.

	public String generate(String initiator, String generator, int generation){
		// generate takes the initiator string, and recurses its self until all generations have been simulated.
		// it then returns the new string from when the recursion is done. (tail recursive)
		// Its primary job is to keep track of how many times a string needs to be processed.
		
		// Initialize output string.
		String string;
		if(generation<=0)
			string = initiator;  // return the input to end the recursion.
		else // generate the next generation (n-1) with the nextGeneration's generated initiator (nextGeneration() output)
			string = generate(nextGeneration(initiator, generator),generator,generation-1);
		return string;
	}
	private String nextGeneration(String initiator, String generator){
		// nextGeneration searches through the string one character at a time looking for characters to replace with generator
		// It does this by recursively checking the first character of the substring after the first character of the substring before it.
		// We know we're done when we check an empty string. This method returns the newInitiator string, which represents the processed line.
		
		// Initialize output string.
		String newInitiator;
		// When the substring gets too small, the string has been processed and we can move on. We therefore return the input.
		if(initiator.length()<=0)
			 newInitiator=initiator;
		else{
			// If the first character is the key, modify the initiator string so that the first letter is replaced with 'generator', then move to the next letter.
			if(initiator.charAt(0)==this.getKey()) newInitiator = generator+nextGeneration(initiator.substring(1,initiator.length()), generator);
			// If the first character is not the key, separate it and check the rest of the string.
			else newInitiator = String.valueOf(initiator.charAt(0))+nextGeneration(initiator.substring(1,initiator.length()),generator);
		}
	return newInitiator;
	
	}
	
}

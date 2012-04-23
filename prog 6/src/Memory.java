import java.io.*;

public class Memory {

	// Initialization
	
	private String[] memory;
	
	
	// Constructor
	public Memory(String fileName){
		// This constructor, for simplicity's sake, gets its information from a file and stores it according to its order.
		// It can only emulate 32 bytes of memory.
		
		try{
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			setMemory(new String[32]);
			for(int i=0;i<32;++i){
				String line = file.readLine();
				if(line==null) memory[i]="00000000";
				else memory[i]=line;
			}
			file.close();
		}
		catch(IOException e){
			System.out.println("The required data file is in the wrong directory or doesn't exist or isn't named properly, but cannot otherwise be found.");
		}
	}
	
	
	// Getters and Setters
	
	public String[] getMemory() {
		return memory;
	}
	public String getContent(int location){
		return memory[location]; 
	}
	public void setMemory(String[] anotherMemory) {
		this.memory = anotherMemory;
	}	
	public void setContent(int location, String contents){
		this.memory[location] = contents;
	}
	
	
	// Methods
	
	public void store(String binaryLocation, String content){
		// This simple method stores data to an array member according to the arguments given.
		
		if(content.length()<=8){
			int arrayLocation = Memory.binaryToDecimal(binaryLocation);
			setContent(arrayLocation,content);
		}
		else System.out.println("ERROR: Input string is not 8 characters.");
	}
	public String read(String location){
		// This is another simple method that reads the array for data.
		
		String result = "";
		result = this.getContent(Memory.binaryToDecimal(location));
		return result;
	}
	public static int binaryToDecimal(String binary){
		// This method converts simple binary numbers to base 10.
		
		int result;
		if(binary.length()<=0){
			result = 0;
		}
		else if(binary.charAt(0)=='0'){
			result = 0 + Memory.binaryToDecimal(binary.substring(1,binary.length()));
		}
		else{
			result = (Character.getNumericValue(binary.charAt(0))*(int)Math.pow(2, binary.length()-1)) + Memory.binaryToDecimal(binary.substring(1,binary.length()));
		}
		return result;
	}
	public static String decimalToBinary(int decimal){
		// This method converts base ten integers to base 2 numbers.
		
		String result;
		if(decimal<=1){
			result = ""+decimal;
		}
		else{
			result = ""+Memory.decimalToBinary(decimal/2)+(decimal%2);
		}
		return result;
	}
	public boolean isNext(int i){
		// This method returns true or false depending on if we've reached the end of the memory or not.
		
		boolean result;
		if(this.getContent(i+1)!=null) result = true;
		else result = false;
		return result;
	}

	
}


public class CPU {
	
	// Initialization
	
	private String insRegister;
	private String PC;
	private String accumulator;

	private Memory localMemory;
	
	
	// Constructor
	
	public CPU(Memory mem){
		// Installs proper memory. You must boot it on your own.
		setMemory(mem);		
	}
	
	
	// Getters and Setters
	
	public Memory getMemory(){
		return localMemory;
	}
	public void setMemory(Memory mem){
		localMemory = mem;
	}
	public String getInsRegister() {
		return insRegister;
	}
	public void setInsRegister(String insRegister) {
		this.insRegister = insRegister;
	}
	public String getPC() {
		return PC;
	}
	public void setPC(String pC) {
		PC = pC;
	}
	public String getAccumulator() {
		return accumulator;
	}
	public void setAccumulator(String accumulator) {
		while(accumulator.length()<8){
			accumulator = "0"+accumulator;
		}
		this.accumulator = accumulator;
	}
	
	
	// Methods
	
	public void powerOn(){
		// This method turns the CPU on, and initializes the processing scheme at the first point in memory
		// It represents a general "power on" state for the class, and presents the results when its ready to powered down.
		
		setPC("00000");
		setInsRegister(getMemory().read(this.getPC()));
		System.out.println("New object with "+this.getMemory().getMemory().length+" bytes of memory, PC: "+getPC()+", and InsRegister: "+getInsRegister()+". accumulated: "+getAccumulator());
		System.out.println();
		beginCycle();
		System.out.println();
		System.out.println("Cycle has ended. Final value: "+Memory.binaryToDecimal(getAccumulator()));
	}

	private void beginCycle(){
		// This method does all of the hard looping work of processing the cycle.
		// It prints out the top of the display, then processes each memory address per the instructions in memory.
		// It does this as long as the program wants it to; it makes no attempt to stop it, but it gives a message
		// if it runs out of memory (i.e., there is no Break).
		
		String breaker = "";
		for(int i = 0;i<=141;++i){ breaker = "-"+breaker; }
		System.out.println("|"+breaker+"|");
		CPU.print("| Execution Stage| ");
		CPU.print("PC | ");
		CPU.print("Prev Acc | ");
		CPU.print("Instruction | ");
		CPU.print("OPCODE | ");
		CPU.print("Action... | ");
		CPU.print("Acc Binary | ");
		CPU.print("Acc Decimal | ");
		System.out.println();
		System.out.println("|"+breaker+"|");
		System.out.print("|");

		
		for(int i = 0;;++i){
			
			// Were are we?
			
			CPU.print("Execution: "+(i+1)+" | ");
			CPU.print("PC: "+this.getPC()+" | ");
			CPU.print("ACC: "+this.getAccumulator()+" | ");
			setInsRegister(this.getMemory().read(this.getPC()));
			CPU.print("Ins: "+this.opCodeReader()+" | ");
			String OPCode = this.getInsRegister().substring(0,3);
			CPU.print("OPCODE: "+OPCode+" | ");
			
			// Checking and following orders...
			
			if(OPCode.equals("000")) {
				CPU.print("*** Breaking... | ");
				CPU.print("ACC: "+this.getAccumulator()+" | ");
				CPU.print("ACC dec.: "+Memory.binaryToDecimal(this.getAccumulator())+" | ");
				break;
			}
			if(OPCode.equals("001")) JANZ();
			if(OPCode.equals("010")) StAM();
			if(OPCode.equals("011")) LdAM();
			if(OPCode.equals("100")) AddM();
			if(OPCode.equals("101")) AddI();
			if(OPCode.equals("110")) SubM();
			if(OPCode.equals("111")) SubI();
			
			// Where did we go?
			
			CPU.print("ACC: "+this.getAccumulator()+" | ");
			CPU.print("ACC dec.: "+Memory.binaryToDecimal(this.getAccumulator())+" | ");

			System.out.println();
			System.out.print("|");
		}
		
		System.out.println();
		System.out.println("|"+breaker+"|");

	}
	private void JANZ(){
		// Jump to the argument if accumulator is zero.
		// Operate the associated 'machine code' as given in specifications.
		
		CPU.print("JANZing... | ");
		if(this.getAccumulator().equals("00000000")){
			this.setPC(CPU.nextLocation(this.getPC()));
		}
		else{
			this.setPC(this.getInsRegister().substring(3,8));
		}
	}
	private void StAM(){
		// Store the accumulator in memory
		// Operate the associated 'machine code' as given in specifications.
		
		CPU.print("Storing... | ");
		String argument = this.getInsRegister().substring(3,8);
		this.getMemory().store(argument, this.getAccumulator());
		this.setPC(CPU.nextLocation(this.getPC()));
	}
	private void LdAM(){
		// Put a given value from a memory location into the accumulator
		// Operate the associated 'machine code' as given in specifications.
		
		CPU.print("Loading... | ");
		String argument = this.getInsRegister().substring(3,8);
		this.setAccumulator(this.getMemory().read(argument));
		this.setPC(CPU.nextLocation(this.getPC()));
	}
	private void AddM(){
		// Add a memory value to the accumulator
		// Operate the associated 'machine code' as given in specifications.
		
		CPU.print("Adding... | ");
		String argument = this.getInsRegister().substring(3,8);
		int mem = Memory.binaryToDecimal(this.getMemory().read(argument));
		int accumulator = Memory.binaryToDecimal(this.getAccumulator());
		String newAccum = Memory.decimalToBinary(mem+accumulator);
		this.setAccumulator(newAccum);
		this.setPC(CPU.nextLocation(this.getPC()));
	}
	private void AddI(){
		// Add the argument to the accumulator value
		// Operate the associated 'machine code' as given in specifications.
		
		CPU.print("Adding... | ");
		String argument = this.getInsRegister().substring(3,8);
		int accumulator = Memory.binaryToDecimal(this.getAccumulator());
		String newAccum = Memory.decimalToBinary(Memory.binaryToDecimal(argument)+accumulator);
		this.setAccumulator(newAccum);
		this.setPC(CPU.nextLocation(this.getPC()));
	}
	private void SubM(){
		// Subtract a given memory value from the accumulator
		// Operate the associated 'machine code' as given in specifications.
		
		CPU.print("Subtracting... | ");
		String argument = this.getInsRegister().substring(3,8);
		int mem = Memory.binaryToDecimal(this.getMemory().read(argument));
		int accumulator = Memory.binaryToDecimal(this.getAccumulator());
		String newAccum = Memory.decimalToBinary(accumulator-mem);
		this.setAccumulator(newAccum);
		this.setPC(CPU.nextLocation(this.getPC()));
	}
	private void SubI(){
		// Subtract the argument from the accumulator
		// Operate the associated 'machine code' as given in specifications.
		
		CPU.print("Subtracting... | ");

				String argument = this.getInsRegister().substring(3,8);
		int accumulator = Memory.binaryToDecimal(this.getAccumulator());
		String newAccum = Memory.decimalToBinary(accumulator-Memory.binaryToDecimal(argument));
		this.setAccumulator(newAccum);
		this.setPC(CPU.nextLocation(this.getPC()));
	}
	private static String nextLocation(String pc){
		// This method simply returns the next address location to go to if there haven't been any other orders.
		
		int result;
		result = Memory.binaryToDecimal(pc)+1;
		String sResult = Memory.decimalToBinary(result);
		while(sResult.length()<5){
			sResult = "0"+sResult;
		}
		if(result>32) CPU.print("ERROR: THIS PROGRAM DOESN'T REFERNCE ANY BREAKS IN MEMORY!");
		return sResult;
	}
	private static void print(String a){
		// Simple shorthand code for printstream with some added formatting.
		
		while(a.length()<18){
			a = " "+a;
		}
		System.out.print(a);
	}
	private String opCodeReader(){
		// Reads the current orders and returns a more human readable string complete with its proper argument
		
		String result;
		String OPCode = this.getInsRegister().substring(0,3);
		String argument = this.getInsRegister().substring(3,8);
		if(OPCode.equals("001")) result = "JANZ";
		else if(OPCode.equals("010")) result = "StAM";
		else if(OPCode.equals("011")) result = "LdAM";
		else if(OPCode.equals("100")) result = "AddM";
		else if(OPCode.equals("101")) result = "AddI";
		else if(OPCode.equals("110")) result = "SubM";
		else if(OPCode.equals("111")) result = "SubI";
		else result = "Halt";
		result = result+" "+Memory.binaryToDecimal(argument);
		return result;
	}


}

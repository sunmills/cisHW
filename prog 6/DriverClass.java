
public class DriverClass {
	
	public static void main(String [] args){
		// Simple driver method. Creates the memory, and installs it to a CPU, which is then powered on.
		
		Memory m = new Memory("data.txt");
		CPU a = new CPU(m);
		
		a.powerOn();

	}
}

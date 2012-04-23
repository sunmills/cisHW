
public class BinaryFriend {

	
	// INTIALIZATION
	
	private String number;              // Object's decimal number
	private boolean isDecimal;			// or isInteger?
	
	// Binary breakdown of 'number'
	private String sign;				// for sign, 1 = negative, 0 = positive.
	private String exponent;
	private String mantissa;			// "significant digits"
	private String binary;				// sign + exponent + mantissa
	
	// Constructor.
	public BinaryFriend(String n){
		if(isNumeric(n)){
				setValues(n);
				finalizeValues();
			}
		else System.out.println("Input '"+n+"' is not numeric and is not useful.");
	}

	
	// Getters and setters.
	
	public String getSign(){
		return sign;
	}
	public void setSign(String sign){
		this.sign = sign;
	}
	public String getExponent(){
		return exponent;
	}
	public void setExponent(String exponent){
		this.exponent = exponent;
	}
	public String getMantissa(){
		return mantissa;
	}
	public void setMantissa(String mantissa){
		this.mantissa = mantissa;
	}
	public String getNumber(){
		return number;
	}
	public void setNumber(String number){
		this.number = number;
	}
	public String getBinary(){
		return binary;
	}
	public void setBinary(String binary){
		this.binary = binary;
	}
	public boolean isDecimal(){
		return isDecimal;
	}
	public void setDecimal(boolean isDecimal){
		this.isDecimal = isDecimal;
	}	
	
	
	// Methods
	
	private void setValues(String n){
		// Sets all the state variables for a new object. Used in the constructor to fully initialize object.
		// Determines if its an integer or not and delegates it appropriately. Sets the number state.
		if(!this.isDecimal()){
			intDecimalToBinary(n);
		}
		else{
			if(n.charAt(n.length()-1) == '.'){
				n = n.substring(0,n.length()-1);
				intDecimalToBinary(n);
			}
			else{
				fracDecimalToBinary(n);
			}
		}
		this.setNumber(n);
	}
	private void intDecimalToBinary(String n){
		// Converts the input string to an integer and passes it to another function that recursively switches
		// it to binary, ultimately returning the mantissa. Sets mantissa.
		int i = Integer.parseInt(n);
		this.setMantissa(this.intConverter(i, 0));
		this.setExponent(""+(this.intConverter(this.getMantissa().length() - this.getMantissa().indexOf(1)-2,0)));
	}
	public String intConverter(int i, int generation){
		// Does the work of conversion. We want to end recursion when we can no longer divide by two and get a new number.
		// The only numbers that can be represented by type int that meet that qualification are between -1 and 1.
		// Thus, as we did we all along, we return either 1 or 0. In all other cases, we simply divide by 2 and return the positive int
		// remainder (which is always either 1 or 0).
		// We additionally must make sure the mantissa doesn't exceed 23 characters.
		String result;
		if(generation>=23){
			result = "";
		}
		else{
			if(i<=1&&i>=-1){
				result=""+Math.abs(i);
			}
			else result = ""+this.intConverter(i/2,generation+1)+Math.abs(i%2);
		}
		return result;
	}
	private void fracDecimalToBinary(String n){
		// Converts input string to binary. Decimal version of intDecimalToBinary.
		// Converts the decimal portion of the string into a double and adds the binary conversion of the double
		// to the binary representation of the n's integer value.
		double i = Double.parseDouble(n.substring(n.indexOf('.'),n.length()));
		this.setMantissa(this.intConverter( Integer.parseInt(n.substring(0,n.indexOf('.'))) , 0 ));
		this.setExponent(""+(this.intConverter(this.getMantissa().length() - this.getMantissa().indexOf(1)-2,0)));
		this.setMantissa(this.getMantissa()+this.decConverter(i,0,n).substring(1,this.decConverter(i,0,n).length()));
	}
	private String decConverter(double i, int generation, String n){
		// The workhorse of the conversion process for decimal numbers. Ensures that the final mantissa, including the integer part,
		// is no more than 23 digits long. It then multiplies the number by 2. each time it multiplies the number, it adds the
		// digit in the ones place to the string.
		String result;
		if(this.getMantissa().length()+generation>23){
			result="";
		}
		else{
			if(i>=1){
				result = 1+this.decConverter((i-1)*2,generation+1,n);
			}
			else result = 0+this.decConverter(i*2,generation+1,n);
		}
		return result;
	}
	public static boolean isPositive(String n){
		// Checks if the input string might be a positive number (doesn't start with '-')
		boolean result;
		if(n.charAt(0)=='-') result = false;
		else result = true;
		return result;
	}
	private boolean isNumeric(String n){
		// public function for testing numerical validity. uses the isNumericTest to make the actual determination.
		return this.isNumericTest(n,true,false);
	}
	private boolean isNumericTest(String n, boolean b, boolean decimal){
		// Recursively checks each character to make sure its numeric.
		// Ignores the first - or . in the number, and returns non-numeric if there is a second instance.
		// Also determines the sign state
		boolean validity;
		boolean result;
		
		if(n.length() <= 0 || b == false){
			result = b;
		}
		else{
			if(n.charAt(0)=='-'&&this.getSign()==null){
				this.setSign("1");	
				result = this.isNumericTest(n.substring(1,n.length()), true, decimal);
			}
			else{
				if(n.charAt(0)!='-'&&this.getSign()==null) this.setSign("0");
				if(n.charAt(0)=='.'){
					if(decimal){
						validity = false;
					}
					else{
						validity = b;
						decimal = true;
						this.setDecimal(true);
					}
				}
				else{
					validity = Character.isDigit(n.charAt(0));
				}
				result = this.isNumericTest(n.substring(1,n.length()), validity, decimal);	
			}
		}
		return result;
	}
	private void finalizeValues(){
		// This method 'finalizes' the raw binary values given to the object, makes them the appropriate length.
		// This function sets the final binary value of any given number.
		if(this.getMantissa().length()<23||this.getExponent().length()<8){
			if(this.getMantissa().length()<23){
				this.setMantissa(this.getMantissa()+"0");
				finalizeValues();
			}
			if(this.getExponent().length()<8){
				this.setExponent("0"+this.getExponent());
				finalizeValues();
			}
		}
		else this.setBinary(this.getSign()+this.getExponent()+this.getMantissa());
	}
	public double toDecimal(){
		// This method begins the process of going back to a base 10 number.
		// If the number is a decimal, it first changes the integer back to decimal. It then adds the decimal version of
		// the binary to the integer after converting it.
		double result;
		if(this.isDecimal()){
			int integer = BinaryFriend.intBinaryToDecimal(this.getMantissa().substring(0,BinaryFriend.intBinaryToDecimal(this.getExponent(),0)+1),0);
			double decimal = BinaryFriend.decBinaryToDecimal(this.getMantissa().substring(BinaryFriend.intBinaryToDecimal(this.getExponent(),0)),0);
			result = integer + decimal;
		}
		else{
			result = BinaryFriend.intBinaryToDecimal(this.getMantissa().substring(0,BinaryFriend.intBinaryToDecimal(this.getExponent(), 0)+1),0);
		}
		
		return this.fixSign(result);
	}
	private static int intBinaryToDecimal(String binary, int generation){
		// Converts integers from binary to decimal.
		// While the number of characters processed is less than the total number of characters, it raises the digit times 2
		// to the power of the generation. It goes from the end to the beginning, so that the first digit in the sequence
		// is the highest.
		int result;
		int newNumber;
		int recursiveNumber;
		if(generation>=binary.length()){
			result = 0;
		}
		else{
			int generationAlt;
			if(generation==0) generationAlt = 1;
			else generationAlt = generation;
			newNumber =(int)( Math.pow((2),generation)*Math.pow(Character.getNumericValue(binary.charAt(binary.length()-1-generation)),generationAlt));
			recursiveNumber = BinaryFriend.intBinaryToDecimal(binary, generation+1);
			result = newNumber+recursiveNumber;
		}
		return result;
	}
	private static double decBinaryToDecimal(String binary, int generation){
		// Converts binary decimal values to base ten.
		// While the number of characters processed is less than the total number of character, it raises the digit time 1/2
		// to the power of the generation. It goes from the end to the beginning, so that the first digit in the sequence has
		// the greatest value.
		double result;
		double newNumber;
		double recursiveNumber;
		if(generation>=binary.length()){
			result = 0;
		}
		else{
			int generationAlt;
			int helper=1;
			if(generation==0) { 
				generationAlt = 1;
				helper=0;
			}
			else generationAlt = generation;
				newNumber = helper*BinaryFriend.round(Math.pow((0.5),generation)*Math.pow((Character.getNumericValue(binary.charAt(generation))),generationAlt));
				recursiveNumber = BinaryFriend.decBinaryToDecimal(binary, generation+1);
				result = newNumber+recursiveNumber;	
		}
		return result;
	}
	public static double round(double n) {
		// This is a helper method that rounds digits to make sure things don't get too crazy.
		double result = n * 100000000;
		result = Math.round(result);
		result = result / 100000000;
		return result;
	}
	private double fixSign(double n){
		// This method checks the objects sign and makes the input the appropriate sign.
		double result;
		n = Math.abs(n);
		if(this.getSign()=="1") result = n*(-1);
		else result = n;
		return result;
	}
	public String addBinary(BinaryFriend friend){
		// This method will facilitate a process by which this object is added to another.
		// It simple concatenates the appropriate parts of the strings to get a string together.
		String result;
		if(this.getExponent().equals("00000001")&&!friend.getExponent().equals("00000001")){
			result = "0"+friend.getExponent();
			String shiftedDec = this.getMantissa().substring(0, this.getMantissa().length()-BinaryFriend.intBinaryToDecimal(friend.getExponent(), 0)+1);
			result = result+friend.getMantissa().substring(0,BinaryFriend.intBinaryToDecimal(friend.getExponent(), 0)-1)+shiftedDec;
		}
		else if(friend.getExponent().equals("00000001")&&!this.getExponent().equals("00000001")){
			result = "0"+this.getExponent();
			String shiftedDec = friend.getMantissa().substring(0, friend.getMantissa().length()-BinaryFriend.intBinaryToDecimal(this.getExponent(), 0)+1);
			result = result+this.getMantissa().substring(0,BinaryFriend.intBinaryToDecimal(this.getExponent(), 0)-1)+shiftedDec;		
			}
		else{
			result = "errorororo!";
		}
		return result;
	}
}
	

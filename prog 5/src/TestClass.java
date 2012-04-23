
public class TestClass {

	public static void main(String[] args){
		
		BinaryFriend n = new BinaryFriend("100");
		System.out.println("Number: "+n.getNumber());
		System.out.println("isDecimal?: "+n.isDecimal());
		System.out.println("Sign: "+n.getSign());
		System.out.println("Exponent: "+n.getExponent());
		System.out.println("Mantissa: "+n.getMantissa());
		System.out.println("Binary: "+n.getBinary());
		System.out.println("Binary: 00000011011001000000000000000000 <= from assignment");
		System.out.println("Convert back: "+n.toDecimal()); // Tests toDecimal
		System.out.println();

		
		BinaryFriend m = new BinaryFriend("3.141592654");
		System.out.println("Number: "+m.getNumber());
		System.out.println("isDecimal?: "+m.isDecimal());
		System.out.println("Sign: "+m.getSign());
		System.out.println("Exponent: "+m.getExponent());
		System.out.println("Mantissa: "+m.getMantissa());
		System.out.println("Binary: "+m.getBinary());
		System.out.println("Binary: 00000000111001001000011111101101 <= from assignment");
		System.out.println("Convert back: "+m.toDecimal()); // Tests toDecimal
		System.out.println();
		
		System.out.println(m.addBinary(n));
		System.out.println(n.addBinary(m));
		System.out.println("00000011011001110010010000111111");
		
	}
}

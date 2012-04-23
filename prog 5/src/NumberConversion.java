
public class NumberConversion {
	
	public static void main(String[] args){
		BinaryFriend a = new BinaryFriend("100");
		BinaryFriend b = new BinaryFriend("3.141592654");
		BinaryFriend c = new BinaryFriend("1.3333333");
		BinaryFriend d = new BinaryFriend("15.725");
		
		System.out.println("Decimal -> binary");
		System.out.println("Decimal "+a.getNumber()+" = "+a.getBinary()+" base 2");
		System.out.println("Decimal "+b.getNumber()+" = "+b.getBinary()+" base 2");
		System.out.println("Decimal "+c.getNumber()+" = "+c.getBinary()+" base 2");
		System.out.println("Decimal "+d.getNumber()+" = "+d.getBinary()+" base 2");
		System.out.println();
		System.out.println("Binary -> decimal");
		System.out.println("Binary "+a.getBinary()+" = "+a.toDecimal()+" base 10");
		System.out.println("Binary "+b.getBinary()+" = "+b.toDecimal()+" base 10");
		System.out.println("Binary "+c.getBinary()+" = "+c.toDecimal()+" base 10");
		System.out.println("Binary "+d.getBinary()+" = "+d.toDecimal()+" base 10");
		System.out.println();
		System.out.println("Addition");
		BinaryFriend ab = new BinaryFriend(a.getNumber()+b.getNumber());
		System.out.println(a.getNumber()+" + "+b.getNumber()+" = "+a.addBinary(b)+" Base 2");
		System.out.println(a.addBinary(b)+" = "+ab.toDecimal());
		
	}

}

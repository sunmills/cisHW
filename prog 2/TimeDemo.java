
public class TimeDemo {

	// Initialization
	public static void main(String[] args){
		EarthTime erTime1 = new EarthTime(3,5,15,21);
		EarthTime erTime2 = new EarthTime(3,2,8,45);
		EarthTime erTime3 = new EarthTime();
		
		PsarTime psTime1 = new PsarTime(5,150,10,23);
		PsarTime psTime2 = new PsarTime(15,19,32,49);
		
		String word = " has been defined to be: ";
		// Do stuff:
		
		System.out.println("erTime1"+word+erTime1);
		System.out.println("erTime2"+word+erTime2);
		System.out.println("erTime3"+word+erTime3);
		System.out.println("psTime1"+word+psTime1);
		System.out.println("psTime2"+word+psTime2);
		
		System.out.println();
		
		System.out.println("erTime1 + erTime2 = "+erTime1.addEarthTime(erTime2));
		System.out.println("erTime2 + erTime4 = "+erTime2.addEarthTime(erTime3));
		System.out.println("erTime1 - erTime2 = "+erTime1.subEarthTime(erTime2));
		System.out.println("psTime2 + psTime1 = "+EarthTime.convert(psTime2.addPsarTime(psTime1)));
		System.out.println("psTime1 + psTime2 = "+EarthTime.convert(psTime1.addPsarTime(psTime2)));
		System.out.println("erTime1 + psTime1 = "+erTime1.addPsarTime(psTime1));
		System.out.println("erTime3 - psTime2 = "+erTime3.subPsarTime(psTime2));
		System.out.println("psTime2 + erTime1 = "+EarthTime.convert(psTime2.addEarthTime(erTime1)));
		System.out.println("psTime2 - erTime1 = "+EarthTime.convert(psTime2.subEarthTime(erTime1)));
		

		
	}
}

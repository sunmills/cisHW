
public class PsarTime {
	
	// Initialization
	private int asurs;
	private int cromas;
	private int cies;
	private int sparens;
	
	// Constructors
	
	// Constructor with arguments
	public PsarTime(int asurs, int cromas, int cies, int sparens) {
		setAsurs(asurs);
		setCromas(cromas);
		setCies(cies);
		setSparens(sparens);
	}
	// Constructor with default values
	public PsarTime(){
		setAsurs(0);
		setCromas(0);
		setCies(0);
		setSparens(0);
	}
	

	// Behaviors
	
	// Getters
	public int getAsurs(){
		return asurs;
	}
	public int getCromas(){
		return cromas;
	}
	public int getCies(){
		return cies;
	}
	public int getSparens(){
		return sparens;
	}

	// Setters
	public void setAsurs(int asur){
		asurs = asur;
	}
	public void setCies(int cie){
		cies = cie;
	}
	public void setCromas(int croma){
		cromas = croma;
	}
	public void setSparens(int sparen){
		sparens = sparen;
	}

	
	// Conversion behaviors
	
	private static PsarTime clockFriend(int totalSparen){
		// This method describes a way to convert sparen into an output/clock friendly format
		
		// Work our way to Asurs
		
		int totalCies 	= totalSparen/40;
		int totalCromas = totalCies/36;
		int totalAsurs  = totalCromas/500;
		
		// Undo double-counting and make the intermediate units clock-friendly.
		
		totalSparen 	= totalSparen % 40;
		totalCies 		= totalCies % 36;
		totalCromas 	= totalCromas % 500;
		
		
		PsarTime pt 	= new PsarTime(totalAsurs, totalCromas, totalCies, totalSparen);
		return pt;
	}
	public static PsarTime convert(EarthTime et){
		
		// Translates an earth date into a star date.
		// 	Converts everything to minutes, then into Sparen,
		//	then back into higher units by way of our clockFriend.
		
		int totalMinutes = ( et.getYears()*365*24*60 ) + ( et.getDays()*24*60 ) + ( et.getHours()*60 ) + ( et.getMinutes() );
		int totalSparen  = totalMinutes;
		
		return clockFriend(totalSparen);
	}
	public PsarTime addPsarTime(PsarTime pt){
		
		// Adds two PsarTime objects together and adjusts for proper clock-time
		//	(i.e., clocks don't represent 61 minutes, but rather and 1 hour and 1 minute)
		
		int totalAsurs  = this.getAsurs()+pt.getAsurs();
		int totalCromas = this.getCromas()+pt.getCromas();
		int totalCies   = this.getCies()+pt.getCies();
		int totalSparen = this.getSparens()+pt.getSparens();
		
		totalCies 		= totalCies + (totalSparen/40);
		totalSparen 	= totalSparen % 40;
		
		totalCromas 	= totalCromas + (totalCies/36);
		totalCies 		= totalCies % 36;
		
		totalAsurs 		= totalAsurs + (totalCromas/500);
		totalCromas 	= totalCromas % 500;
		
		PsarTime newpt  = new PsarTime(totalAsurs, totalCromas, totalCies, totalSparen);
		return newpt;
	}
	public PsarTime addEarthTime(EarthTime et){
		
		// Converts an EarthTime object into a PsarTime object and adds them with the above method.
		
		PsarTime etPsarTime   = new PsarTime(convert(et).getAsurs(),convert(et).getCromas(),convert(et).getCies(),convert(et).getSparens());
		PsarTime thisPsarTime = new PsarTime(this.getAsurs(), this.getCromas(), this.getCies(), this.getSparens());
		PsarTime sumTime      = thisPsarTime.addPsarTime(etPsarTime);
		return sumTime;
	}
	public PsarTime subPsarTime(PsarTime pt){
		
		// The easiest way to deal with subtraction is to work with sparens, since
		//	there wont be unit conversion issues. After converting and doing
		// 	the math, we convert back into proper clock units using our clockFriend.
		
		int totalSparen =	 ((( this.getAsurs()*500*36*40) + ( this.getCromas()*36*40) + (this.getCies()*40) + (this.getSparens()))
							-((  pt.getAsurs()*500*36*40  ) + ( pt.getCromas()*36*40  ) + ( pt.getCies()*40 ) + ( pt.getSparens())));
		
		return clockFriend(totalSparen);
	}
	public PsarTime subEarthTime(EarthTime et){
		
		// Lets convert into PsarTime and then subtract like we normally would.
		
		PsarTime etPsarTime   = convert(et);
		PsarTime thisPsarTime = new PsarTime(this.getAsurs(), this.getCromas(), this.getCies(), this.getSparens());
		PsarTime diffTime     = thisPsarTime.subPsarTime(etPsarTime);
		return diffTime;
	}
	
	
	public String toString(){
		String clockReading = this.getAsurs()+"A:"+this.getCromas()+"Cr:"+this.getCies()+"Ci:"+this.getSparens()+"S";
		return clockReading;
	}
}

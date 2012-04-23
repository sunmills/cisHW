
public class EarthTime {
	
	// Initialization
	private int years;
	private int days;
	private int hours;
	private int minutes;
	
	// Constructors
	
	// Constructor with arguments
	public EarthTime(int years, int days, int hours, int minutes) {
		setYears(years);
		setDays(days);
		setHours(hours);
		setMinutes(minutes);
	}
	// Constructor with default values
	public EarthTime(){
		setYears(0);
		setDays(0);
		setHours(0);
		setMinutes(0);
	}
	

	// Behaviors
	
	// Getters
	public int getYears(){
		return years;
	}
	public int getDays(){
		return days;
	}
	public int getHours(){
		return hours;
	}
	public int getMinutes(){
		return minutes;
	}

	// Setters
	public void setYears(int year){
		years = year;
	}
	public void setHours(int hour){
		hours = hour;
	}
	public void setDays(int day){
		days = day;
	}
	public void setMinutes(int minute){
		minutes = minute;
	}

	// Conversion behaviors
	
	private static EarthTime clockFriend(int totalMinutes){
		// This method describes a way to convert minutes into an output/clock friendly format
		
		// Work our way to Years
		
		int totalHours = totalMinutes/60;
		int totalDays  = totalHours/24;
		int totalYears = totalDays/365;
		
		// Undo double-counting and make the intermediate units clock-friendly.
		
		totalMinutes   = totalMinutes % 60;
		totalHours 	   = totalHours % 24;
		totalDays 	   = totalDays % 365;
		
		// New EarthTime object that contains the output variables
		EarthTime pt   = new EarthTime(totalYears, totalDays, totalHours, totalMinutes);
		return pt;
	}
	public static EarthTime convert(PsarTime et){
		
		// Translates an psar date into an earth date.
		// 	Converts everything to sparens, then into Minutes,
		//	then back into higher units by way of our clockFriend.
		
		int totalSparens = ( et.getAsurs()*500*36*40 ) + ( et.getCromas()*36*40 ) + ( et.getCies()*40 ) + ( et.getSparens() );
		int totalMinute  = totalSparens;
		
		return clockFriend(totalMinute);
	}
	public EarthTime addEarthTime(EarthTime pt){
		
		// Adds two EarthTime objects together and adjusts for proper clock-time
		//	(i.e., clocks don't represent 61 minutes, but rather and 1 hour and 1 minute)
		
		int totalYears  = this.getYears()+pt.getYears();
		int totalDays 	= this.getDays()+pt.getDays();
		int totalHours  = this.getHours()+pt.getHours();
		int totalMinute = this.getMinutes()+pt.getMinutes();
		
		totalHours 	= totalHours + (totalMinute/60);
		totalMinute = totalMinute % 60;
		
		totalDays 	= totalDays + (totalHours/24);
		totalHours	= totalHours % 24;
		
		totalYears 	= totalYears + (totalDays/365);
		totalDays 	= totalDays % 365;
		
		EarthTime newpt  = new EarthTime(totalYears, totalDays, totalHours, totalMinute);
		return newpt;
	}
	public EarthTime addPsarTime(PsarTime et){
		
		// Converts a PsarTime object into a EarthTime object and adds them with the above method.
		
		EarthTime etEarthTime 	= convert(et);
		EarthTime thisEarthTime = new EarthTime(this.getYears(), this.getDays(), this.getHours(), this.getMinutes());
		EarthTime sumTime       = thisEarthTime.addEarthTime(etEarthTime);
		return sumTime;
	}
	public EarthTime subEarthTime(EarthTime pt){
		
		// The easiest way to deal with subtraction is to work with minutes, since
		//	there wont be unit conversion issues. After converting and doing
		// 	the math, we convert back into proper clock units using our clockFriend.
		
		int totalMinute =	 ((( this.getYears()*365*24*60) + ( this.getDays()*24*60) + (this.getHours()*60) + (this.getMinutes()))
							-((  pt.getYears()*365*24*60  ) + ( pt.getDays()*24*60  ) + ( pt.getHours()*60 ) + ( pt.getMinutes())));
		
		return clockFriend(totalMinute);
	}
	public EarthTime subPsarTime(PsarTime et){
		
		// Lets convert into EarthTime and then subtract like we normally would.
		
		EarthTime etEarthTime 	= convert(et);
		EarthTime thisEarthTime = new EarthTime(this.getYears(), this.getDays(), this.getHours(), this.getMinutes());
		EarthTime diffTime 		= thisEarthTime.subEarthTime(etEarthTime);
		return diffTime;
	}
	
	
	public String toString(){
		String clockReading = this.getYears()+"Y:"+this.getDays()+"D:"+this.getHours()+"H:"+this.getMinutes()+"M";
		return clockReading;
	}
}



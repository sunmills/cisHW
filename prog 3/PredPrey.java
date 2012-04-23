
public class PredPrey {
	
	// State variables and initialization
	
	private double birthRate;
	private double SearchEff;
	private double FoodConv;
	private double DeathRate;
	
	// Construction ahead (constructors)
	
	public PredPrey(){
		// Default constructor us given in specifications
		setbirthRate(0.75);
		setSearchEff(0.05);
		setFoodConv(0.1);
		setDeathRate(0.25);
	}
	
	public PredPrey(double birthrate, double searcheff, double foodconv, double deathrate) {
		// Custom constructor as required in specifications
		setbirthRate(birthrate);
		setSearchEff(searcheff);
		setFoodConv(foodconv);
		setDeathRate(deathrate);
	}
	
	
	// getters and setters
	// GET
	public double getbirthRate(){
		return birthRate;
	}
	public double getSearchEff(){
		return SearchEff;
	}
	public double getFoodConv(){
		return FoodConv;
	}
	public double getDeathRate(){
		return DeathRate;
	}
	// SET
	public void setbirthRate(double birthrate){
		birthRate = birthrate;
	}
	public void setSearchEff(double searcheff){
		SearchEff = searcheff;
	}
	public void setFoodConv(double foodconv){
		FoodConv = foodconv;
	}
	public void setDeathRate(double deathrate){
		DeathRate = deathrate;
	}
	
	
	// Doer methods

	public void userFriend(int PreyCnt, int PredatorCnt, int generations){
		// User friendly method; omits count argument. Calls the recursive helper method.
		lotkaVolterraModel(0,PreyCnt,PredatorCnt,generations);
	}
	private void lotkaVolterraModel(int generation, int PreyCnt, int PredatorCnt, int generations){
		// Helper method that does all of the work recursively.
		// If it meets certain case requirements, it will end simulation.
		// Else, it will print current generations information, and call its self again with the next generation's data.
		
		// Specifications made no mention of declaring *why* the recursion has ended. I would posit that
		// Ira doesn't need to know this information.
		if(generation > generations || PreyCnt < 0 || PredatorCnt < 0){
			System.out.println("Simulation Ended.");
		}
		else {
			String message = "Generation="+generation+" "+" Prey="+PreyCnt+" "+" Predator="+PredatorCnt;
			System.out.println(message);
			this.lotkaVolterraModel(generation+1,this.dPreyCnt(PreyCnt,PredatorCnt), this.dPredatorCnt(PreyCnt,PredatorCnt), generations);
		}
	}
	private int dPreyCnt(int PreyCnt, int PredatorCnt){
		// Separate function for readability purposes; calculates the change in PreyCnt per change in time.
		// Uses formulat given in specifications.
		int dPreyCnt = (int)(PreyCnt+((this.getbirthRate()*PreyCnt)-(this.getSearchEff()*PreyCnt*PredatorCnt)));
		return dPreyCnt;
	}
	private int dPredatorCnt(int PreyCnt, int PredatorCnt){
		// Separate function for readability purposes; calculates the change in PredatorCnts per change in time.
		// Uses formula given in specifications.
		int dPredatorCnt = (int)(PredatorCnt+((getFoodConv()*getSearchEff()*PreyCnt)-(getDeathRate()*PredatorCnt)));
		return dPredatorCnt;
	}
	

}

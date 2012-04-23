
public class TestCases {

	public static void main(String[] args){
		PredPrey deadPrey = new PredPrey();
		deadPrey.userFriend(1, 55, 7);
		
		System.out.println();
		
		PredPrey deadPred = new PredPrey(.25, -.5, 0.1, 1);
		deadPred.userFriend(10, 6, 10);
		
		System.out.println();
		
		PredPrey endOfTime = new PredPrey();
		endOfTime.userFriend(10, 0, 4);
	}
}

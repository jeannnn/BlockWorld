public class LocalClimbing extends HillClimbing {
	
	public LocalClimbing() {
		debug = true;
		isGlobal = false;
	}
//////////////////////////////////////
//////////////////////////////////////
/////////////	PRIVATE FUNCTIONS	///////////////
//////////////////////////////////////
	
	protected void EvaluateState(State dumbState) {
		
		int dumbStateOnBlockSize = dumbState.OnPredicate.size();
		int totalScore = 0;
		
			for (int j = 0; j < dumbStateOnBlockSize; j++) {
				
					String dumbBlock = new String(dumbState.OnPredicate.get(j));
					String goalBlock = new String(goalState.getOnPredicate(dumbBlock));
					
					if (goalBlock.equals(dumbBlock))
						totalScore++;
			}
		
		dumbState.setPrior(totalScore);
	}
	
}

public class GlobalClimbing extends HillClimbing {
	
	public GlobalClimbing() {
		debug = false;
		isGlobal = true;
	}
	
//////////////////////////////////////
//////////////////////////////////////
/////////////	PRIVATE FUNCTIONS	///////////////
//////////////////////////////////////
	
	
	protected void EvaluateState(State dumbState) {
		
		int dumbStateClearBlockSize = dumbState.ClearPredicate.size();
		int eachBlockscore = 0, totalScore = 0;
		

			
			for (int j = 0; j < dumbStateClearBlockSize; j++) {
				
					eachBlockscore = 0;
					String dumbBlock = new String(dumbState.ClearPredicate.get(j));
					String goalBlock = new String(goalState.getOnPredicate(dumbBlock));
					
					dumbBlock = dumbState.getOnPredicate(dumbBlock);
					
					while (goalBlock.equals(dumbBlock)) {
								
						eachBlockscore++;
						goalBlock = goalBlock.substring(1);
						dumbBlock = dumbBlock.substring(1);
								
						if (goalBlock.equals("0")) {
							totalScore += eachBlockscore;
							break;
						}
								
						goalBlock = goalState.getOnPredicate(goalBlock);
						dumbBlock = dumbState.getOnPredicate(dumbBlock);	
						
					}
			}
		
		dumbState.setPrior(totalScore);
	}
	
}

import java.util.Comparator;
import java.util.PriorityQueue;


public abstract class HillClimbing extends CoreSearch {
	protected PriorityQueue<State> PriorQueue;
	protected Comparator<State> comparator;
	protected boolean debug, isGlobal;
	protected State goalState;
	
	public void init(ControlState newControl) {
		
		control = newControl;
		comparator = new EvaluationComparator();
		PriorQueue = new PriorityQueue<State>(10, comparator);
		PriorQueue.add(control.getStartState());
		
		goalState = control.getGoalState();
	}
	
	public void search() {
		
		if (isGlobal)
			System.out.println("GLOBAL HILL CLIMBING SEARCH------------");
		else
			System.out.println("LOCAL HILL CLIMBING SEARCH------------");

		if (PriorQueue.isEmpty()) {
			System.out.println("queue empty");
			return;
		}
		
		if (control.checkGoal(PriorQueue.peek())) {
			System.out.println("Goal at first state");
			PriorQueue.peek().showState();
			return;
		}
		
		PollQueue();
		
	}
	
//////////////////////////////////////
//////////////////////////////////////
/////////////	protected FUNCTIONS	///////////////
//////////////////////////////////////
	
	protected void PollQueue() {
		
		State currentState;
		while (!PriorQueue.isEmpty()) {
			
			currentState = PriorQueue.poll();
			currentState.addClear("0"); 
			
			if (debug) {
				System.out.println("---------Queue poll-----------");
				currentState.showState();
			}
			
			changeStateFromCurrentState(currentState);
			if (reachGoal) {
				System.out.printf("########### Total states %d ############## \n", numState);
				return;
			}
		
		}
		System.out.println("NO SOLUTIONS------------");

	}
		
	protected void checkNewState(State dumbState, State currentState) {
		
		if (!control.checkDup(dumbState)) {
			
			dumbState.removeClear("0");
			
			EvaluateState(dumbState);
			
			if (checkGoal(dumbState, currentState))
				return;
			
			
			
			State newState = control.copyState(dumbState);
			numState++;
			newState.setStateNum(numState);
			newState.setParentStateNum(currentState.getStateNum());
																	
			control.addState(newState);
			PriorQueue.add(newState);
			
			newState.showState();
			
			
		}
	}
	
//////////////////////////////////////
//////////////////////////////////////
/////////////	ABSTRACT FUNCTIONS	///////////////
//////////////////////////////////////

	abstract protected void EvaluateState(State dumbState);
}

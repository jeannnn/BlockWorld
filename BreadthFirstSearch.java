import java.util.LinkedList;
import java.util.Queue;


public class BreadthFirstSearch extends CoreSearch{
	
	private Queue<State> queueState;
	private boolean debug = false;

	
	public void init(ControlState newControl) {
		
		control = newControl;
		queueState = new LinkedList<State>();
		queueState.add(control.getStartState());
		
	}
	
	public void search() {
		
		System.out.println("BFS SEARCH------------");

		if (queueState.isEmpty()) {
			System.out.println("queue empty");
			return;
		}
		
		if (control.checkGoal(queueState.peek())) {
			System.out.println("Goal at first state");
			queueState.peek().showState();
			return;
		}
		
		PollQueue();
		
	}
	
//////////////////////////////////////
/////////////	CORE FUNCTIONS	///////////////
//////////////////////////////////////
	
	private void PollQueue() {
		
		State currentState;
		while (!queueState.isEmpty()) {
			
			currentState = queueState.poll();
			currentState.addClear("0"); 
			
			if (debug) {
				System.out.println("Queue poll");
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
			
			if (checkGoal(dumbState, currentState))
				return;
			
			State newState = control.copyState(dumbState);
			numState++;
			newState.setStateNum(numState);
			newState.setParentStateNum(currentState.getStateNum());
																	
			control.addState(newState);
			queueState.add(newState);
			
			
		}
	}
	
}

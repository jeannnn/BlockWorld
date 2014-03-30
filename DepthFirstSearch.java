import java.util.Stack;

public class DepthFirstSearch extends CoreSearch {
	private Stack<State> stackState;
	private boolean debug = false;
	
	public void init(ControlState newControl) {
		
		control = newControl;
		stackState = new Stack<State>();
		stackState.add(control.getStartState());
		
	}
	
	public void search() {
		
		System.out.println("DFS SEARCH------------");

		if (stackState.isEmpty()) {
			System.out.println("stack empty");
			return;
		}
		
		if (control.checkGoal(stackState.peek())) {
			System.out.println("Goal at first state");
			stackState.peek().showState();
			return;
		}
		
		PopStack();
		
	}
	
	
	
//////////////////////////////////////
/////////////	CORE FUNCTIONS	///////////////
//////////////////////////////////////
	
	private void PopStack() {
		
		State currentState;
		while (!stackState.isEmpty()) {
			
			currentState = stackState.pop();
			currentState.addClear("0"); 
			
			if (debug) {
				System.out.println("Stack pop");
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
			stackState.add(newState);
			
			
		}
	}
	
}

import java.util.*;

public class ControlState {

	private List<State> listState;
	private State goalState; 
	
	public ControlState() {
		 listState = new ArrayList<State>();
	}
	
	public void addState(State newState) {
		
		listState.add(newState);
		
	}
	
	public void setGoal(State goal) {
		
		goalState = goal;
		
	}
	
	public State getStartState() {
		return listState.get(0);
	}
	
	public State getGoalState() {
		return goalState;
	}
	
	public boolean checkDup(State newState) {
		
		Iterator<State>	iterator = listState.iterator();
		while(iterator.hasNext()) {
			
			State oldState = (State) iterator.next();
			
			if (compareTwoListPredicate(oldState.OnPredicate, newState.OnPredicate))
				return true;
		}
		
		return false;
	}
	
	public boolean checkGoal(State newState) {
		
		if (listState.isEmpty())
			return false;
		
		return compareTwoListPredicate(newState.OnPredicate, goalState.OnPredicate);
	}
	
	public State copyState(State source) {
		
		if (source == null)
			return null;
		
		List<String> OnPredicate = new ArrayList<String>();
		List<String> ClearPredicate = new ArrayList<String>();
		
		source.copyOnPredicate(OnPredicate);
		source.copyClearPredicate(ClearPredicate);
		
		State targetState = new State();
		
		targetState.setOnPredicate(OnPredicate);
		targetState.setClearPredicate(ClearPredicate);
		
		targetState.setStateNum(source.getStateNum());
		targetState.setParentStateNum(source.getParentStateNum());
		targetState.setPrior(source.getPrior());
		
		return targetState;
	}
	
	public void traceState(State endState) {
		
		State tracing = endState;
		
		while (tracing.getStateNum() != 0) {
			
			tracing.showState();
			tracing = listState.get(tracing.getParentStateNum());
			
		}
		
		tracing.showState();
	}
	
	
	public void showListState() {
		
		System.out.println("Show list state");
		Iterator<State> ite;
		ite = listState.iterator();
		while (ite.hasNext()) {
			
			State state = (State) ite.next();
			state.showState();
		}
			
	}
	
	//////////////////////////////////////
	//////////////////////////////////////
	/////////////	UTILITY FUNCTIONS	///////////////
	//////////////////////////////////////
	
	
	private boolean compareTwoListPredicate(List<String> sourceList, List<String> comparedList) {
		
		if (sourceList.size() != comparedList.size())
			return false;
		
		int size = sourceList.size();
		
		boolean flag = false;
			
		for (int indexSource = 0; indexSource < size; indexSource++) { 
			
			flag = false;
			
			for (int indexCmp = 0; indexCmp < size; indexCmp++)
					
				if ( comparedList.get(indexCmp).equals(sourceList.get(indexSource)) ) {
					flag = true;
					break;
				}
				
			if (!flag)
				return false;
		}
		
		return true;
		
	}
}

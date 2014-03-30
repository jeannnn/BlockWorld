import java.util.*;

public class Main {

	public static void main(String[] args) {
			
		State state1 = new State();
					
		state1.addOn("AB");
		state1.addOn("BC");
		state1.addOn("CD");
		state1.addOn("DE");
		state1.addOn("EF");
		state1.addOn("F0");
		state1.addClear("A");
		
		
		state1.setStateNum(0);
		state1.setParentStateNum(0);
			
		state1.showState();

		State stateGoal = new State();
		
		stateGoal.addOn("FE");
		stateGoal.addOn("ED");
		stateGoal.addOn("DC");
		stateGoal.addOn("CB");
		stateGoal.addOn("BA");
		stateGoal.addOn("A0");
		stateGoal.addClear("F");
		
		ControlState control = new ControlState();
		control.addState(state1);
		control.setGoal(stateGoal);
		
		//control.showListState();
		
		Search bfs = new BreadthFirstSearch();
		Search dfs = new DepthFirstSearch();
		Search globalClimb = new GlobalClimbing();
		Search localClimb = new LocalClimbing();
		
		
		//globalClimb.init(control);
		//globalClimb.search();
		
		localClimb.init(control);
		localClimb.search();
		
		//bfs.init(control);
		//bfs.search();
		
	}

}

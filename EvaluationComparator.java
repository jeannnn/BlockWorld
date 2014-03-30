import java.util.Comparator;

public class EvaluationComparator implements Comparator<State>{
	@Override
	public int compare(State x, State y) {
		
		if (x.getPrior() > y.getPrior())
			return -1;
		if (x.getPrior() < y.getPrior())
			return 1;
		
		return 0;
	}
}

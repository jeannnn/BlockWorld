import java.util.*;

public class State {
	
	protected List<String> OnPredicate;
	protected List<String> ClearPredicate;
	private int stateNum, parentStateNum, prior;
	
	public State() {
		prior = -1;
		stateNum = -1;
		parentStateNum = -1;
		OnPredicate = new ArrayList<String>();
		ClearPredicate = new ArrayList<String>();
	}
	
	public void setStateNum(int num) {
		stateNum = num;
	}
	
	public void setParentStateNum(int num) {
		parentStateNum = num;
	}
	
	public void setPrior(int num) {
		prior = num;
	}
	
	public int getPrior() {
		return prior;
	}
	
	public int getStateNum() {
		return stateNum;
	}
	
	public int getParentStateNum() {
		return parentStateNum;
	}
	
	public void setOnPredicate(List<String> NewOnPredicate) {
		OnPredicate = NewOnPredicate;
	}
	
	public void setClearPredicate(List<String> NewClearPredicate) {
		ClearPredicate = NewClearPredicate;
	}
	
	public void addOn(String onPre) {
		
		if (!OnPredicate.contains(onPre)) 
			OnPredicate.add(onPre);
		
	}
	
	public String removeOn(String upperBlock) {
		
		int i;
		
		for (i = 0; i < OnPredicate.size(); i++) {
			if (OnPredicate.get(i).codePointAt(0) == upperBlock.codePointAt(0))
				break;
		}
		
		String s = OnPredicate.get(i);
		OnPredicate.remove(i);
		
		return s;
		
	}
	
	public void addClear(String clearPre) {

		if (ClearPredicate.isEmpty())
			ClearPredicate.add(clearPre);
			
		for (int i = 0; i < ClearPredicate.size(); i++) {
			
			if (ClearPredicate.get(i).codePointAt(0) == clearPre.codePointAt(0))
				return;
			
			if (ClearPredicate.get(i).codePointAt(0) > clearPre.codePointAt(0)) {
				ClearPredicate.add(ClearPredicate.indexOf(ClearPredicate.get(i)), clearPre);
				break;
			}
			
			if (i == ClearPredicate.size() - 1)
				ClearPredicate.add(clearPre);
		}
	}
	
	public void removeClear(String clearPre) {
		
		if (ClearPredicate.contains(clearPre))
			ClearPredicate.remove(clearPre);
	}
	
	public void copyOnPredicate(List<String> onList) {
		
		if (OnPredicate.size() == 0)
			return;
		
		for(String s : OnPredicate) {
			String element = new String(s);
			onList.add(element);
		}
		
	}
	
	public void copyClearPredicate(List<String> clearList) {
		
		if (ClearPredicate.size() == 0)
			return;
		
		for(String s : ClearPredicate) {
			String element = new String(s);
			clearList.add(element);
		}
			
	}
	
	public String getOnPredicate(String upperBlock) {
		
		for (int i = 0; i < OnPredicate.size(); i++) {
			if (OnPredicate.get(i).codePointAt(0) == upperBlock.codePointAt(0))
				return OnPredicate.get(i);
		}
		
		return null;
	}
	
	public void showState() {
		
		System.out.printf("State %d , Parent State %d\n", stateNum, parentStateNum);
		if (prior != -1)
			System.out.printf("Priority %d \n", prior);
		
		for (String s : OnPredicate)
			System.out.printf("%s ",s);
		System.out.println();
		
		for (String s :	ClearPredicate)
			System.out.printf("%s ",s);
		System.out.println();
	}
}

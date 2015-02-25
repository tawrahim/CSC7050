/**
 * 
 * @author tawheedraheem
 *
 */
public class State {
	
	private boolean isLastState;
	private boolean isFirstState;
	private String name;
	private String value;
	private String nextState;
	
	
	public State(String name, String value, boolean isFirstState, boolean isLastState, String nextState) {
		this.name = name;
		this.value = value;
		this.nextState = nextState;
		this.isFirstState = isFirstState;
		this.isLastState = isLastState;
	}
	
	public boolean isLastState() {
		return isLastState;
	}
	public void setLastState(boolean isLastState) {
		this.isLastState = isLastState;
	}
	public boolean isFirstState() {
		return isFirstState;
	}
	public void setFirstState(boolean isFirstState) {
		this.isFirstState = isFirstState;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getNextState() {
		return nextState;
	}

	public void setNextState(String nextState) {
		this.nextState = nextState;
	}
}

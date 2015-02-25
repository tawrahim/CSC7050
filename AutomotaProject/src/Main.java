import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * This class is the entry point of our program
 * 
 * @author tawheedraheem
 *
 */
public class Main {

	private static char[] stateValues;
	
	public static void main(String[] args) {
		
		HashMap<String, State> statesMap = new HashMap<>();
		State first_state = null;
		State last_state = null;
        
        
		System.out.println("***** Welcome to the Automota Program *****");

		Scanner sc = new Scanner(System.in);
		
		System.out.print("How many states do you want to create: ");
		int numberOfAutomota = 0;
		
		try {
			numberOfAutomota = sc.nextInt();
		} catch (Exception nfe) {
			System.out.println("The program is quitting because you did not enter a valid integer");
			System.exit(1);
		}
		
		System.out.println("Great! I see that your automota has " + numberOfAutomota + " states");
		
		boolean isLastState = false;
		boolean isFirstState = false;
		String name;
		String value;
		String nextState;
		
		for (int i = 0; i < numberOfAutomota; i++) {
			
			System.out.println("Please enter information about your state #" + (i+1));
			
			try {
				System.out.print("Name: ");
				name = sc.next();
				
				System.out.print("Value: ");
				value = sc.next();
				
				System.out.print("Next State (enter next state name or (-) if does not exist): ");
				nextState = sc.next();
				
				if (first_state == null) {
					System.out.print("Is this the First State? [Y/N]: ");
					if (sc.next().equals("Y")) {
						isFirstState = true;
					}
				} else {
					isFirstState = false;
				}
				
				if (!isFirstState) {
					if (last_state == null) {
						System.out.print("Is this the Last State? [Y/N]: ");
						if (sc.next().equals("Y")) isLastState = true;
					} else {
						isLastState = false;
					}
				}
				
				State s = makeState(name, value, isFirstState, isLastState, nextState);
				
				statesMap.put(name, s);
				
				if (isFirstState) first_state = s;
				if (isLastState) last_state = s;
				
			} catch ( Exception e) {
				System.err.print("Something wrong happened with your data input so we are quiting....");
				System.exit(1);
			}
		}
		
		// close input stream
		//sc.close();
		
		// print generated state diagram to user
		printGeneratedStateDiagram(first_state, last_state, statesMap);
		
		// We ask user to enter a string and we determine if is valid
		System.out.print("Please enter a string you want to find: ");
		char[] userString = null;
		int userStringLength = 0; 
		
		try {
			String str = sc.next();
			userStringLength = str.length();
			userString = str.toCharArray();
		} catch (Exception nfe) {
			System.out.println("The program is quitting because you did not enter a valid integer");
			System.exit(1);
		}
		
		// close input stream
		sc.close();
		
		if (userStringLength < stateValues.length) {
			System.out.println("IVALID STRING.... The State Machine has Halted!");
			return;
		} else if (userStringLength > stateValues.length) {
			System.out.println("IVALID STRING.... The State Machine got stuck!");
			return;
		} else {
			for (int i = 0; i < stateValues.length - 1; i++) {
				if (!(String.valueOf(stateValues[i]).equals(String.valueOf(userString[i])))){
					System.out.println("IVALID STRING.... Your string does not match our states!");
					return;
				}
			}
			System.out.println("VALID STRING... The string you entered is valid and matches our state machine.");
		}

	}
	
	private static void printGeneratedStateDiagram(State first_state,
			State last_state, HashMap<String, State> statesMap) {
		
		stateValues = new char[statesMap.size()];
		
		if (first_state == null || last_state == null) 
			throw new NoSuchElementException("Your state diagram is invalid because it does not have a start and end state.");
		
		prettyPrint(first_state);
		stateValues[0] = first_state.getValue().charAt(0);
		
		String transition = first_state.getNextState();
		
		for (int i = 0; i < statesMap.size() - 1; i++) {
			State s = statesMap.get(transition);
			prettyPrint(s);
			transition = s.getNextState();
			stateValues[i+1] =  s.getValue().charAt(0);
		}		
	}

	private static State makeState(String name, String value, boolean isFirstState, boolean isLastState, String nextState) {
		State s = new State(name, value, isFirstState, isLastState, nextState);
		return s;
	}
	
	private static void prettyPrint(State s) {
		
		 System.out.println("**********************");
		 System.out.println("*   Name: " + s.getName() +"          *");
		 System.out.println("*   Value: " + s.getValue() +"         *");
		 System.out.println("*   Next State: " + s.getNextState() +"    *");
		 System.out.println("**********************"); 
		
		if (!s.isLastState()) {
			 System.out.println("        |             ");
			 System.out.println("        |             ");
			 System.out.println("        |             ");
			 System.out.println("        |             ");			
		}
	}
}

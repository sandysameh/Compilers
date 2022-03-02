package csen1002.main.task1;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write your info here
 * 
 * @name Sandy Sameh
 * @id 43-0234
 * @labNumber 10
 */
public class DFA {
	//ArrayList<Integer> goal;
	//String[] myDFA;
	List<String> statesPosition;
	List<String> zeroToState;
	List<String> OneToState;
	List<String> isGoalList;

	/**
	 * DFA constructor
	 * 
	 * @param description is the string describing a DFA
	 */
	public DFA(String description) {
		String[] divideMyInput =description.split("#");
		//System.out.println(divideMyInput[1]);
		String[] goals =divideMyInput[1].split(",");//"1,3"
		//System.out.println(goals);//["1","3"]
		List<String> goalList = new ArrayList<>(Arrays.asList(goals));
		
		String [] states =divideMyInput[0].split(";");//"0,0,1;1,2,1;2,0,3;3,3,3"
		String [] statePositionArray=new String[states.length];
		String [] ZeroPositions=new String[states.length];
		String [] OnePositions=new String[states.length];
		String [] GoalArray=new String[states.length];

		//System.out.println(states[0]);//

		for(int i=0;i<states.length;i++) {
			boolean isGoal =false;
			System.out.println(goalList.contains(states[i].split(",")[0]));
			if(goalList.contains(states[i].split(",")[0])) {
				isGoal=true;
			}
			GoalArray[i]=isGoal+"";
			statePositionArray[i]=states[i].split(",")[0];
			ZeroPositions[i]=states[i].split(",")[1];
			OnePositions[i]=states[i].split(",")[2];
			

			
		}
		statesPosition=new ArrayList<>(Arrays.asList(statePositionArray));
		zeroToState= new ArrayList<>(Arrays.asList(ZeroPositions));
		OneToState = new ArrayList<>(Arrays.asList(OnePositions));
		isGoalList = new ArrayList<>(Arrays.asList(GoalArray));
		// TODO Write Your Code Here
	}

	/**
	 * Returns true if the string is accepted by the DFA and false otherwise.
	 * 
	 * @param input is the string to check by the DFA.
	 * @return if the string is accepted or not.
	 */
	public boolean run(String input) {
		// TODO Write Your Code Here
		
		String StartPosition="0";
		

		for(int i=0;i<input.length();i++) {
			int move =Integer.parseInt(input.charAt(i)+"");
		
			int index=statesPosition.indexOf(StartPosition);

			if(1==move) {
				StartPosition=OneToState.get(index);
			}else {
				StartPosition=zeroToState.get(index);
			}

		}
		//System.out.print(StartPosition);
		int FinalIndex =statesPosition.indexOf(StartPosition);
		
		
		if(isGoalList.get(FinalIndex).equals("true")) {
			return true;
		}
		else {
		return false;}
	}
//	public static void main(String args[]) {
//		DFA one = new DFA("0,0,1;1,2,1;2,0,3;3,3,3#1,3");
//	}
}

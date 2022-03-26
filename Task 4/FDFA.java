package csen1002.main.task4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Write your info here
 * 
 * @name Sandy Sameh
 * @id 43-1186
 * @labNumber 10
 */
public class FDFA {
	/**
	 * FDFA constructor
	 * 
	 * @param description is the string describing a FDFA
	 */
	List<String> statesPosition;
	List<String> zeroToState;
	List<String> OneToState;
	List<String> isGoalList;
	List<String> Action;
	Stack<String> stack2 ;
	public FDFA(String description) {
		// TODO Write Your Code Here
		
		String[] divideMyInput =description.split("#");
		//System.out.println(divideMyInput[1]);
		String[] goals =divideMyInput[1].split(",");//"1,3"
		//System.out.println(goals);//["1","3"]
		List<String> goalList = new ArrayList<>(Arrays.asList(goals));
		
		String [] states =divideMyInput[0].split(";");//"0,0,1;1,2,1;2,0,3;3,3,3"
		String [] statePositionArray=new String[states.length];
		String [] ZeroPositions=new String[states.length];
		String [] OnePositions=new String[states.length];
		String [] ActionPositions=new String[states.length];

		String [] GoalArray=new String[states.length];

		//System.out.println(states[0]);//

		for(int i=0;i<states.length;i++) {
			boolean isGoal =false;
//			System.out.println(goalList.contains(states[i].split(",")[0]));
			if(goalList.contains(states[i].split(",")[0])) {
				isGoal=true;
			}
			GoalArray[i]=isGoal+"";
			statePositionArray[i]=states[i].split(",")[0];
			ZeroPositions[i]=states[i].split(",")[1];
			OnePositions[i]=states[i].split(",")[2];
			ActionPositions[i]=states[i].split(",")[3];

			

			
		}
		statesPosition=new ArrayList<>(Arrays.asList(statePositionArray));
		zeroToState= new ArrayList<>(Arrays.asList(ZeroPositions));
		OneToState = new ArrayList<>(Arrays.asList(OnePositions));
		Action = new ArrayList<>(Arrays.asList(ActionPositions));
		isGoalList = new ArrayList<>(Arrays.asList(GoalArray));
		
		System.out.println(statesPosition.toString());
		System.out.println(zeroToState.toString());
		System.out.println(OneToState.toString());
		System.out.println(Action.toString());
		System.out.println(isGoalList.toString());

	
	}
	

	/**
	 * Returns a string of actions.
	 * 
	 * @param input is the string to simulate by the FDFA.
	 * @return string of actions.
	 */
	
	public void pushalltoStack(String input,String StartPosition) {
		
		stack2.push(StartPosition);
		for(int i=0;i<input.length();i++) {
			int move =Integer.parseInt(input.charAt(i)+"");
		
			int index=statesPosition.indexOf(StartPosition);

			if(1==move) {
				StartPosition=OneToState.get(index);
			}else {
				StartPosition=zeroToState.get(index);
			}
			stack2.push(StartPosition);


	}}
	public String run(String input) {
		//String 
		
		String StartPosition="0";
		//String qr = "";
		String result="";
		String stateBefore ="";
        stack2 = new Stack<String>();
        pushalltoStack(input, StartPosition);
        System.out.println(stack2.toString());
        // ALl the string is accepted 
        String qr=stack2.peek();
        System.out.println("My last Elemt " +qr);
        System.out.println(statesPosition.indexOf(qr));
        int L =input.length();
        
        int r=0;
        while(L>r) {
        	int indexx=statesPosition.indexOf(stack2.peek());
        	System.out.println(indexx);
        	if(isGoalList.get(indexx).equals("true")) {
        		
        		result+=input.substring(r, L)+","+Action.get(indexx)+";";
        		System.out.println(result);
        		if(L==input.length()) {
        			
        			return result;
        		}
        		else {
        		if(L>r) {
        			stack2.empty();
        			r=L;
        			L=input.length();
        			System.out.println("stateeeeee "+stateBefore);
        			pushalltoStack(input.substring(r,L), StartPosition);
        			qr=stack2.peek();
        			
        		}
        		else {
        			int ind=statesPosition.indexOf(qr);
        			result+=input.substring(r,input.length())+","+Action.get(ind)+";";
        			return result;
        		}
        		
        	}}
        	else {
        		L--;
        		stateBefore=stack2.pop();
        	
        }
        }
        
        System.out.println("state rejected "+qr);
        int ind=statesPosition.indexOf(qr);
		result+=input.substring(r,input.length())+","+Action.get(ind)+";";        
        //return result;
        
		return result;
	}
	//01011000"),"01011,A;000,O;"
	public static void main(String args[]) {
	FDFA one = new FDFA("0,1,0,N;1,1,2,O;2,3,1,P;3,3,4,Q;4,3,4,A#4");
	one.run("01011000");
}
}


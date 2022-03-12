package csen1002.main.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Write your info here
 * 
 * @name Sandy Sameh
 * @id 43-1186
 * @labNumber 10
 */
public class NFA{
	List<Integer> statesPosition;
	 HashMap<Integer, HashSet<Integer>> MyEpsilons;
	 	String [] MyDFAstateZero;
		String [] MyDFAstateOne;
		List<String> goalList;

	/**
	 * NFA constructor
	 * 
	 * @param description is the string describing a NFA
	 */
	public NFA(String description) {
		// TODO Write Your Code Here
		String[] divideMyInput =description.split("#");
		String[] goals =divideMyInput[3].split(",");//"1,3"
	
		String [] Zeros =divideMyInput[0].split(";");
		String [] Ones =divideMyInput[1].split(";");
		String [] Epsilon=divideMyInput[2].split(";");
		goalList = new ArrayList<>(Arrays.asList(goals));

		
		statesPosition=new ArrayList<>();
		getmyStatesHelper(Zeros,Ones,Epsilon);
		
		MyDFAstateOne=new String[statesPosition.size()];
		MyDFAstateZero=new String[statesPosition.size()];

		
		getmyEpsiolonHelper(Epsilon);	
		
		setMySingleOneMove(Ones);
		setMySingleZeroMove(Zeros);

		
	
		
	
		
		
	}

	/**
	 * Returns true if the string is accepted by the NFA and false otherwise.
	 * 
	 * @param input is the string to check by the NFA.
	 * @return if the string is accepted or not.
	 */
	
	public void getmyStatesHelper(String[] Zeros,String[] Ones, String[] Epsilon) {
		
		
		List<Integer> list = new ArrayList<>();
		for (int i=0;i<Zeros.length;i++) {
			list.add(Integer.parseInt((Zeros[i].split(","))[0]));
			list.add(Integer.parseInt((Zeros[i].split(","))[1]));

		}
		for (int i=0;i<Ones.length;i++) {
			list.add(Integer.parseInt((Ones[i].split(","))[0]));
			list.add(Integer.parseInt((Ones[i].split(","))[1]));

		}
		for (int i=0;i<Epsilon.length;i++) {
			list.add(Integer.parseInt((Epsilon[i].split(","))[0]));
			list.add(Integer.parseInt((Epsilon[i].split(","))[1]));

		}
		//Keda i Havee all the States.
		
		Collections.sort(list);
		Set<Integer> unique = new LinkedHashSet<>(list); // 4 5 6
		Object[] uniquearray =unique.toArray();
		for(int i=0;i<uniquearray.length;i++) {
			statesPosition.add((int)uniquearray[i]);
			//System.out.println(statesPosition.get(i));
			//	System.out.println(statesPosition.get((i));
		}
		

		
	}
	
	public void getmyEpsiolonHelper(String[] Epsilon) {
		boolean stop =false;
		boolean firsttime =true;
        MyEpsilons = new HashMap<Integer, HashSet<Integer>>();

        String []EpsilonAction=new String[statesPosition.size()];
        
        for(int i=0;i<Epsilon.length;i++) {
        	
        	if(EpsilonAction[statesPosition.get(Integer.parseInt(Epsilon[i].split(",")[0]))]==null) {
        	EpsilonAction[statesPosition.get(Integer.parseInt(Epsilon[i].split(",")[0]))]=Epsilon[i].split(",")[1]+",";
       
        }
        	else {
            	EpsilonAction[statesPosition.get(Integer.parseInt(Epsilon[i].split(",")[0]))]+=Epsilon[i].split(",")[1]+",";

        	}
        	}

        
        
		while(!stop) {
			if(firsttime) {
				for(int i=0;i<statesPosition.size();i++) {
				HashSet<Integer> statesEpsilon = new HashSet<>();
				statesEpsilon.add(i);
				if(EpsilonAction[i]!=null) {
					for(int k=0;k<EpsilonAction[i].split(",").length;k++) {
						statesEpsilon.add(Integer.parseInt(EpsilonAction[i].split(",")[k]));
					}	
				}
				MyEpsilons.put(i, statesEpsilon);
			//	System.out.println("i "+ i +statesEpsilon.toString());
				}
				firsttime=false;
			}
			else {
				int count =0 ;
				for(int i=0;i<statesPosition.size();i++) {
					//HashSet<Integer> updatestatesEpsilon = new HashSet<>();

					int intialSize = MyEpsilons.get(i).size();
					Object [] epsylonStates =MyEpsilons.get(i).toArray();
					for(int j=0;j<epsylonStates.length;j++) {
						Object [] addedStates=MyEpsilons.get((int)epsylonStates[j]).toArray();
						
						 Integer[] integerArray = Arrays.asList(addedStates).toArray(new Integer[0]);
						List<Integer> addedStatesList= new ArrayList<>(Arrays.asList(integerArray));

					MyEpsilons.get(i).addAll(addedStatesList);
					
					}
					int updatedCount=MyEpsilons.get(i).size();
					if(updatedCount!=intialSize) {
						count++;
					}
					
					
				}
				if(count==0) {
					stop=true;
				}
			}
			
			
			
			
		}

	}
	
	public void setMySingleOneMove(String [] Ones) {
		for(int i=0;i<Ones.length;i++) {
			MyDFAstateOne[Integer.parseInt(Ones[i].split(",")[0])]=Ones[i].split(",")[1];
		}
		
	}
	
	public void setMySingleZeroMove(String [] Zeros) {
		for(int i=0;i<Zeros.length;i++) {
			MyDFAstateZero[Integer.parseInt(Zeros[i].split(",")[0])]=Zeros[i].split(",")[1];
		}
		
	}
	
	public HashSet<Integer> GetMyEpsilonOneMove(Object [] startStateObject){
		HashSet<Integer> WillReturnThis =new HashSet<>();
		
		
		for(int i=0;i<startStateObject.length;i++) {
			int index= (int)startStateObject[i];
			if(MyDFAstateOne[index]!=null) {
				WillReturnThis.addAll(MyEpsilons.get(Integer.parseInt(MyDFAstateOne[index])));
			}
		}
		return WillReturnThis;
	}

	public HashSet<Integer> GetMyEpsilonZeroMove(Object [] startStateObject){
		HashSet<Integer> WillReturnThis =new HashSet<>();
		
		
		for(int i=0;i<startStateObject.length;i++) {
			int index= (int)startStateObject[i];
			if(MyDFAstateZero[index]!=null) {
				WillReturnThis.addAll(MyEpsilons.get(Integer.parseInt(MyDFAstateZero[index])));
			}
		}
		return WillReturnThis;
	}
	
	public boolean run(String input) {
		HashSet<Integer> StartState = new HashSet<>();
		StartState=MyEpsilons.get(0);
		
		for(int i=0;i<input.length();i++) {
			//HashSet<Integer> currentState ;
			Object [] startStateObject = StartState.toArray();

			int move =Integer.parseInt(input.charAt(i)+"");
			if(1==move) {
				StartState=GetMyEpsilonOneMove(startStateObject);
				if(StartState.size()==0) {
					return false;
				}
			}else {
				StartState=GetMyEpsilonZeroMove(startStateObject);
				if(StartState.size()==0) {
					return false;
				}
			}
			
		}
		
		for(int j=0;j<goalList.size();j++) {
			int myIndexGoal=Integer.parseInt(goalList.get(j));
			if(StartState.contains(myIndexGoal)){
				return true;
				
			}
		}
		return false;
		


	}
	
//	public static void main(String args[]) {
//
//		
//	}
}
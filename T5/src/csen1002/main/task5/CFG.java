package csen1002.main.task5;

import java.util.ArrayList;

/**
 * Write your info here
 * 
 * @name Sandy Sameh
 * @id 43-1186
 * @labNumber 10
 */
public class CFG {
	/**
	 * CFG constructor
	 * 
	 * @param description is the string describing a CFG
	 */
	String[] to_update;
	String[] letters;

	int globel_i;
	String result="";
	public CFG(String description) {
		String[] divideMyInput =description.split(";");
		globel_i=divideMyInput.length;
		to_update =new String[globel_i];
		letters =new String[globel_i];

		for(int i=0;i<globel_i;i++) {
			letters[i]=divideMyInput[i].split(",")[0];
			to_update[i]=divideMyInput[i];
		}
		
		for(int i=0;i<globel_i;i++) {
			for(int j=0;j<i;j++) {

				Subsitue(i,to_update[i],letters[i],letters[j],j);
			}
			lreHelper(i,to_update[i],letters[i]);
		}
		// TODO Write Your Code Here
	}

	/**
	 * Returns a string of elimnated left recursion.
	 * 
	 * @param input is the string to simulate by the CFG.
	 * @return string of elimnated left recursion.
	 */
	
	public void Subsitue(int myPosition,String Myword,String MyLetter,String LettertoReplace, int ReplaceLetterPos) {
		String [] splitMyword = Myword.split(",");
		ArrayList<String> toAdd = new ArrayList<String>();
		System.out.println(MyLetter+" My letter");
		for(int i=1;i<splitMyword.length;i++) {
			System.out.println("to replace + "+LettertoReplace+ " compare "+splitMyword[i].charAt(0)+"");

			if(LettertoReplace.equals(splitMyword[i].charAt(0)+"")) {
				System.out.println("Hna");
				String[] replaceWith =to_update[ReplaceLetterPos].split(",");
				
				for(int j=1;j<replaceWith.length;j++) {
					System.out.println(j);
					toAdd.add(splitMyword[i].replaceFirst(LettertoReplace, replaceWith[j]));
				}
			}
			else {
			toAdd.add(splitMyword[i]);}
		}
		String NewResult=""+MyLetter+",";
		for(int k=0;k<toAdd.size();k++) {
			if(k==toAdd.size()-1) {
			NewResult+=toAdd.get(k);}
			else {
			NewResult+=toAdd.get(k)+",";
			}
		}
		System.out.println(NewResult);
		to_update[myPosition]=NewResult;
		
		// TODO Write Your Code Here
	}
	
	public void lreHelper(int myPosition,String Myword,String MyLetter) {
		String [] splitMyword = Myword.split(",");
		String Normal=""+MyLetter+",";
		String Dash ="";
		ArrayList<String> Alphas = new ArrayList<String>();
		ArrayList<String> Betas = new ArrayList<String>();

		for(int i=1;i<splitMyword.length;i++) {
			if(MyLetter.equals(splitMyword[i].charAt(0)+"")) {
				System.out.println("My aplhas "+splitMyword[i].substring(1,splitMyword[i].length()));
				Alphas.add(splitMyword[i].substring(1,splitMyword[i].length()));
			}
			else {
				Betas.add(splitMyword[i]);
			}
			}
		if(Alphas.size()!=0) {
			Dash+=MyLetter+"',";
			//change 
			for(int k=0;k<Betas.size();k++) {
				if(k==Betas.size()-1) {
					
						Normal+=Betas.get(k)+MyLetter+"'";
					
				}
				else {
					Normal+=Betas.get(k)+MyLetter+"'"+",";
				}
				
			}
			System.out.println(Dash);
			for(int k=0;k<Alphas.size();k++) {
				if(k==Alphas.size()-1) {
					
					Dash+=Alphas.get(k)+MyLetter+"',e";
					
					
				}
				else {
					Dash+=Alphas.get(k)+MyLetter+"'"+",";
				}
			}
			System.out.println(Normal);

			to_update[myPosition]=Normal;
			
			if(myPosition+1!=globel_i) {
				result+=Normal+";"+Dash+';';
			}
			else {
				result+=Normal+";"+Dash;

			}
		}
		else {
			if(myPosition+1!=globel_i) {
				result+=to_update[myPosition]+';';
			}
			else {
				result+=to_update[myPosition];

			}
		}
		
		
		// TODO Write Your Code Here
	}
	public String lre() {
		// TODO Write Your Code Here
		return result;
		
	}
	public static void main(String[] args) {
		CFG cfg = new CFG("S,ScT,Sm,T,n;T,mSn,imLn,i;L,SdL,S");
		
	}
}

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class generateArrays {
	
	public static ArrayList<Integer> SuffixArray (String word){
		
		ArrayList<Integer> suffixArray = new ArrayList<Integer>();
		ArrayList<String> arrayOfUnsortedSuffixes = new ArrayList<String>();
		
		for(int i=0;i<word.length();i++) {
			arrayOfUnsortedSuffixes.add(word.substring(i));
		}
		arrayOfUnsortedSuffixes.add("");

		System.out.println(arrayOfUnsortedSuffixes);
		Collections.sort(arrayOfUnsortedSuffixes);
		
		  /*for(int i=0;i<arrayOfUnsortedSuffixes.size();i++) { 
			  for(int j=0;j<word.length();j++) {
			  	String toCompare = word.substring(j);
			  	if(arrayOfUnsortedSuffixes.get(i).equals(toCompare)) { 
			  		suffixArray.add(j); 
				  }
			  }
		  }*/
		System.out.println(arrayOfUnsortedSuffixes);
		for(int i=0; i<arrayOfUnsortedSuffixes.size();i++) {
			int lengthOfSuffix = arrayOfUnsortedSuffixes.get(i).length();
			int wordLength = word.length();
			int position = wordLength - lengthOfSuffix;
			suffixArray.add(position); 
			
		}
		
		
		return suffixArray;
		
	}
	
	public static int lookUpLCP(ArrayList<Integer> LCPArray, ArrayList<Integer> inverseSuffixArray, int one, int two) {
		int SAOne;
		int SATwo;
		int returnLCP = 0;
		one=inverseSuffixArray.get(one);
		two=inverseSuffixArray.get(two);
		if(one>two) {
			SAOne=two;
			SATwo=one;
		}else {
			SAOne=one;
			SATwo=two;
		}
		System.out.println("SAOne: "+(SAOne+1));
		System.out.println("SATwo: "+SATwo);
		if(SAOne<SATwo) {
			System.out.println("Ran");
			returnLCP=rangeMinimumQuery(LCPArray, SAOne+1, SATwo);
		}
		if(returnLCP!=0) {
			return LCPArray.get(returnLCP);
		}
		else {
			return 0;
		}
	}
	
	public static ArrayList<Integer> LCP(ArrayList<Integer> suffixArray, String word) {

		ArrayList<Integer> LCPArray = new ArrayList<Integer>();
		LCPArray.add(null);
		
		int maxWordPos = word.length()-1;
		
		for(int i=1; i<suffixArray.size(); i++) {
			int lcpValue = 0;
			int firstPos = suffixArray.get(i);
			int secondPos = suffixArray.get(i-1);
			if(firstPos>maxWordPos || secondPos>maxWordPos) {
				lcpValue=0;
			} //else if (){
				
			//}
		}
		
		
		return LCPArray;
	}
	
	public static ArrayList<Integer> generateLCPArray(ArrayList<Integer> suffixArray, String word) {
		
		ArrayList<Integer> LCPArray = new ArrayList<Integer>();
		LCPArray.add(null);
		
		int lastSuffixInt = suffixArray.get(0);
		
		for(int i=1; i<suffixArray.size(); i++) {
			String substrOne = word.substring(lastSuffixInt);
			String substrTwo = word.substring(suffixArray.get(i));
			
			int LCP = LCParray(substrOne, substrTwo);
			LCPArray.add(LCP);
			
			lastSuffixInt = suffixArray.get(i);
		}
		
		
		return LCPArray;
	
	}
	
public static int LCParray(String suffOne, String suffTwo) {
		
		String lcp = "";
		int suffOneLength = suffOne.length();
		int suffTwoLength = suffTwo.length();
		int shortestLength = 0;
	
		if(suffOneLength > suffTwoLength) {
			shortestLength = suffTwoLength;
		}else {
			shortestLength = suffOneLength;
		}
		
		for(int i = 0; i < shortestLength; i++) {
			if(suffOne.charAt(i) == suffTwo.charAt(i)) {
				lcp = lcp + suffOne.charAt(i);
			}
			else {
				i=shortestLength;
			}
		}
		
		return lcp.length();
	}
	
public static int rangeMinimumQuery (ArrayList<Integer> array, int firstPos, int lastPos) {
	
	int lowest=array.get(firstPos);
	int lowestPos = firstPos;
	for(int i = firstPos; i<lastPos+1; i++) {
		int current = array.get(i);
		if (current<lowest) {
			lowest = current;
			lowestPos = i;
		}
	}
		
	return lowestPos;
}

public static ArrayList<Integer> generateInverseSuffixArray (ArrayList<Integer> suffixArray){
	ArrayList<Integer> inverse = new ArrayList<Integer>();
	
	int count=0;
	while(count!=suffixArray.size()) {
		
		for(int i=0; i<suffixArray.size(); i++) {
			
			if(count == suffixArray.get(i)) {
				inverse.add(i);
			}
		}
		count++;
	}
	return inverse;
}


}

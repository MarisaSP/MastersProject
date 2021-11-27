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
		suffixArray.add(word.length());
		for(int i=0;i<arrayOfUnsortedSuffixes.size();i++) {
			for(int j=0; j<word.length();j++) {
				String toCompare = word.substring(j);
				if(arrayOfUnsortedSuffixes.get(i).equals(toCompare)) {
					suffixArray.add(j);
				}
			}
		}
		
		
		return suffixArray;
		
	}
	
	
	public static ArrayList<Integer> generateLCPArray(ArrayList<Integer> suffixArray, String word) {
		
		ArrayList<Integer> LCPArray = new ArrayList<Integer>();
		LCPArray.add(null);
		
		int lastSuffixInt = suffixArray.get(0);
		
		for(int i=1; i<suffixArray.size(); i++) {
			String substrOne = word.substring(lastSuffixInt);
			String substrTwo = word.substring(suffixArray.get(i));
			
			int LCP = LCPArray(substrOne, substrTwo);
			LCPArray.add(LCP);
			
			lastSuffixInt = suffixArray.get(i);
		}
		
		
		return LCPArray;
	
	}
	
public static int LCPArray(String suffOne, String suffTwo) {
		
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


}

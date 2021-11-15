
public class generateArrays {
	
	public static String LCPArray(String suffOne, String suffTwo) {
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
		
		return lcp;
	}

}

import java.util.List;
import java.util.ArrayList;

public class TableInstanceClass {
	
	String strStored;
	ArrayList<ArrayList<int []>> allRecords;

	ArrayList<Integer> suffixArray;
	ArrayList<Integer> lcpArray;
	ArrayList<Integer> inverseSuffixArray;
	
	
	
	public void setup(String thisWord, ArrayList<ArrayList<int []>> records) {
		strStored = thisWord;
		allRecords = records;
	}
	
	public static ArrayList<ArrayList<int []>> makeTbl(int i, int j, int cols, int wordLength) {
		ArrayList<ArrayList<int []>> allRecords = new ArrayList<ArrayList<int []>>();
		ArrayList<int []> singleRecord = new ArrayList<int []>();
		int entry[]=new int[2];
		
		//If string is empty, each column is set to the empty word
		if(i==j) {
			entry[0]=i;
			entry[1]=i;
			for(int m=0; m<cols; m++) {
				singleRecord.add(consolidateEntry(entry));
			}
		allRecords.add(consolidateSingleRecords(singleRecord));
		
		//If only one column, the column contains the whole string
		}else if (cols==1){
			entry[0]=i;
			entry[1]=j;
			singleRecord.add(consolidateEntry(entry));
			allRecords.add(consolidateSingleRecords(singleRecord));
			
			
		}else {
			entry[0]=i;
			entry[1]=j;
			singleRecord.add(consolidateEntry(entry));
			for(int x=1; x<cols;x++) {
				entry[0]=j;
				entry[1]=j;
				singleRecord.add(consolidateEntry(entry));
			}
			allRecords.add(consolidateSingleRecords(singleRecord));
			singleRecord.clear();
			
			for(int m=1; m<=(j-i); m++) {
				ArrayList<ArrayList <int[]>> theseRecords = makeTbl(j-m, j, cols-1, wordLength);
				for(ArrayList <int []> b : theseRecords) {
					entry[0]=i;
					entry[1]=j-m;
					singleRecord.add(consolidateEntry(entry));
					for( int [] c : b) {
						singleRecord.add(consolidateEntry(c));
						
					}
					allRecords.add(consolidateSingleRecords(singleRecord));
					singleRecord.clear();
				}
			}
		}
		
		
		
		
		return allRecords;
	}
	
	public static int[] consolidateEntry(int[] entryIn) {
		int entryOut[] = new int[2];
		entryOut[0] = entryIn[0];
		entryOut[1] = entryIn[1];
		return entryOut;
	}
	
	public static ArrayList<int []> consolidateSingleRecords(ArrayList<int []> singleRecIn) {
		ArrayList singleRecOut = new ArrayList<int []>();
		for( int [] c : singleRecIn) {
			singleRecOut.add(consolidateEntry(c));
		}
		return singleRecOut;
	}
	
	public void formArrays() {
		suffixArray = generateArrays.SuffixArray(strStored);
		lcpArray = generateArrays.generateLCPArray(suffixArray, strStored);
		inverseSuffixArray = generateArrays.generateInverseSuffixArray(suffixArray);
	}
	
	public ArrayList<Integer> getSuffixArray() {
		return suffixArray;
	}
	
	public ArrayList<Integer> getLCPArray() {
		return lcpArray;
	}
	
	public ArrayList<Integer> getInverseSuffixArray(){
		return inverseSuffixArray;
	}
}




















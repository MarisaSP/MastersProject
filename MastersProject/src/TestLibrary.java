import java.util.ArrayList;
import java.util.Collections;

public class TestLibrary {
	
	String STOREDWORD;
	int STOREDWORDLENGTH;
	ArrayList<ArrayList<int []>> TABLE;

	ArrayList<Integer> SUFFIXARRAY;
	ArrayList<Integer> LCPARRAY;
	ArrayList<Integer> INVERSESUFFIXARRAY;
	ArrayList<int []> SUBWORDLIST;
	

	public void setup(String thisWord, int cols) {
		STOREDWORD=thisWord;
		STOREDWORDLENGTH = thisWord.length();
		
		SUFFIXARRAY = SuffixArray();
		LCPARRAY = generateLCPArray();
		INVERSESUFFIXARRAY = generateInverseSuffixArray();
		
		TABLE=makeTbl(0, STOREDWORDLENGTH, cols, STOREDWORDLENGTH);
		
		
	}
		
	private ArrayList<ArrayList<int []>> makeTbl(int i, int j, int cols, int wordLength) {
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
		
		
		private int[] consolidateEntry(int[] entryIn) {
			int entryOut[] = new int[2];
			entryOut[0] = entryIn[0];
			entryOut[1] = entryIn[1];
			return entryOut;
		}
		
		private ArrayList<int []> consolidateSingleRecords(ArrayList<int []> singleRecIn) {
			ArrayList singleRecOut = new ArrayList<int []>();
			for( int [] c : singleRecIn) {
				singleRecOut.add(consolidateEntry(c));
			}
			return singleRecOut;
		}
		
		private ArrayList<Integer> SuffixArray (){
			
			String word = STOREDWORD;
			ArrayList<Integer> suffixArray = new ArrayList<Integer>();
			ArrayList<String> arrayOfUnsortedSuffixes = new ArrayList<String>();
			
			for(int i=0;i<word.length();i++) {
				arrayOfUnsortedSuffixes.add(word.substring(i));
			}
			arrayOfUnsortedSuffixes.add("");

			Collections.sort(arrayOfUnsortedSuffixes);
			for(int i=0; i<arrayOfUnsortedSuffixes.size();i++) {
				int lengthOfSuffix = arrayOfUnsortedSuffixes.get(i).length();
				int wordLength = word.length();
				int position = wordLength - lengthOfSuffix;
				suffixArray.add(position); 
				
			}
			
			return suffixArray;
			
		}
		
		private ArrayList<Integer> generateLCPArray() {
			String word = STOREDWORD;
			ArrayList<Integer>  suffixArray = SUFFIXARRAY;
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
		
		private int LCParray(String suffOne, String suffTwo) {
			
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
		
		private ArrayList<Integer> generateInverseSuffixArray (){
			ArrayList<Integer> suffixArray = SUFFIXARRAY;
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
		
		public int noOfSubwordsSetLength(int lengthOfSubword){
			int subBound = (STOREDWORD.length()+1)-lengthOfSubword;
			
			for(int i=1; i<LCPARRAY.size(); i++) {
				if(LCPARRAY.get(i) >= lengthOfSubword) {
					subBound = subBound - 1;
				}
			}
			
			return subBound;
		}
		
		public void eliminateRepititionsInTbl() {
			ArrayList<Integer> inverseSuffixArray = INVERSESUFFIXARRAY;
			ArrayList<Integer> lcpArray = LCPARRAY;
			ArrayList<ArrayList<int []>> tableToOptimise = TABLE;
			ArrayList<int []> originals = new ArrayList<int []>();
			ArrayList<ArrayList<int []>> optimisedTable = new ArrayList<ArrayList<int []>>();
			int[] indexes = new int[2];
			ArrayList<int []> freshRecords = new ArrayList<int []>();
			for (ArrayList<int []> eachRecord : tableToOptimise) {
				for ( int[] b : eachRecord) {
					indexes[0]=b[0];
					indexes[1]=b[1];
					boolean found = false;
					for(int[] OG : originals) {
						if(OG[0]==b[0] && OG[1]==b[1]) {
							found=true;
						}
					}
					if(!found) {
						int LCP;
						for(int[] OG : originals) {
							if(OG[0]==OG[1] || b[0]==b[1]) {
								LCP = 0;
							}else {
								LCP = lookUpLCP(lcpArray, inverseSuffixArray, Integer.valueOf(b[0]), Integer.valueOf(OG[0]));
							}
							if( (b[1]-b[0]) == (OG[1]-OG[0]) ) {
								if( ((LCP >= (b[1]-b[0])) && (LCP!=0)) || (( (b[1]-b[0])==0) && (OG[1]-OG[0]==0)) ) {
									found = true;
									indexes[0]=OG[0];
									indexes[1]=OG[1];
								}
							}
						}
						
						if(!found) {
							originals.add(b);
						}
					}
					
					
					freshRecords.add(consolidateEntry(indexes));
					
				}
				optimisedTable.add(consolidateSingleRecords(freshRecords));
				freshRecords.clear();
			
			}
			TABLE = optimisedTable;
		}
		
		private int lookUpLCP(ArrayList<Integer> LCPArray, ArrayList<Integer> inverseSuffixArray, int one, int two) {
			int SAOne;
			int SATwo;
			int returnLCP = 0;
			if( (one>=inverseSuffixArray.size()) || (two>=inverseSuffixArray.size()) )
			{
				return 0;
			}else {
				one=inverseSuffixArray.get(one);
				two=inverseSuffixArray.get(two);
				if(one>two) {
					SAOne=two;
					SATwo=one;
				}else {
					SAOne=one;
					SATwo=two;
				}
				if(SAOne<SATwo) {
					returnLCP=rangeMinimumQuery(LCPArray, SAOne+1, SATwo);
				}
				if(returnLCP!=0) {
					return LCPArray.get(returnLCP);
				}
				else {
					return 0;
				}
			}
		}
		
		private int rangeMinimumQuery (ArrayList<Integer> array, int firstPos, int lastPos) {
			
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
		
		
		public ArrayList<int []> enumerateAllSubwords() {
			ArrayList<int []> subwordList = new ArrayList<int[]>();
			int [] subword = new int[2];
			int count = 0;
			subword[0]=STOREDWORDLENGTH;
			subword[1]=STOREDWORDLENGTH;
			subwordList.add(consolidateEntry(subword));
			
			while(count!=STOREDWORDLENGTH) {
				int SAval = SUFFIXARRAY.get(count);
				if(LCPARRAY.get(count+1) == 0) {
					int countdown = STOREDWORDLENGTH;
					while(SAval!=countdown) {
						subword[0]=SAval;
						subword[1]=countdown;
						subwordList.add(consolidateEntry(subword));
						countdown=countdown-1;
					}
				} else {
					int lcp = LCPARRAY.get(count+1);
					int cd = STOREDWORDLENGTH;
					while((SAval+lcp) < cd) {
						subword[0]=SAval;
						subword[1]=cd;
						subwordList.add(consolidateEntry(subword));
						cd=cd-1;
					}
				}
				
				count++;
			}
			int SAval = SUFFIXARRAY.get(count);
			int countdown = STOREDWORDLENGTH;
			while(SAval!=countdown) {
				subword[0]=SAval;
				subword[1]=countdown;
				subwordList.add(consolidateEntry(subword));
				countdown=countdown-1;
			}
			
			
			return subwordList;
		}
		
		public void stringEqualityTesting() {
			
		}
		
		public void deDuplicate(ArrayList<ArrayList<int []>> tbl) {
			//ArrayList<ArrayList<int []>> oldTable = TABLE;
			//ArrayList<ArrayList<int []>> newTable = new ArrayList<>();
			ArrayList<ArrayList<int []>> oldTable = tbl;
			ArrayList<ArrayList<int []>> newTable = new ArrayList<>();
			
			ArrayList<int []> newrecord = new ArrayList<>();
			int [] newfield = new int[2];
			
			for(ArrayList <int []> oldRecord : oldTable) {
				boolean found = false;
				for(ArrayList <int []> newRec : newTable) {
					int count = 0;
					for(int i=0;i<oldRecord.size();i++) {
						int[] old = oldRecord.get(i);
						int[] newer = newRec.get(i);
						if((old[0]==newer[0]) && (old[1]==newer[1])) {
							count++;
						}
					}
					if(count==oldRecord.size()) {
						found=true;
					}
				}
				if(found==false) {
					newTable.add(consolidateSingleRecords(oldRecord));
				}
			
			}
			TABLE=newTable;
		}

		public ArrayList<Integer> getSuffixArray(){
			return SUFFIXARRAY;
		}

		public ArrayList<Integer> getLCPArray(){
			return LCPARRAY;
		}
		
		public ArrayList<Integer> getInverseSuffixArray(){
			return INVERSESUFFIXARRAY;
		}

		public ArrayList<ArrayList<int []>> getTable(){
			return TABLE;
		}
		public void setTable(ArrayList<ArrayList<int []>> tbl){
			TABLE=tbl;
		}


}

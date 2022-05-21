import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestLibrary {
	
	String STOREDWORD;
	int STOREDWORDLENGTH;
	ArrayList<ArrayList<int []>> TABLE;
	ArrayList<String> COLUMNS;

	ArrayList<Integer> SUFFIXARRAY;
	ArrayList<Integer> LCPARRAY;
	ArrayList<Integer> INVERSESUFFIXARRAY;
	ArrayList<int []> SUBWORDLIST;
	
	//Handles string alone
	public void inputStr(String inputString) {
		STOREDWORD = inputString;
		STOREDWORDLENGTH = inputString.length();
		SUFFIXARRAY = SuffixArray();
		INVERSESUFFIXARRAY = generateInverseSuffixArray();
		LCPARRAY = generateLCPArray();
	}
	
	//Initializer
	public void setup(String thisWord, String eqn) {
		inputStr(thisWord);
		try {
			handleEquation(eqn);
		}
		catch(Exception e){
			System.out.println("Please input a valid equation");
		}
		
		
		
	}
	
	
	//Handles the query, called by setup function
	private void handleEquation(String equation) {
		ArrayList<ArrayList<ArrayList<int []>>> listOfTables = new ArrayList<>();
		ArrayList<ArrayList<String>> listOfCols = new ArrayList<>();
		ArrayList<String> regConstr = new ArrayList<>();
		ArrayList<String> colBuilder = new ArrayList<>();
		ArrayList<String> tblBuilder = new ArrayList<>();
		String[] minusStart = equation.split(":=");
		String maineqn = minusStart[1];
		String[] splitTwo = maineqn.split("<-");
		String colsToProject = splitTwo[0];
			String conditions = splitTwo[1];
			conditions=conditions.substring(1, conditions.length()-1);
			String[] listOfConditions = conditions.split("\\)\\(");
			for(int i=0; i<listOfConditions.length; i++) {
				if(listOfConditions[i].contains(":")) {
					regConstr.add(listOfConditions[i]);
				}
				else if(listOfConditions[i].contains("S")) {
					tblBuilder.add(listOfConditions[i]);
				}
				else if(listOfConditions[i].contains("=")) {
					colBuilder.add(listOfConditions[i]);
				}
				
			}
			for(String s : tblBuilder) {
				ArrayList<String> columns = new ArrayList<>();
				String [] totalColsSplit = s.split("=");
				String totalCols = totalColsSplit[1];
				for(int i=0; i<totalCols.length();i++) {
					columns.add(totalCols.substring(i, i+1));
				}
				ArrayList<ArrayList<int []>> myTbl = new ArrayList<>();
				myTbl = makeTbl(0, STOREDWORDLENGTH, totalCols.length(), columns, regConstr);
				
				
				
				myTbl=eliminateRepititionsInTbl(myTbl);
				
				myTbl=duplicateCols(myTbl, columns);
				
				ArrayList<Integer> freshCols = new ArrayList<>();
				for(int count=0; count<columns.size();count++) {
					boolean found = false;
					for(int a=0; a<freshCols.size(); a++) {
						if(columns.get(freshCols.get(a)).equals(columns.get(count))){
							found=true;
						}
					}
					if(!found) {
						freshCols.add(count);
					}
				}
				
				myTbl = removeDuplicColsFromTbl(myTbl, freshCols);
				ArrayList<String> cols = new ArrayList<>();
				for(int a : freshCols) {
					cols.add(columns.get(a));
				}
			
				
				listOfTables.add(consolidateTables(myTbl));
				listOfCols.add(columns);

			}
			
			ArrayList<ArrayList<int []>> joinTable = listOfTables.get(0);
			ArrayList<String> joinCols = listOfCols.get(0);
			for(int a=1; a<listOfTables.size(); a++) {
				ArrayList<ArrayList<int []>> table = listOfTables.get(a);
				ArrayList<String> col = listOfCols.get(a);
				joinTable = join(joinTable, joinCols, table, col);
				joinCols = colJoin(joinCols, listOfCols.get(a));
			}
			
			
			System.out.println("Join Tables Result");
			System.out.println(joinCols);
			for(ArrayList <int []> b : joinTable) {
				for( int [] c : b) {
					System.out.print("["+c[0]+","+c[1] + ")"+ " ");
				}
				System.out.println(" ");
			}
			ArrayList<ArrayList<int[]>> myTbl = colConstraints(joinTable, joinCols, colBuilder);
			System.out.println("Apply Column Constraints (Final Table Returned)");
			System.out.println(joinCols);
			for(ArrayList <int []> b : myTbl) {
				int count = 0;
				for( int [] c : b) {
					System.out.print("["+c[0]+","+c[1] + ")"+ " ");
					count++;
				}
				System.out.println(" ");
			}
			
		colsToProject=colsToProject.substring(4);
		colsToProject=colsToProject.substring(0,colsToProject.length()-1);
		String[] spl = colsToProject.split(",");
		ArrayList<String> projCols = new ArrayList<>();
		for(String p : spl) {
			projCols.add(p);
		}
		System.out.println(projCols);
		ArrayList<ArrayList<int[]>> finalTbl = projection(myTbl, joinCols, projCols);
		finalTbl = deDuplicate(finalTbl);
		System.out.println("And finally, Project!");
		System.out.println(projCols);
		for(ArrayList <int []> b : finalTbl) {
			int count = 0;
			for( int [] c : b) {
				System.out.print("["+c[0]+","+c[1] + ")"+ " ");
				count++;
			}
			System.out.println(" ");
		}
	}
	
	private ArrayList<ArrayList<int []>> colConstraints(ArrayList<ArrayList<int []>> table, ArrayList<String> colms, ArrayList<String> colConstr){
		ArrayList<ArrayList<int []>> newTbl = new ArrayList<>();
		for(String s : colConstr) {
			if(s.contains("!")) {
				
			}
			else if(s.contains("=")) {
				
			}
		}
		
		for(ArrayList<int[]> a : table) {
			boolean accepted = true;
			
			for(String s : colConstr) {
				String[] split;
				boolean equal=true;
				if(s.contains("!")) {
					split = s.split("!=");
					equal = false;
				}else {
					split = s.split("=");
					equal = true;
				}
				String first = split[0];
				String conditions = split[1];
				String strToCheckFor = "";
				int firstPos=-1;
				for(int i=0; i<colms.size();i++) {
					if(colms.get(i).equals(first)) {
						firstPos=i;
					}
				}
				for(int j=0; j<conditions.length();j++) {
					String m = conditions.substring(j, j+1);
					int colNo=-1;
					for(int i=0; i<colms.size();i++) {
						if(colms.get(i).equals(m)) {
							colNo = i;
						}
					}
					int[] intToCheck = a.get(colNo);
					strToCheckFor = strToCheckFor + STOREDWORD.substring(intToCheck[0], intToCheck[1]);
					
				}
				int[] wordFromFirst = a.get(firstPos);
				String word = STOREDWORD.substring(wordFromFirst[0], wordFromFirst[1]);
				if(equal) {
					if(!(word.equals(strToCheckFor))) {
						accepted=false;
					}
				}else {
					if(word.equals(strToCheckFor)) {
						accepted=false;
					}
				}
			}
			if(accepted) {
				newTbl.add(consolidateSingleRecords(a));
			}
		}
		
		
		return newTbl;
	}
	
	
	private ArrayList<ArrayList<int []>>  removeDuplicColsFromTbl(ArrayList<ArrayList<int []>> givenTbl, ArrayList<Integer> cols){
		ArrayList<ArrayList<int []>> newTbl = new ArrayList<>();
		ArrayList<int []> newRecord = new ArrayList<>();
		for(ArrayList<int[]> a : givenTbl) {
			for(int b : cols) {
				newRecord.add(a.get(b));
			}
			newTbl.add(consolidateSingleRecords(newRecord));
			newRecord.clear();
		}
		return newTbl;
	}
	
	private ArrayList<ArrayList<int []>> duplicateCols(ArrayList<ArrayList<int []>> table, ArrayList<String> colms){
		ArrayList<ArrayList<int []>> newTbl = new ArrayList<>();
		ArrayList<Integer> freshCols = new ArrayList<>();
		
		
		ArrayList<int []> colsToCheck = new ArrayList<>();
		
		
		int count=0;
		for(String s : colms) {
			int[] entry = new int[2];
			boolean found = false;
			for(int a=0; a<freshCols.size(); a++) {
				if(colms.get(freshCols.get(a)).equals(colms.get(count))){
					found=true;
					entry[0]=freshCols.get(a);
					entry[1]=count;
					colsToCheck.add(consolidateEntry(entry));
				}
			}
			if(!found) {
				freshCols.add(count);
			}
			
			count++;
		}
		for(ArrayList<int []> a : table) {
			boolean accepted = true;
			for(int[] entr : colsToCheck) {
				int posOne = entr[0];
				int posTwo = entr[1];
				int[] entryOne = a.get(posOne);
				int[] entryTwo = a.get(posTwo);
				if(!((entryOne[0]==entryTwo[0])&&(entryOne[1]==entryTwo[1]))) {
					accepted=false;
				}
				
			}
			if(accepted) {
				newTbl.add(a);
			}
		}
		
		
		
		return newTbl;
	}
	
	private int eqnSort(String fullStr) {
		String[] sections = fullStr.split("=");
		String mainstring = sections[0];
		String columns = sections[1];
		ArrayList<String> cols = new ArrayList<>();
		
		char[] charArr = columns.toCharArray();	 
	    for (char ch: charArr) {
	        cols.add(Character.toString(ch));
	    }
		COLUMNS = cols;
		int noOfCols = columns.length();
		return noOfCols;
	}
	
	public void addRegularConstraint(String cols, String regConstr) {
		ArrayList<ArrayList<int []>> oldTable = TABLE;
		ArrayList<ArrayList<int []>> newTable = new ArrayList<>();
		ArrayList<Integer> colsToApply = new ArrayList<>();
		String[] colms = cols.split(",");
		for(String s : colms) {
			for(int i=0; i<COLUMNS.size(); i++) {
				if(s.equals(COLUMNS.get(i))){
					colsToApply.add(i);
				}
			}
		}
		for(ArrayList<int []> record : oldTable) {
			boolean regexCheck = true;
			for(int a : colsToApply) {
				int [] entry = record.get(a);
				String strToTest = STOREDWORD.substring(entry[0], entry[1]);
				boolean regexMatch = Pattern.matches(regConstr, strToTest);
				if(!regexMatch) {
					regexCheck=false;
				}
			}
			
			if(regexCheck) {
				newTable.add(consolidateSingleRecords(record));
			}
		}
		
		TABLE = newTable;
	}
	
	private ArrayList<ArrayList<int []>> makeWholeTbl(int i, int j, int cols) {
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
				ArrayList<ArrayList <int[]>> theseRecords = makeWholeTbl(j-m, j, cols-1);
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
	
	public ArrayList<ArrayList<int []>> makeTbl(int i, int j, int cols, ArrayList<String> allCols, ArrayList<String> regConstr) {
			ArrayList<ArrayList<int []>> allRecords = new ArrayList<ArrayList<int []>>();
			ArrayList<int []> singleRecord = new ArrayList<int []>();
			int entry[]=new int[2];
			
			int thisColPos = allCols.size()-cols;
			String thisCol = allCols.get(thisColPos);
			
			//If string is empty, each column is set to the empty word
			if (cols==1){
				boolean fits = true;
				String substring = STOREDWORD.substring(i,j);
				for(String s : regConstr) {
					String[] decomp = s.split(":");
					String var = decomp[0];
					String regex = decomp[1];
					if(var.equals(thisCol)) {
						 Pattern pattern = Pattern.compile(regex);
						 Matcher matcher = pattern.matcher(substring);
						 boolean matchFound = matcher.find();
						 if(!matchFound) {
							 fits=false;
						 }
					}
				}
				if(fits) {
					entry[0]=i;
					entry[1]=j;
					singleRecord.add(consolidateEntry(entry));
					allRecords.add(consolidateSingleRecords(singleRecord));
					singleRecord.clear();
				}
				
			}else {
				for(int m=j; m>=i; m--) {
					boolean fits = true;
					String substring = STOREDWORD.substring(i,m);
					for(String s : regConstr) {
						String[] decomp = s.split(":");
						String var = decomp[0];
						String regex = decomp[1];
						if(var.equals(thisCol)) {
							 Pattern pattern = Pattern.compile(regex);
							 Matcher matcher = pattern.matcher(substring);
							 boolean matchFound = matcher.find();
							 if(!matchFound) {
								 fits=false;
							 }
						}
					}
					if(fits) {
						ArrayList<ArrayList<int []>> newTbl = makeTbl(m, j, cols-1, allCols, regConstr);
						for(ArrayList<int []> a : newTbl) {
							int arraySize = a.size();
							if(arraySize==cols-1) {
								entry[0]=i;
								entry[1]=m;
								singleRecord.add(consolidateEntry(entry));
								for(int[] b : a) {
									singleRecord.add(consolidateEntry(b));
								}
								allRecords.add(consolidateSingleRecords(singleRecord));
								singleRecord.clear();
							}
						}
						
						
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
		
	private ArrayList<ArrayList<int []>> consolidateTables(ArrayList<ArrayList<int []>> tbl) {
		ArrayList<ArrayList<int []>> tableOut = new ArrayList<>();
		for(ArrayList<int []> c : tbl) {
			tableOut.add(consolidateSingleRecords(c));
		}
		return tableOut;
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
		
	private ArrayList<Integer> oldGenerateLCPArray() {
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
	
	private ArrayList<Integer> generateLCPArray() {
		ArrayList<Integer> LCPArray = new ArrayList<Integer>();
		ArrayList<Integer>  suffixArray = SUFFIXARRAY;
		int[] lcp = new int[suffixArray.size()];
		ArrayList<Integer>  inverseSuffixArray = INVERSESUFFIXARRAY;
		String txt = STOREDWORD;
		int n = suffixArray.size();
		int k = 0;
		
		 for (int i=0; i<n; i++){
			 if (inverseSuffixArray.get(i) == n-1)
		        {
		            k = 0;
		            continue;
		        }
			 int j = suffixArray.get(inverseSuffixArray.get(i)+1);
			 
			 boolean match = true;
			 while ( (i+k<n) && (j+k<n) && match ) {
				 if(i+k<txt.length() && j+k<txt.length()) {
					 if(txt.charAt(i+k)==txt.charAt(j+k)){
						 k++;
					 }else {
						 match = false;
					 }
				 }else {
					 match = false;
				 }
			 }
		 
			 lcp[inverseSuffixArray.get(i)] = k;;
		 
		     if (k>0) {
		         k--;
		     }
			 
			 
		 }
			
		LCPArray.add(null);
		for(int i : lcp) {
			LCPArray.add(i);
		}
		LCPArray.remove(LCPArray.size()-1);
		
		
		return LCPArray;
	}
	
	private ArrayList<Integer> trialGenerateLCPArray() {
		String word = STOREDWORD;
		ArrayList<Integer>  suffixArray = SUFFIXARRAY;
		ArrayList<Integer>  inverseSuffixArray = INVERSESUFFIXARRAY;
		ArrayList<String> unsortedLCPArray = new ArrayList<String>();
		ArrayList<Integer> LCPArray = new ArrayList<Integer>();
		unsortedLCPArray.add(null);
		
		
		String thisLCP = "";
		
		for(int i=word.length()-1; i>=0;i--) {
			thisLCP = "";
			//Initial char
			String thisChar = Character.toString(word.charAt(i));
			int invSuff = inverseSuffixArray.get(i);
			
			//Value prior in suffix array
			int priorValue = suffixArray.get(invSuff-1);
			String prior;
			if(priorValue==word.length()) {
				prior="";
			}else {
				prior = Character.toString(word.charAt(priorValue));
			}
			
			if(thisChar.equals(prior)) {
				int onePlusThisi=i+1;
				int onePlusPriorValue = priorValue+1;
				if(onePlusPriorValue < word.length() && onePlusThisi < word.length()) {
					String onePlusThisChar = Character.toString(word.charAt(onePlusThisi));
					String onePlusPrior = Character.toString(word.charAt(onePlusPriorValue));
					if(onePlusThisChar.equals(onePlusPrior)) {
						thisLCP="l"+Integer.toString(onePlusThisi);
					}else {
						thisLCP="1";
					}
					
					
					
				}else {
					thisLCP="1";
				}
		
			}else {
				thisLCP="0";
			}
			unsortedLCPArray.add(""+thisLCP);
			//System.out.println("Unsorted Array: "+unsortedLCPArray);
			
		}
		ArrayList<Integer> newLCPArray = new ArrayList<Integer>();
		System.out.println(unsortedLCPArray);
		newLCPArray.add(null);
		for(int j=1; j<unsortedLCPArray.size();j++) {
			String p = unsortedLCPArray.get(j);
			if(p.contains("l")) {
				String strInt = p.substring(1);
				System.out.println(strInt);
				int lookUp = word.length()-Integer.parseInt(strInt);
				System.out.println(lookUp);
				int total = newLCPArray.get(lookUp);
				System.out.println(total);
				newLCPArray.add(total+1);
				System.out.println();
			}else {
				newLCPArray.add(Integer.parseInt(p));
			}
		}
		
		
		System.out.println(newLCPArray);
		for(int m : suffixArray) {
			LCPArray.add(newLCPArray.get(word.length()-m));
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
		
	private ArrayList<Integer> oldGenerateInverseSuffixArray (){
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
	
	private ArrayList<Integer> generateInverseSuffixArray (){
		ArrayList<Integer> suffixArray = SUFFIXARRAY;
		int[] inv = new int[suffixArray.size()];
		ArrayList<Integer> inverse = new ArrayList<Integer>();
		for(int i=0; i<suffixArray.size();i++) {
			inv[suffixArray.get(i)]=i;
		}
		
		for(int j=0; j<inv.length;j++) {
			inverse.add(inv[j]);
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
		
	
	public ArrayList<ArrayList<int []>> eliminateRepititionsInTbl(ArrayList<ArrayList<int []>> tableToOptimise) {
			ArrayList<Integer> inverseSuffixArray = INVERSESUFFIXARRAY;
			ArrayList<Integer> lcpArray = LCPARRAY;
			//ArrayList<ArrayList<int []>> tableToOptimise = TABLE;
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
			//TABLE = optimisedTable;
			return optimisedTable;
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
			
	public ArrayList<String> enumerateAllSubwords() {
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
			String subwords;
			ArrayList<String> sbwords = new ArrayList<>();
			for(int[] a : subwordList) {
				subwords=STOREDWORD.substring(a[0], a[1]);
				sbwords.add(subwords);
			}
			
			return sbwords;
		}
		
	public boolean stringEqualityTesting(int[] strOne, int[] strTwo) {
			int a = strOne[0];
			int b = strOne[1];
			int c = strTwo[0];
			int d = strTwo[1];
			boolean strEqual = false;
			if(b-a == d-c) {
				int lcp = lookUpLCP(LCPARRAY, INVERSESUFFIXARRAY, a, c);
				if(lcp >= b-a) {
					strEqual = true;
				}
			}
			return strEqual;
		}
		
	public ArrayList<ArrayList<int []>> deDuplicate(ArrayList<ArrayList<int []>> tbl) {
			ArrayList<ArrayList<int []>> oldTable = tbl;
			ArrayList<ArrayList<int []>> newTable = new ArrayList<>();
			
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
			return newTable;
		}

	public ArrayList<ArrayList<int []>> projection(ArrayList<ArrayList<int []>> oldTable, ArrayList<String> oldCols, ArrayList<String> newCols) {
		ArrayList<ArrayList<int []>> newTable = new ArrayList<ArrayList<int []>>();
		ArrayList<int []> newrec = new ArrayList<>();
		
		ArrayList<Integer> colPosToKeep = new ArrayList<>();
		for(int i=0; i<oldCols.size(); i++) {
			for(String s : newCols) {
				if(oldCols.get(i).equals(s)) {
					colPosToKeep.add(i);
				}
			}
		}
		
		for(ArrayList<int []> record : oldTable) {
			for(int a : colPosToKeep) {
				newrec.add(record.get(a));
			}
			newTable.add(consolidateSingleRecords(newrec));
			newrec.clear();
		}
		
		return newTable;
		
	}
	
	private ArrayList<String> colJoin(ArrayList<String> colsOne, ArrayList<String> colsTwo){
		ArrayList<String> fullColList = new ArrayList<>();
		fullColList.addAll(colsOne);
		int count = 0;
		
		for(String s : colsTwo) {
			boolean found = false;
			for(String t : colsOne) {
				if(s.equals(t)) {
					found = true;
				}
			}
			if(!found) {
				fullColList.add(s);
			}
			count++;
		}
		
		return fullColList;
	}

	public ArrayList<ArrayList<int []>> join(ArrayList<ArrayList<int []>> tableOne, ArrayList<String> colsOne, ArrayList<ArrayList<int []>> tableTwo, ArrayList<String> colsTwo) {
		
		ArrayList<ArrayList<int []>> newTable = new ArrayList<>();
		ArrayList<Integer> colIntMatch = new ArrayList<>();
		ArrayList<String> fullColList = new ArrayList<>();
		fullColList.addAll(colsOne);
		ArrayList<Integer> colsFromTblTwo = new ArrayList<>();
		int count = 0;
		for(String s : colsTwo) {
			boolean found = false;
			for(String t : colsOne) {
				if(s.equals(t)) {
					found = true;
				}
			}
			if(!found) {
				colsFromTblTwo.add(count);
				fullColList.add(s);
			}
			count++;
		}
		for(int i=0; i<colsOne.size(); i++) {
			for(int j=0; j<colsTwo.size(); j++) {
				if(colsOne.get(i).equals(colsTwo.get(j))) {
					colIntMatch.add(i);
					colIntMatch.add(j);
				}
			}
		}
		ArrayList<int[]> record = new ArrayList<>();
		
		for(ArrayList<int[]> a : tableOne) {
			for(ArrayList<int[]> b : tableTwo) {
				boolean accepted = true;
				for(int k=0; k<colIntMatch.size(); k=k+2) {
					if(accepted) {
						int[] one = a.get(colIntMatch.get(k));
						int[] two = b.get(colIntMatch.get(k+1));
						if(!( (one[0]==two[0]) && (one[1]==two[1]) )){
							accepted=false;
						}
					}
				}
				if(accepted) {
					record.addAll(a);
					for(int x : colsFromTblTwo) {
						record.add(b.get(x));
					}
					newTable.add(consolidateSingleRecords(record));
					record.clear();
				}
				
				
			}
		}
		//TABLE=newTable;
		//COLUMNS = fullColList;
		
		return newTable;
	}

	public ArrayList<ArrayList<int []>> semijoin(ArrayList<ArrayList<int []>> tableOne, ArrayList<String> colsOne, ArrayList<ArrayList<int []>> tableTwo, ArrayList<String> colsTwo) {
		ArrayList<ArrayList<int []>> newTbl = new ArrayList<>();
		ArrayList<Integer> colIntMatch = new ArrayList<>();
		
		for(int i=0; i<colsOne.size(); i++) {
			for(int j=0; j<colsTwo.size(); j++) {
				if(colsOne.get(i).equals(colsTwo.get(j))) {
					colIntMatch.add(i);
					colIntMatch.add(j);
				}
			}
		}
		
		for(ArrayList<int[]> a : tableOne) {
			for(ArrayList<int[]> b : tableTwo) {
				boolean accepted = true;
				for(int k=0; k<colIntMatch.size(); k=k+2) {
					if(accepted) {
						int[] one = a.get(colIntMatch.get(k));
						int[] two = b.get(colIntMatch.get(k+1));
						if(!( (one[0]==two[0]) && (one[1]==two[1]) )){
							accepted=false;
						}
					}
				}
				if(accepted) {
					newTbl.add(a);
				}
			}
		}
		
		TABLE = newTbl;
		COLUMNS = colsOne;
		return newTbl;
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
	public ArrayList<String> getCols(){
		return COLUMNS;
	}
	public void setTable(ArrayList<ArrayList<int []>> tbl){
			TABLE=tbl;
		}

	

}














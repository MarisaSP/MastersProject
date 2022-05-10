import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
class TestArrays {

	@Test
	void test() {
		
		
		
		TestLibrary testLibrary = new TestLibrary();
		//testLibrary.inputStr("marisaswordsareverylongandcomplicated");
		//System.out.println(testLibrary.enumerateAllSubwords());
		//testLibrary.setup("aba","m:=Ans(x)<-(S=xyz)(S=wz)(y:[b])");
		testLibrary.setup("aba","m:=Ans(x)<-(S=xyx)");
		
		
		Pattern pattern = Pattern.compile("[a|b]");
		 Matcher matcher = pattern.matcher("ab");
		 boolean matchFound = matcher.find();
		 if(matchFound) {
			 System.out.println("");
		 }
		
		
		
		/*TestLibrary tl = new TestLibrary();
		tl.setup("abab","m=zx");
		
		ArrayList<ArrayList<int []>> firstTbl = testLibrary.getTable();
		ArrayList<String> firstCols = testLibrary.getCols();
	
		
		System.out.println("OGtable");
		for(ArrayList <int []> b : firstTbl) {
			int count = 0;
			for( int [] c : b) {
				System.out.print("["+c[0]+","+c[1] + ")"+ " ");
				count++;
			}
			System.out.println(" ");
		}
		
		ArrayList<ArrayList<int []>> otherTbl = tl.getTable();
		ArrayList<String> otherCols = tl.getCols();
		
		testLibrary.semijoin(firstTbl, firstCols, otherTbl, otherCols);
		
		
		
		
		
		
		ArrayList<ArrayList<int []>> secondTbl = testLibrary.getTable();
		System.out.println("New table");
		for(ArrayList <int []> b : secondTbl) {
			int count = 0;
			for( int [] c : b) {
				System.out.print("["+c[0]+","+c[1] + ")"+ " ");
				count++;
			}
			System.out.println(" ");
		}
		
		*/
		
		/*
		ArrayList<ArrayList<int []>> mytbl = new ArrayList<>();
		int[] first = new int[2];		
		first[0] = 0;
		first[1]=1;
		int[] second = new int[2];	
		second[0]=2;
		second[1]=3;
		first[0] = 0;
		first[1]=1;
		ArrayList<int[]> record = new ArrayList<>();
		ArrayList<int[]> recordtwo = new ArrayList<>();
		ArrayList<int[]> recordthree = new ArrayList<>();
		record.add(first);
		record.add(first);
		record.add(second);
		mytbl.add(record);
		mytbl.add(record);
		recordtwo.add(second);
		recordtwo.add(first);
		recordtwo.add(first);
		recordthree.add(first);
		recordthree.add(second);
		recordthree.add(second);
		mytbl.add(recordthree);
		mytbl.add(recordthree);
		mytbl.add(recordtwo);
		
		
		
		
		TestLibrary tl = new TestLibrary();
		tl.setTable(mytbl);
		ArrayList<ArrayList<int []>> firstTbl = tl.getTable();
		System.out.println("OGtable");
		for(ArrayList <int []> b : firstTbl) {
			int count = 0;
			for( int [] c : b) {
				System.out.print("["+c[0]+","+c[1] + ")"+ " ");
				count++;
			}
			System.out.println(" ");
		}
		
		tl.deDuplicate(firstTbl);
		ArrayList<ArrayList<int []>> secondTbl = tl.getTable();
		System.out.println();
		System.out.println("Second");
		for(ArrayList <int []> b : secondTbl) {
			int count = 0;
			for( int [] c : b) {
				System.out.print("["+c[0]+","+c[1] + ")"+ " ");
				count++;
			}
			System.out.println(" ");
		}*/
		
	/*	tl.setup("banana", 2);
		ArrayList<ArrayList<int []>> a = tl.getTable();
		System.out.println("Start");
		for(ArrayList <int []> b : a) {
			int count = 0;
			for( int [] c : b) {
				System.out.print("["+c[0]+","+c[1] + ")"+ " ");
				count++;
			}
			System.out.println(" ");
		}
		System.out.println(tl.getSuffixArray());
		System.out.println(tl.getLCPArray());
		System.out.println(tl.getInverseSuffixArray());
		System.out.println(" ");
		
		
		tl.eliminateRepititionsInTbl();
		ArrayList<ArrayList<int []>> d = tl.getTable();
		
		for(ArrayList <int []> b : d) {
			int count = 0;
			for( int [] c : b) {
				System.out.print("["+c[0]+","+c[1] + ")"+ " ");
				count++;
			}
			System.out.println(" ");
		}
		
		System.out.println("Subwords");
		
		ArrayList <int []> subwordList = tl.enumerateAllSubwords();
		for( int [] c : subwordList) {
			System.out.println("["+c[0]+","+c[1] + ")"+ " ");
		}
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*ArrayList<Integer> suffixArray = generateArrays.SuffixArray("insulin");
		System.out.println("Suffix Array");
		System.out.println(suffixArray);
		ArrayList<Integer> LCPArray = generateArrays.generateLCPArray(suffixArray, "insulin");
		System.out.println("LCP Array");
		System.out.println(LCPArray);
		int result = generateArrays.rangeMinimumQuery(LCPArray, 2, 3);
		System.out.println("RMQ");
		System.out.println(result); */
		
		/*
		TableInstanceClass thisTable = new TableInstanceClass();
		ArrayList<ArrayList<int []>> a = thisTable.makeTbl(0,6,3,6);
		System.out.println("Original Table");
		for(ArrayList <int []> b : a) {
			int count = 0;
			for( int [] c : b) {
				System.out.print("["+c[0]+","+c[1] + ")"+ " ");
				count++;
			}
			System.out.println(" ");
		}
		thisTable.setup("banana",a);
		thisTable.formArrays();
		System.out.println(thisTable.getInverseSuffixArray());
		System.out.println(thisTable.getSuffixArray());
		System.out.println(thisTable.getLCPArray());
		
		System.out.println("1");
		ArrayList<ArrayList<int []>> x = Optimisation.equalityCheck(thisTable.getInverseSuffixArray(), thisTable.getLCPArray(), a);
		System.out.println("2");
		System.out.println("New Table");
		System.out.println("3");
		for(ArrayList <int []> b : x) {
			int count = 0;
			for( int [] c : b) {
				System.out.print("["+c[0]+","+c[1] + ")"+ " ");
				count++;
			}
			System.out.println(" ");
		}
		System.out.println("4");
		*/
		//TableInstanceClass anotherTable = new TableInstanceClass();
		//ArrayList<ArrayList<int []>> ogTable = anotherTable.makeTbl(0,4,2,4);
		
		

		/*
		TestLibrary tst = new TestLibrary();
		tst.setup("abab",2);
		//ArrayList<ArrayList<int []>> a = tst.getTable();
		System.out.println("Original Table");
		for(ArrayList <int []> b : a) {
			int count = 0;
			for( int [] c : b) {
				System.out.print("["+c[0]+","+c[1] + ")"+ " ");
				count++;
			}
			System.out.println(" ");
		}
		
		//tst.eliminateRepititionsInTbl();
		ArrayList<ArrayList<int []>> d = tst.getTable();
		System.out.println("New Table");
		for(ArrayList <int []> b : d) {
			int count = 0;
			for( int [] c : b) {
				System.out.print("["+c[0]+","+c[1] + ")"+ " ");
				count++;
			}
			System.out.println(" ");
		} */
	}

}

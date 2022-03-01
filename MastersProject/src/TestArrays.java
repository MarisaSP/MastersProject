import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestArrays {

	@Test
	void test() {
		/*ArrayList<Integer> suffixArray = generateArrays.SuffixArray("insulin");
		System.out.println("Suffix Array");
		System.out.println(suffixArray);
		ArrayList<Integer> LCPArray = generateArrays.generateLCPArray(suffixArray, "insulin");
		System.out.println("LCP Array");
		System.out.println(LCPArray);
		int result = generateArrays.rangeMinimumQuery(LCPArray, 2, 3);
		System.out.println("RMQ");
		System.out.println(result); */
		TableInstanceClass thisTable = new TableInstanceClass();
		ArrayList<ArrayList<int []>> a = thisTable.makeTbl(0,4,2,4);
		for(ArrayList <int []> b : a) {
			int count = 0;
			for( int [] c : b) {
				System.out.print("["+c[0]+","+c[1] + ")"+ " ");
				count++;
			}
			System.out.println(" ");
		}
		thisTable.setup("abab",a);
		thisTable.formArrays();
	}

}

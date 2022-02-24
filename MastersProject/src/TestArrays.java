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
		ArrayList<ArrayList<int []>> a = TableInstanceClass.makeTbl(0,5,5,6);
		for(ArrayList <int []> b : a) {
			int count = 0;
			for( int [] c : b) {
				System.out.println("Count: "+count);
				System.out.println(c[0]);
				System.out.println(c[1]);
				count++;
			}
		}
	}

}

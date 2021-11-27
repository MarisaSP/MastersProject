import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestArrays {

	@Test
	void test() {
		ArrayList<Integer> suffixArray = generateArrays.SuffixArray("banana");
		System.out.println("Suffix Array");
		System.out.println(suffixArray);
		ArrayList<Integer> LCPArray = generateArrays.generateLCPArray(suffixArray, "banana");
		System.out.println("LCP Array");
		System.out.println(LCPArray);
		int result = generateArrays.rangeMinimumQuery(suffixArray, 2, 6);
		System.out.println("RMQ");
		System.out.println(result);
	}

}

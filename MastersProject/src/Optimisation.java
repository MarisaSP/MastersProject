import java.util.ArrayList;

public class Optimisation extends TableInstanceClass{

	public static ArrayList<ArrayList<int []>> equalityCheck(ArrayList<Integer> inverseSuffixArray, ArrayList<Integer> lcpArray, ArrayList<ArrayList<int []>> tableToOptimise) {
		ArrayList<int []> originals = new ArrayList<int []>();
		ArrayList<ArrayList<int []>> optimisedTable = new ArrayList<ArrayList<int []>>();
		int[] indexes = new int[2];
		ArrayList<int []> freshRecords = new ArrayList<int []>();
		System.out.println("a");
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
					for(int[] OG : originals) {
						int LCP = generateArrays.lookUpLCP(lcpArray, inverseSuffixArray, Integer.valueOf(b[0]), Integer.valueOf(OG[0]));
						if( (LCP >= (b[1]-b[0])&&(LCP!=0)) || (( (b[1]-b[0])==0) && (OG[1]-OG[0]==0)) ) {
							found = true;
							indexes[0]=OG[0];
							indexes[1]=OG[1];
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
		return optimisedTable;
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
}

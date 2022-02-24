import java.util.List;
import java.util.ArrayList;

public class TableInstanceClass {
	
	String strStored;
	List<String> tableColumns;
	List<String> records;

	public void createTable(int wordLength, List<String> columns) {
		ArrayList arrayOfRecords = new ArrayList();
		ArrayList record = new ArrayList();
		if (columns.size() == 1) {
			record.add(subword(0,wordLength));
			arrayOfRecords.add(recordToAppend(record));
			
		}
		else {
			
		}
	}
	
    public ArrayList recordToAppend (ArrayList tempList) {
        ArrayList append = new ArrayList<>();
       // for (ArrayList s: tempList) {
       //     append.add(s);
      //  }
        return append;
    }
	
	
	
	
	public ArrayList subword(int i, int j) {
		ArrayList sw = new ArrayList();
		sw.add(i);
		sw.add(j);
		return sw;
	}
	
	public static ArrayList<ArrayList<int []>> makeTbl(int i, int j, int cols, int wordLength) {
		ArrayList allRecords = new ArrayList<ArrayList>();
		ArrayList singleRecord = new ArrayList<int []>();
		int entry[]=new int[2];
		
		if(i>j) {
			entry[0]=-1;
			entry[1]=-1;
			for(int m=0; m<cols; m++) {
				singleRecord.add(consolidateEntry(entry));
			}
		allRecords.add(singleRecord);
		
		}else if (cols==1){
			entry[0]=i;
			entry[1]=j;
			singleRecord.add(consolidateEntry(entry));
			allRecords.add(singleRecord);
		}else {
			entry[0]=i;
			entry[1]=j;
			singleRecord.add(consolidateEntry(entry));
			for(int x=1; x<cols;x++) {
				entry[0]=-1;
				entry[1]=-1;
				singleRecord.add(consolidateEntry(entry));
			}
			allRecords.add(singleRecord);
			for(int m=0; m<(j-i); m++) {
				
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
}




















import java.util.ArrayList;

public class BorrowRecordList {
	private static ArrayList<BorrowRecord> borrowedRecord = new ArrayList<>();

	public static ArrayList<BorrowRecord> getBorrowedRecord() {
		return borrowedRecord;
	}

	public static void setBorrowedRecord(ArrayList<BorrowRecord> borrowedRecord) {
		BorrowRecordList.borrowedRecord = borrowedRecord;
	}

	
}

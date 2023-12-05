import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class BorrowRecordFileManager  {
	 @SuppressWarnings("unchecked")
	    public static void loadData() throws IOException, ClassNotFoundException {
	        File file = new File("borrowRecord.bin");
	        if (file.exists()) {
	            FileInputStream fileInputStream = new FileInputStream("borrowRecord.bin");
	            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	            BorrowRecordList.setBorrowedRecord((ArrayList<BorrowRecord>) objectInputStream.readObject());
	            objectInputStream.close();
	        }
	    }

	    public static void saveData() throws IOException {
	        FileOutputStream fileOutputStream = new FileOutputStream("borrowRecord.bin");
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	        objectOutputStream.writeObject(BorrowRecordList.getBorrowedRecord());
	        objectOutputStream.close();
	    }
}

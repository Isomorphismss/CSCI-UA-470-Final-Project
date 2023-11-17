import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class BorrowRecord implements Serializable {
    private Book borrowedBook;
    private String borrowerName;
    private Date borrowDate;
    private Date dueDate;
    private static ArrayList<BorrowRecord> borrowedRecord = new ArrayList<>();

    public BorrowRecord(Book borrowedBook, String borrowerName, Date borrowDate, Date dueDate) {
        this.borrowedBook = borrowedBook;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public static ArrayList<BorrowRecord> getAllBorrowRecord(){
        return borrowedRecord;
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public static void setAllBorrowRecord(ArrayList<BorrowRecord> borrowedRecord){
        BorrowRecord.borrowedRecord = borrowedRecord;
    }

    public void setBorrowedBook(Book borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isOverdue(Date currentDate) {
        return currentDate.after(this.dueDate);
    }


    @SuppressWarnings("unchecked")
    public static void loadData() throws IOException, ClassNotFoundException {
        File file = new File("borrowRecord.bin");
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream("booksInventory.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            BorrowRecord.setAllBorrowRecord((ArrayList<BorrowRecord>) objectInputStream.readObject());
            objectInputStream.close();
        }
    }

    public static void saveData() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("borrowRecord.bin");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(BorrowRecord.getAllBorrowRecord());
        objectOutputStream.close();
    }
}
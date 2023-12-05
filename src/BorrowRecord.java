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
    

    public BorrowRecord(Book borrowedBook, String borrowerName, Date borrowDate, Date dueDate) {
        this.borrowedBook = borrowedBook;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
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


    
}
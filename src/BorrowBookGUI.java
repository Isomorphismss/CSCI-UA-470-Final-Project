import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BorrowBookGUI extends JDialog {
    private Book book;
    private JTextField borrowerNameField;
    private JTextField dueDateField;
    private JButton submitButton;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public BorrowBookGUI(JFrame parent, Book book) {
        super(parent, "Borrow Book", true);
        this.book = book;
        initializeComponents();
        setupLayout();
        pack();
    }

    private void initializeComponents() {
        borrowerNameField = new JTextField(20);
        dueDateField = new JTextField(20);
        submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String borrowerName = borrowerNameField.getText();
                    Date dueDate = dateFormat.parse(dueDateField.getText());
                    Date currentDate = new Date();
                    if (borrowerName.isEmpty()) {
                        JOptionPane.showMessageDialog(BorrowBookGUI.this, "Invalid input. Please check the borrower's name.");
                        return;
                    }
                    else if (dueDate.before(currentDate)){
                        JOptionPane.showMessageDialog(BorrowBookGUI.this, "Invalid due date. Due date should not be earlier than today");
                        return;
                    }
                    book.setQuantity(book.getQuantity() - 1);
                    BorrowRecord borrowRecord = new BorrowRecord(book, borrowerName, currentDate, dueDate);
                    ArrayList<BorrowRecord> currentBorrowRecord = BorrowRecord.getAllBorrowRecord();
                    currentBorrowRecord.add(borrowRecord);
                    BorrowRecord.saveData();
                    Book.saveData();

                    JOptionPane.showMessageDialog(BorrowBookGUI.this, "Book lent successfully.");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(BorrowBookGUI.this, "Error parsing date. Please use the format yyyy-MM-dd.");
                }
            }
        });
    }

    private void setupLayout() {
        setLayout(new GridLayout(3, 2));
        add(new JLabel("Borrower's Name:"));
        add(borrowerNameField);
        add(new JLabel("Due Date (yyyy-MM-dd):"));
        add(dueDateField);
        add(submitButton);
    }
}
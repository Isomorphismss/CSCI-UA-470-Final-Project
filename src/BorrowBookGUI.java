import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class BorrowBookGUI extends JDialog {
    private JPanel contentPane;
    private JTextField borrowerNameField;
    private JTextField dueDateField;
    private JButton submitButton;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Book book;
    private JLabel lblBorrowerName;
    private JLabel lblDueDate;
    public BorrowBookGUI(JFrame parent, Book book) {
        super(parent, "Borrow Book", true);
        this.book = book;

        setBounds(100, 100, 450, 200);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lblBorrowerName = new JLabel("Borrower's Name:");
        lblBorrowerName.setBounds(62, 40, 110, 15);
        contentPane.add(lblBorrowerName);

        borrowerNameField = new JTextField(20);
        borrowerNameField.setBounds(182, 37, 187, 21);
        contentPane.add(borrowerNameField);

        lblDueDate = new JLabel("Due Date (yyyy-MM-dd):");
        lblDueDate.setBounds(62, 70, 150, 15);
        contentPane.add(lblDueDate);

        dueDateField = new JTextField(20);
        dueDateField.setBounds(222, 67, 137, 21);
        contentPane.add(dueDateField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(172, 120, 93, 23);
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
                    else if (dueDate.before(currentDate)) {
                        JOptionPane.showMessageDialog(BorrowBookGUI.this, "Invalid due date. Due date should not be earlier than today.");
                        return;
                    }
                    book.setQuantity(book.getQuantity() - 1);
                    BorrowRecord borrowRecord = new BorrowRecord(book, borrowerName, currentDate, dueDate);
                    ArrayList<BorrowRecord> currentBorrowRecord = BorrowRecordList.getBorrowedRecord();
                    currentBorrowRecord.add(borrowRecord);
                    BorrowRecordFileManager.saveData();
                    BookFileManager.saveData();

                    JOptionPane.showMessageDialog(BorrowBookGUI.this, "Book lent successfully.");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(BorrowBookGUI.this, "Error parsing date. Please use the format yyyy-MM-dd.");
                }
            }
        });
        contentPane.add(submitButton);
    }
}
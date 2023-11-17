import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ViewAllBorrowedBooksGUI extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JButton btnReturn;

    public ViewAllBorrowedBooksGUI() throws IOException {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 550, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        displayAllBorrowedBooks(BorrowRecord.getAllBorrowRecord());

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        btnClose.setBounds(320, 211, 150, 23);
        contentPane.add(btnClose);

        btnReturn = new JButton("Return This Book");
        btnReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    BorrowRecord selectedRecord = BorrowRecord.getAllBorrowRecord().get(selectedRow);
                    Book returnedBook = selectedRecord.getBorrowedBook();
                    for (Book book : Book.getBooksInventory()) {
                        if (book.equals(returnedBook)) {
                            book.setQuantity(book.getQuantity() + 1);
                            break;
                        }
                    }
                    BorrowRecord.getAllBorrowRecord().remove(selectedRow);
                    try {
                        Book.saveData();
                        BorrowRecord.saveData();
                        displayAllBorrowedBooks(BorrowRecord.getAllBorrowRecord());
                        JOptionPane.showMessageDialog(ViewAllBorrowedBooksGUI.this, "Book returned successfully.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(ViewAllBorrowedBooksGUI.this, "Error saving data.");
                    }
                } else {
                    JOptionPane.showMessageDialog(ViewAllBorrowedBooksGUI.this, "Please select a book to return.");
                }
            }
        });
        btnReturn.setBounds(80, 211, 150, 23);
        contentPane.add(btnReturn);
    }

    public void close() {
        dispose();
    }

    public void displayAllBorrowedBooks(ArrayList<BorrowRecord> borrowRecords) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String[] columnNames = {"Book Title", "Borrower Name", "Borrowed Date", "Due Date", "Days Left/Overdue"};
        Object[][] data = new Object[borrowRecords.size()][5];
        for (int i = 0; i < borrowRecords.size(); i++) {
            BorrowRecord record = borrowRecords.get(i);
            data[i][0] = record.getBorrowedBook().getTitle();
            data[i][1] = record.getBorrowerName();
            data[i][2] = dateFormat.format(record.getBorrowDate());
            data[i][3] = dateFormat.format(record.getDueDate());
            data[i][4] = getDaysLeftOrOverdue(record.getDueDate());
        }
        if (tableModel == null) {
            tableModel = new DefaultTableModel(data, columnNames);
            table = new JTable(tableModel) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(10, 10, 520, 180);
            contentPane.add(scrollPane);
        } 
        else {
            tableModel.setDataVector(data, columnNames);
        }
    }

    private String getDaysLeftOrOverdue(Date dueDate) {
        long millisPerDay = 24 * 60 * 60 * 1000;
        long daysLeft = (dueDate.getTime() - System.currentTimeMillis()) / millisPerDay;
        return daysLeft >= 0 ? String.valueOf(daysLeft) : "Overdue!";
    }
}

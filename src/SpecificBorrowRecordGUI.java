import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SpecificBorrowRecordGUI extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    public SpecificBorrowRecordGUI(ArrayList<BorrowRecord> borrowRecordsForBook) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 550, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        displayAllBorrowedBooks(borrowRecordsForBook);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        btnClose.setBounds(200, 211, 150, 23);
        contentPane.add(btnClose);
    }

    private void close() {
        dispose();
    }

    private void displayAllBorrowedBooks(ArrayList<BorrowRecord> borrowRecordsForBook) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String[] columnNames = {"Book Title", "Borrower Name", "Borrowed Date", "Due Date", "Days Left/Overdue"};
        Object[][] data = new Object[borrowRecordsForBook.size()][5];

        for (int i = 0; i < borrowRecordsForBook.size(); i++) {
            BorrowRecord record = borrowRecordsForBook.get(i);

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
        } else {
            tableModel.setDataVector(data, columnNames);
        }
    }

    private String getDaysLeftOrOverdue(Date dueDate) {
        long millisPerDay = 24 * 60 * 60 * 1000;
        long daysLeft = (dueDate.getTime() - System.currentTimeMillis()) / millisPerDay;
        return daysLeft >= 0 ? String.valueOf(daysLeft) : "Overdue!";
    }
}

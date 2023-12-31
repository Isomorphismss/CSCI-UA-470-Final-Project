import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.*;

public class SearchBookGUI extends JFrame {
    private JPanel contentPane;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnReset;
    private JButton btnCancel;
    private JButton btnLendBook;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnShowLendingRecord;
    private Map<Integer, Book> rowToBookMap = new HashMap<>();

    public SearchBookGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query = txtSearch.getText();
                search(query);
            }
        });
        btnSearch.setBounds(58, 204, 93, 23);
        contentPane.add(btnSearch);

        btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        btnReset.setBounds(177, 204, 93, 23);
        contentPane.add(btnReset);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        btnCancel.setBounds(297, 204, 93, 23);
        contentPane.add(btnCancel);

        btnLendBook = new JButton("Lend this Book");
        btnLendBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(table==null) {
            		JOptionPane.showMessageDialog(SearchBookGUI.this, "Please select a book to lend.");
            		return;
            	}
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Book selectedBook = rowToBookMap.get(selectedRow);
                    if (selectedBook != null) {
                        if (selectedBook.getQuantity() > 0) {
                            lendBook(selectedBook);
                        } else {
                            JOptionPane.showMessageDialog(SearchBookGUI.this, "This book is currently not available for lending.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(SearchBookGUI.this, "Please select a valid book to lend.");
                    }
                } else {
                    JOptionPane.showMessageDialog(SearchBookGUI.this, "Please select a book to lend.");
                }
            }
        });
        btnLendBook.setBounds(134, 240, 180, 23);
        contentPane.add(btnLendBook);

        JButton btnShowLendingRecord = new JButton("Show Lending Record");
        btnShowLendingRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLendingRecord();
            }
        });
        btnShowLendingRecord.setBounds(134, 270, 180, 23);
        contentPane.add(btnShowLendingRecord);

        txtSearch = new JTextField();
        txtSearch.setBounds(58, 28, 321, 21);
        contentPane.add(txtSearch);
        txtSearch.setColumns(10);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(58, 62, 329, 132);
        contentPane.add(scrollPane);
    }

    public void search(String query) {
        contentPane.remove(scrollPane);
        String[] columnNames = {"Title", "Author", "ISBN", "Genre", "Quantity"};
        ArrayList<Book> results = new ArrayList<>();
        if (!query.isEmpty()) {
            for (Book book : BookList.getBooksInventory()) {
                if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                    book.getISBN().toLowerCase().contains(query.toLowerCase()) ||
                    book.getGenre().toLowerCase().contains(query.toLowerCase())) {
                    results.add(book);
                }
            }
        }
        Object[][] data = new Object[results.size()][5];
        
        rowToBookMap.clear();
        for (int i = 0; i < results.size(); i++) {
        	Book b = results.get(i);
            rowToBookMap.put(i, b);
            data[i][0] = b.getTitle();
            data[i][1] = b.getAuthor();
            data[i][2] = b.getISBN();
            data[i][3] = b.getGenre();
            data[i][4] = b.getQuantity();
        }
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(58, 62, 329, 132);
        contentPane.add(scrollPane);
        txtSearch.setText("");
    }

    public void showLendingRecord() {
        if (this.table == null) {
            JOptionPane.showMessageDialog(this, "Please select a book to view lending records.");
        } else {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Book selectedBook = rowToBookMap.get(selectedRow); // Use the map to get the selected book
                if (selectedBook != null) {
                    ArrayList<BorrowRecord> borrowRecordsForBook = new ArrayList<>();
                    for (BorrowRecord record : BorrowRecordList.getBorrowedRecord()) {
                        if (record.getBorrowedBook().equals(selectedBook)) {
                            borrowRecordsForBook.add(record);
                        }
                    }
                    if (!borrowRecordsForBook.isEmpty()) {
                        new SpecificBorrowRecordGUI(borrowRecordsForBook).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "No lending records found for this book.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a valid book to view lending records.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a book to view lending records.");
            }
        }
    }


    public void cancel() {
        dispose();
    }

    public void reset() {
        contentPane.remove(scrollPane);
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(58, 62, 329, 132);
        contentPane.add(scrollPane);
        txtSearch.setText("");
    }

    public void lendBook(Book selectedBook) {
        BorrowBookGUI borrowBookGUI = new BorrowBookGUI(this, selectedBook);
        borrowBookGUI.setVisible(true);
        search(txtSearch.getText());
    }
}
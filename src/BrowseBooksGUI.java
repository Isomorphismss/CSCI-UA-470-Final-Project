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

public class BrowseBooksGUI extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    public BrowseBooksGUI() throws IOException {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        displayAllBooks(Book.getBooksInventory());

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        deleteBook(selectedRow);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(BrowseBooksGUI.this, "Please select a row first.");
                }
            }
        });
        btnDelete.setBounds(54, 211, 93, 23);
        contentPane.add(btnDelete);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        updateBook(selectedRow);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(BrowseBooksGUI.this, "Please select a row first.");
                }
            }
        });
        btnUpdate.setBounds(174, 211, 93, 23);
        contentPane.add(btnUpdate);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        btnClose.setBounds(296, 211, 93, 23);
        contentPane.add(btnClose);
    }

    public void close() {
        dispose();
    }

    public void displayAllBooks(ArrayList<Book> books) {
        String[] columnNames = {"Title", "Author", "ISBN", "Genre", "Quantity"};
        Object[][] data = new Object[books.size()][5];
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            data[i][0] = book.getTitle();
            data[i][1] = book.getAuthor();
            data[i][2] = book.getISBN();
            data[i][3] = book.getGenre();
            data[i][4] = book.getQuantity();
        }
        if (tableModel == null) {
            tableModel = new DefaultTableModel(data, columnNames);
            table = new JTable(tableModel) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(54, 40, 333, 139);
            contentPane.add(scrollPane);
        } else {
            tableModel.setDataVector(data, columnNames);
        }
    }

    public void deleteBook(int index) throws IOException {
        Book.getBooksInventory().remove(index);
        displayAllBooks(Book.getBooksInventory()); // Refresh the table model directly
        Book.saveData();
    }

    public void updateBook(int index) throws IOException {
        Book selectedBook = Book.getBooksInventory().get(index);
        UpdateBookGUI updateBookGUI = new UpdateBookGUI(this, selectedBook, index);
        updateBookGUI.setVisible(true);
        displayAllBooks(Book.getBooksInventory());
    }
}

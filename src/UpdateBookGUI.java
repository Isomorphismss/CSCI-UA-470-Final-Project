import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UpdateBookGUI extends JDialog {
    private JPanel contentPane;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtISBN;
    private JTextField txtGenre;
    private JTextField txtQuantity;
    private JButton btnSave;
    private JButton btnCancel;
    private Book book;
    private int bookIndex;

    public UpdateBookGUI(Frame parent, Book book, int index){
        super(parent, "Update Book", true);

        this.book = book;
        this.bookIndex = index;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(62, 40, 79, 15);
        contentPane.add(lblTitle);

        JLabel lblAuthor = new JLabel("Author:");
        lblAuthor.setBounds(62, 70, 79, 15);
        contentPane.add(lblAuthor);

        JLabel lblISBN = new JLabel("ISBN:");
        lblISBN.setBounds(62, 100, 79, 15);
        contentPane.add(lblISBN);

        JLabel lblGenre = new JLabel("Genre:");
        lblGenre.setBounds(62, 130, 79, 15);
        contentPane.add(lblGenre);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(62, 160, 79, 15);
        contentPane.add(lblQuantity);

        txtTitle = new JTextField(book.getTitle());
        txtTitle.setBounds(158, 37, 187, 21);
        contentPane.add(txtTitle);

        txtAuthor = new JTextField(book.getAuthor());
        txtAuthor.setBounds(158, 67, 187, 21);
        contentPane.add(txtAuthor);

        txtISBN = new JTextField(book.getISBN());
        txtISBN.setBounds(158, 97, 187, 21);
        contentPane.add(txtISBN);

        txtGenre = new JTextField(book.getGenre());
        txtGenre.setBounds(158, 127, 187, 21);
        contentPane.add(txtGenre);

        txtQuantity = new JTextField(String.valueOf(book.getQuantity()));
        txtQuantity.setBounds(158, 157, 187, 21);
        contentPane.add(txtQuantity);

        btnSave = new JButton("Save");
        btnSave.setBounds(62, 206, 93, 23);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveBook();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        contentPane.add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(252, 206, 93, 23);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnCancel);
    }

    private void saveBook() throws IOException {
        book.setTitle(txtTitle.getText());
        book.setAuthor(txtAuthor.getText());
        book.setISBN(txtISBN.getText());
        book.setGenre(txtGenre.getText());
        try {
            book.setQuantity(Integer.parseInt(txtQuantity.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.");
            return;
        }
        Book.getBooksInventory().set(bookIndex, book);
        Book.saveData();
        JOptionPane.showMessageDialog(this, "Book updated successfully.");
        dispose();
    }
}

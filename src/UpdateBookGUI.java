import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UpdateBookGUI extends JDialog {
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

        setTitle("Update Book");
        setBounds(100, 100, 450, 300);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 2));
        add(panel, BorderLayout.CENTER);

        panel.add(new JLabel("Title:"));
        txtTitle = new JTextField(book.getTitle());
        panel.add(txtTitle);

        panel.add(new JLabel("Author:"));
        txtAuthor = new JTextField(book.getAuthor());
        panel.add(txtAuthor);

        panel.add(new JLabel("ISBN:"));
        txtISBN = new JTextField(book.getISBN());
        panel.add(txtISBN);

        panel.add(new JLabel("Genre:"));
        txtGenre = new JTextField(book.getGenre());
        panel.add(txtGenre);

        panel.add(new JLabel("Quantity:"));
        txtQuantity = new JTextField(String.valueOf(book.getQuantity()));
        panel.add(txtQuantity);

        btnSave = new JButton("Save");
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
        panel.add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(btnCancel);
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

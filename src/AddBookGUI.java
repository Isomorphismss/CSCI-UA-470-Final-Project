import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AddBookGUI extends JFrame {
    private JPanel contentPane;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtISBN;
    private JTextField txtGenre;
    private JTextField txtQuantity;
    private JButton btnSave;
    private JButton btnReset;
    private JButton btnCancel;
    private JLabel lblTitle;
    private JLabel lblAuthor;
    private JLabel lblISBN;
    private JLabel lblGenre;
    private JLabel lblQuantity;

    public AddBookGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    saveBook();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnSave.setBounds(62, 206, 93, 23);
        contentPane.add(btnSave);
        
        btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
        btnReset.setBounds(158, 206, 93, 23);
        contentPane.add(btnReset);
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        btnCancel.setBounds(252, 206, 93, 23);
        contentPane.add(btnCancel);
        
        lblTitle = new JLabel("Title:");
        lblTitle.setBounds(62, 40, 79, 15);
        contentPane.add(lblTitle);
        
        lblAuthor = new JLabel("Author:");
        lblAuthor.setBounds(62, 70, 79, 15);
        contentPane.add(lblAuthor);

        lblISBN = new JLabel("ISBN:");
        lblISBN.setBounds(62, 100, 79, 15);
        contentPane.add(lblISBN);

        lblGenre = new JLabel("Genre:");
        lblGenre.setBounds(62, 130, 79, 15);
        contentPane.add(lblGenre);

        lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(62, 160, 79, 15);
        contentPane.add(lblQuantity);
        
        txtTitle = new JTextField();
        txtTitle.setBounds(158, 37, 187, 21);
        contentPane.add(txtTitle);
        txtTitle.setColumns(10);
        
        txtAuthor = new JTextField();
        txtAuthor.setBounds(158, 67, 187, 21);
        contentPane.add(txtAuthor);
        txtAuthor.setColumns(10);
        
        txtISBN = new JTextField();
        txtISBN.setBounds(158, 97, 187, 21);
        contentPane.add(txtISBN);
        txtISBN.setColumns(10);

        txtGenre = new JTextField();
        txtGenre.setBounds(158, 127, 187, 21);
        contentPane.add(txtGenre);
        txtGenre.setColumns(10);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(158, 157, 187, 21);
        contentPane.add(txtQuantity);
        txtQuantity.setColumns(10);
    }

    private void saveBook() throws IOException {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String isbn = txtISBN.getText();
        String genre = txtGenre.getText();
        int quantity;

        if(title.isEmpty() || author.isEmpty() || isbn.isEmpty() || genre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
        } else {
            try {
                quantity = Integer.parseInt(txtQuantity.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid quantity.");
                return;
            }
            BookList.getBooksInventory().add(new Book(title, author, isbn, genre, quantity));
            BookFileManager.saveData();
            JOptionPane.showMessageDialog(this, "Book added successfully.");
            resetFields();
        }
    }

    private void cancel() {
        dispose();
    }

    private void resetFields() {
        txtTitle.setText("");
        txtAuthor.setText("");
        txtISBN.setText("");
        txtGenre.setText("");
        txtQuantity.setText("");
    }
}
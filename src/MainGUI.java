import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainGUI extends JFrame {

    private JPanel contentPane;
    private JLabel lblWelcome;
    private JButton btnAdd;
    private JButton btnSearch;
    private JButton btnBrowse;

    public MainGUI() throws ClassNotFoundException, IOException {
        Book.loadData(); 
        setTitle("Library Inventory System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblWelcome = new JLabel("Welcome to the Library Inventory System");
        lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 14)); 
        lblWelcome.setBounds(68, 5, 350, 20); 
        contentPane.add(lblWelcome);

        btnAdd = new JButton("Add a Book");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showAddBook();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnAdd.setBounds(120, 50, 200, 40); 
        contentPane.add(btnAdd);
        
        btnSearch = new JButton("Search For a Book");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showSearchBook();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnSearch.setBounds(120, 110, 200, 40); 
        contentPane.add(btnSearch);
        
        btnBrowse = new JButton("Browse All Books");
        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showBrowseBooks();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnBrowse.setBounds(120, 170, 200, 40); 
        contentPane.add(btnBrowse);
    }

    public void showBrowseBooks() throws IOException {
        BrowseBooksGUI browseGUI = new BrowseBooksGUI();
        browseGUI.setVisible(true);
    }

    public void showAddBook() throws IOException {
        AddBookGUI addBookGUI = new AddBookGUI();
        addBookGUI.setVisible(true);
    }

    public void showSearchBook() throws IOException {
        SearchBookGUI searchBookGUI = new SearchBookGUI();
        searchBookGUI.setVisible(true);
    }
}

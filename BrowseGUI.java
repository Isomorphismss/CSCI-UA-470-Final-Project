
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class BrowseGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	
	public BrowseGUI() throws IOException {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		displayall(Contact.getContactsBook());
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = table.getSelectedRow();
				if (a!=-1) {
					try {
						delete(a);
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		btnDelete.setBounds(54, 211, 93, 23);
		contentPane.add(btnDelete);
		
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
	public void displayall(ArrayList<Contact>contact) throws IOException {
		String[] columnNames = {"First Name", "Last Name", "Phone"};
		Object[][]o=new Object[contact.size()][3];
		int i=0;
		for(Contact c: contact) {
			o[i][0]=c.getFirstName();
			o[i][1]=c.getLastName();
			o[i][2]=c.getPhone();
			i++;
		}
		tableModel=new DefaultTableModel(o,columnNames);
		table=new JTable(tableModel) {
			 public boolean isCellEditable(int row, int column) {                
			        return false;               
			    };
		};
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(54, 40, 333, 139);
		
		contentPane.add(scrollPane);
		Contact.saveData();
		
	}
	public void delete(int n) throws IOException {
		Contact.getContactsBook().remove(n);
		contentPane.remove(scrollPane);
		displayall(Contact.getContactsBook());
		Contact.saveData();
	}

}

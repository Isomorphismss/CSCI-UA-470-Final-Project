import java.awt.BorderLayout;

import java.awt.EventQueue;

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

public class SearchGUI extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JButton btnReset;
	private JButton btncancel;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel tableModel;
	public SearchGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a=txtSearch.getText();
				search(a);
				
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
		
		btncancel = new JButton("Cancel");
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btncancel.setBounds(297, 204, 93, 23);
		contentPane.add(btncancel);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(58, 28, 321, 21);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 62, 329, 132);
		contentPane.add(scrollPane);
	}
	public void search(String b) {
		if(b.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Please enter a search query.");
		}
		else {
			contentPane.remove(scrollPane);
			String[] columnNames = {"First Name", "Last Name", "Phone"};
			int n=0;
			for(Contact c:Contact.getContactsBook()) {
				if(c.getFirstName().toLowerCase().contains(b.toLowerCase())||c.getLastName().toLowerCase().contains(b.toLowerCase())||c.getPhone().toLowerCase().contains(b.toLowerCase())) {
					n+=1;
				}
			}
			Object[][]o=new Object[n][3];
			int h=0;
			for(Contact c:Contact.getContactsBook()) {
				if(c.getFirstName().toLowerCase().contains(b.toLowerCase())||c.getLastName().toLowerCase().contains(b.toLowerCase())||c.getPhone().toLowerCase().contains(b.toLowerCase())) {
					o[h][0]=c.getFirstName();
					o[h][1]=c.getLastName();
					o[h][2]=c.getPhone();
					h++;
				}
			}
			tableModel=new DefaultTableModel(o,columnNames);
			table=new JTable(tableModel) {
				public boolean isCellEditable(int row, int column) {                
		        return false;               
		    };
	};
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(58, 62, 329, 132);
			contentPane.add(scrollPane);
			txtSearch.setText("");
	}
		
	}
	public void cancel() {
		dispose();
	}
	public void reset() {
		contentPane.remove(scrollPane);
		tableModel=new DefaultTableModel();
		table=new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(58, 62, 329, 132);
		contentPane.add(scrollPane);
		txtSearch.setText("");
		
	}

}

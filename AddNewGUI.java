import java.awt.BorderLayout;
import java.awt.EventQueue;

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

public class AddNewGUI extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField fn;
	private JTextField ln;
	private JTextField pn;
	private JButton btnSave;
	private JButton btnReset;
	private JButton btnCancel;
	private JLabel lblfn;
	private JLabel lblln;
	private JLabel lblp;
	public AddNewGUI() {
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
					save();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(62, 206, 93, 23);
		contentPane.add(btnSave);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
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
		
		lblfn = new JLabel("First Name:");
		lblfn.setBounds(62, 40, 79, 15);
		contentPane.add(lblfn);
		
		lblln = new JLabel("Last Name:");
		lblln.setBounds(62, 88, 79, 15);
		contentPane.add(lblln);
		
		lblp = new JLabel("Phone Number:");
		lblp.setBounds(62, 140, 93, 15);
		contentPane.add(lblp);
		
		fn = new JTextField();
		fn.setBounds(158, 37, 187, 21);
		contentPane.add(fn);
		fn.setColumns(10);
		
		ln = new JTextField();
		ln.setBounds(158, 85, 187, 21);
		contentPane.add(ln);
		ln.setColumns(10);
		
		pn = new JTextField();
		pn.setBounds(158, 137, 187, 21);
		contentPane.add(pn);
		pn.setColumns(10);
	}
	public void save() throws IOException {
		String a=fn.getText();
		String b=ln.getText();
		String c=pn.getText();
		if(a.isEmpty()||b.isEmpty()||c.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Please fill all fields.");
		}
		else {
			Contact.getContactsBook().add(new Contact(a,b,c));
			Contact.saveData();
			JOptionPane.showMessageDialog(frame, "Contact added successfully.");
			reset();
		}
	}
	public void cancel() {
		dispose();
	}
	public void reset() {
		fn.setText("");
		ln.setText("");
		pn.setText("");
	}
}

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnAdd;
	private JButton btnSearch;
	private JButton btnBrowse;

	public MainGUI() throws ClassNotFoundException, IOException {
		Contact.loadData();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					show_Add();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(175, 32, 93, 23);
		contentPane.add(btnAdd);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					show_Search();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(175, 93, 93, 23);
		contentPane.add(btnSearch);
		
		btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					show_browse();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnBrowse.setBounds(175, 152, 93, 23);
		contentPane.add(btnBrowse);
	}
	public void show_browse() throws IOException {
		BrowseGUI b=new BrowseGUI();
		b.show();
	}
	public void show_Add() throws IOException {
		AddNewGUI a=new AddNewGUI();
		a.show();
	}
	public void show_Search() throws IOException {
		SearchGUI s=new SearchGUI();
		s.show();
	}

}

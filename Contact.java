import java.io.*;
import java.util.ArrayList;

public class Contact implements Serializable {
	 private String firstName;
	 private String lastName;
	 private String phone;
	 private static ArrayList<Contact> contactsBook = new ArrayList<>();
	 public Contact(String firstName, String lastName, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
	}
	public static ArrayList<Contact> getContactsBook() {
		return contactsBook;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public static void setContactsBook(ArrayList<Contact> contactsBook) {
		Contact.contactsBook = contactsBook;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone() {
		return phone;
	}
	
	@SuppressWarnings("unchecked")
	public static void loadData() throws IOException, ClassNotFoundException {
		
        		File file = new File("contacts.bin");
        		if(file.exists()) {
	           		FileInputStream fileInputStream = new FileInputStream("contacts.bin");
	                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	                Contact.setContactsBook((ArrayList<Contact>) objectInputStream.readObject());
	                objectInputStream.close();
	               
	        		}
            
    }
	
    public static void saveData() throws IOException {
        
           		FileOutputStream fileOutputStream = new FileOutputStream("contacts.bin");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(Contact.getContactsBook());
                objectOutputStream.close();
                
            } 
	
	 
}

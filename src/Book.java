import java.io.*;
import java.util.ArrayList;

public class Book implements Serializable {
    private String title;
    private String author;
    private String ISBN;
    private String genre;
    private int quantity;
    private static ArrayList<Book> booksInventory = new ArrayList<>();

    public Book(String title, String author, String ISBN, String genre, int quantity) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.quantity = quantity;
    }

    public static ArrayList<Book> getBooksInventory() {
        return booksInventory;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public static void setBooksInventory(ArrayList<Book> booksInventory) {
        Book.booksInventory = booksInventory;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Ensure it's the same book regardless of memory location
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return ISBN != null ? ISBN.equals(book.ISBN) : book.ISBN == null;
    }

    public int hashCode() {
        return ISBN != null ? ISBN.hashCode() : 0;
    }

    @SuppressWarnings("unchecked")
    public static void loadData() throws IOException, ClassNotFoundException {
        File file = new File("booksInventory.bin");
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream("booksInventory.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Book.setBooksInventory((ArrayList<Book>) objectInputStream.readObject());
            objectInputStream.close();
        }
    }

    public static void saveData() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("booksInventory.bin");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(Book.getBooksInventory());
        objectOutputStream.close();
    }
}
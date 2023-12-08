import java.util.ArrayList;

public class BookList {
	private static ArrayList<Book> booksInventory = new ArrayList<>();

	public static ArrayList<Book> getBooksInventory() {
		return booksInventory;
	}

	public static void setBooksInventory(ArrayList<Book> booksInventory) {
		BookList.booksInventory = booksInventory;
	}

}

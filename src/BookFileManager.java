import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class BookFileManager  {
	@SuppressWarnings("unchecked")
    public static void loadData() throws IOException, ClassNotFoundException {
        File file = new File("booksInventory.bin");
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream("booksInventory.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            BookList.setBooksInventory((ArrayList<Book>) objectInputStream.readObject());
            objectInputStream.close();
        }
    }

    public static void saveData() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("booksInventory.bin");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(BookList.getBooksInventory());
        objectOutputStream.close();
    }
}

import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.IOException;

public class ChatList {
    private final ArrayList<ListItem> list;

    ChatList() {
         this.list = new ArrayList<ListItem>(100);
    }

    ChatList(ArrayList<ListItem> list) {
        this.list = list;
    }

    /**
     * Reads new ChatList from save file.
     *
     * @param filePath relative path to save file, as a Path object
     * @return a new ChatList containing the data from the save file, or an empty ChatList if file does nto exist
     * @throws IOException
     */
    public static ChatList readFromFile(Path filePath) throws IOException {
        ChatList list = new ChatList();

        if (Files.exists(filePath)) {
            try (Stream<String> lines = Files.lines(filePath)){
                lines.forEach(line -> list.addItem(ListItem.parseLine(line)));
            }
        }

        return list;
    }

    /**
     * Write current ChatList to a save file.
     * If file already exists, will overwrite current contents.
     * If file and/or directory does not exist, will create a new one.
     *
     * @param filePath relative path to save file, as a Path object
     * @throws IOException
     */
    public void writeToFile(Path filePath) throws IOException {
        String toWrite = this.list.stream().map(i -> i.toFile()).collect(Collectors.joining("\n"));

        Files.createDirectories(filePath.getParent()); //create directory structure (does nothing if already exists)
        Files.writeString(filePath, toWrite); //write to file (creates file if does not exist)
    }

    /**
     * Adds item to the end of the list.
     *
     * @param item: ListItem object, representing a to-do, event or deadline in the list.
     */
    public void addItem(ListItem item) {
        list.add(item);
    }

    /**
     * marks existing item as completed.
     *
     * @param n: position of item in the list (starting with 1).
     * @return: marked list item, in the form of a String.
     */
    public String markItem(int n) {
        ListItem item = list.get(n - 1);
        item.mark();
        return item.toString();
    }

    /**
     * Unmarks existing item, then return it as a String
     *
     * @param n: position of item in the list (starting with 1)
     * @return: unmarked list item, in the form of a String.
     */
    public String unmarkItem(int n) {
        ListItem item = list.get(n - 1);
        item.unmark();
        return item.toString();
    }

    /**
     * Deletes item from the list
     *
     * @param n: position of item in the list (starting with 1)
     * @return: deleted list item, in the form of a String.
     */
    public String deleteItem(int n) {
        ListItem item = list.get(n - 1);
        list.remove(n - 1);
        return item.toString();
    }

    /**
     * get length of the list
     */
    public int getLength() {
        return list.size();
    }

    @Override
    public String toString() {
        String res = "";
        for(int i = 1; i <= list.size(); i++) {
            res += (i + ". ");
            res += list.get(i - 1);
            if (i < list.size()) {
                res += "\n";
            }
        }

        return res;
    }
}

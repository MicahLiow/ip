package orangutan.chatlist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stores listItems of ListItems, and provides functionality to add, modify and remove them.
 */
public class ChatList {
    private final ArrayList<ListItem> listItems;

    /**
     * Creates an empty ChatList.
     */
    public ChatList() {
        listItems = new ArrayList<ListItem>(100);
    }

    /**
     * Creates a new ChatList from existing data.
     *
     * @param listItems ArrayList of ListItems to be stored in this listItems.
     */
    public ChatList(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

    /**
     * Returns the listItems as a stream of ListItems.
     */
    public Stream<ListItem> toStream() {
        return listItems.stream();
    }

    /**
     * Adds item to the end of the listItems.
     *
     * @param item ListItem object, representing a to-do, event or deadline in the listItems.
     */
    public void addItem(ListItem item) {
        listItems.add(item);
    }

    /**
     * Marks existing item as completed.
     *
     * @param index Position of item in the listItems (starting with 1).
     * @return Marked listItems item, in the form of a String.
     */
    public String markItem(int index) {
        ListItem item = listItems.get(index - 1);
        item.mark();
        return item.toString();
    }

    /**
     * Unmarks existing item, then return it as a String.
     *
     * @param index Position of item in the listItems (starting with 1).
     * @return Unmarked listItems item, in the form of a String.
     */
    public String unmarkItem(int index) {
        ListItem item = listItems.get(index - 1);
        item.unmark();
        return item.toString();
    }

    /**
     * Deletes item from the listItems.
     *
     * @param index Position of item in the listItems (starting with 1).
     * @return Deleted listItems item, in the form of a String.
     */
    public String deleteItem(int index) {
        ListItem item = listItems.get(index - 1);
        listItems.remove(index - 1);
        return item.toString();
    }

    /**
     * Finds items whose descriptions contain query.
     *
     * @param query String to be found.
     * @return ChatList of matching items.
     */
    public ChatList findItem(String query) {
        List<ListItem> matchStream = listItems.stream().filter(item -> item.getItem().contains(query)).toList();
        return new ChatList(new ArrayList<ListItem>(matchStream));
    }

    /**
     * Gets length of the listItems.
     */
    public int getLength() {
        return listItems.size();
    }

    /**
     * Prints out an enumerated listItems of items.
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 1; i <= listItems.size(); i++) {
            res += (i + ". ");
            res += listItems.get(i - 1);
            if (i < listItems.size()) {
                res += "\n";
            }
        }

        return res;
    }
}

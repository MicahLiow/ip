package orangutan.chatlist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ChatList {
    private final ArrayList<ListItem> list;

    public ChatList() {
         this.list = new ArrayList<ListItem>(100);
    }

    public ChatList(ArrayList<ListItem> list) {
        this.list = list;
    }

    /**
     * returns the list as a stream of ListItems.
     */
    public Stream<ListItem> toStream() {
        return list.stream();
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
     * Finds items whose descriptions contain query.
     *
     * @param query String to be found.
     * @return ChatList of matching items.
     */
    public ChatList findItem(String query) {
        List<ListItem> resStream = list.stream().filter(item -> item.getItem().contains(query)).toList();
        return new ChatList(new ArrayList<ListItem>(resStream));
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
import java.util.ArrayList;

public class ChatList {
    private final ArrayList<ListItem> list = new ArrayList<ListItem>(100);

    ChatList() {}

    public void addItem(ListItem item) {
        list.add(item);
    }

    public String markItem(int n) {
        ListItem item = list.get(n-1);
        item.mark();
        return item.toString();
    }

    public String unmarkItem(int n) {
        ListItem item = list.get(n-1);
        item.unmark();
        return item.toString();
    }

    @Override
    public String toString() {
        String res = "";
        for(int i = 1; i <= list.size(); i++) {
            res += (i + ". ");
            res += list.get(i-1);
            if (i < list.size()) {res += "\n";}
        }

        return res;
    }
}

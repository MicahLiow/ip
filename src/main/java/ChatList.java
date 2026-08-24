import java.util.ArrayList;

public class ChatList {
    private ArrayList<String> list = new ArrayList<String>(100);

    ChatList() {}

    public void addItem(String s) {
        list.add(s);
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

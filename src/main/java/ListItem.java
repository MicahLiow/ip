public class ListItem {
    private final String item;
    private boolean isCompleted;

    ListItem(String s) {
        this.item = s;
        this.isCompleted = false;
    }

    public void mark() {
        this.isCompleted = true;
    }

    public void unmark() {
        this.isCompleted = false;
    }

    public String toString() {
        String res = "";
        res += this.isCompleted ? "[X] " : "[ ] ";
        res += item;

        return res;
    }
}

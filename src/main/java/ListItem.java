public class ListItem {
    private final String item;
    private boolean isCompleted;
    protected String icon; //protected and not final so that children can have different icons

    ListItem(String item) {
        this.item = item;
        this.isCompleted = false;
        this.icon = "   ";
    }

    public void mark() {
        this.isCompleted = true;
    }

    public void unmark() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        String res = "";
        res += icon + " ";
        res += this.isCompleted ? "[X] " : "[ ] ";
        res += item;

        return res;
    }
}

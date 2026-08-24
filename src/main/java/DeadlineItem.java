public class DeadlineItem extends ListItem{
    private final String by;

    DeadlineItem(String item, String by) {
        super(item);
        this.icon = "[D]";

        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}

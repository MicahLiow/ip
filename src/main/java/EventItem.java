public class EventItem extends ListItem{
    private final String from;
    private final String to;

    EventItem(String item, String from, String to) {
        super(item);
        this.icon = "[E]";

        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to " + to + ")";
    }
}

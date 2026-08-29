import java.util.Arrays;
import java.util.stream.Stream;

public class ListItem {
    private final ItemType type;
    private final String item;
    private boolean isCompleted; //not final so we can mark and unmark a task
    private final String from;
    private final String to;
    private final String by;

    /**
     * constructs a ListItem.
     *
     * @param type the type of item, can be TODO, DEADLINE or EVENT
     * @param item the description of the list item (e.g. dinner)
     * @param isCompleted whether the item has already been completed
     * @param params any other relevant String parameters
     *     TODO: no extra params. DEADLINE: param "by". EVENT: params "from", "to"
     */
    ListItem(ItemType type, String item, boolean isCompleted, String...params) {
        this.type = type;
        this.item = item;
        this.isCompleted = isCompleted;

        switch (type) { //correct number of parameters is checked by the calling class
            case TODO:
                this.from = null;
                this.to = null;
                this.by = null;
                break;
            case DEADLINE:
                this.from = null;
                this.to = null;
                this.by = params[0];
                break;
            case EVENT:
                this.from = params[0];
                this.to = params[1];
                this.by = null;
                break;
            default:
                this.from = null;
                this.to = null;
                this.by = null;
                break;
        }
    }

    }

    /**
     * Marks item as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * unmarks item as to be completed.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Returns a comma-separated string representation of the data in this object
     * to be written to a file
     *
     * @return "[icon],[isCompleted (1 or 0)],[item name]" (e.g. "   ,1,Dinner")
     */
    public String toFile() {
        return String.format("%s,%d,%s", this.icon, this.isCompleted ? 1 : 0, this.item);
    }

    /**
     * Returns item parameters (by, from, to) as a String for toString().
     *
     * @param paramName parameter name ("by", "from", "to")
     * @param param parameter value (e.g. "Monday 5pm")
     * @param prefix any String to be added to the beginning of the result
     * @param suffix any String to be added after the end of the result
     * @return null if the parameter is of null value,
     *     else a String of format "prefix paramName: param suffix"
     *     (e.g. "(by: Monday 5pm)", where prefix is "(" and suffix is ")")
     */
    private static String paramToString(String paramName, String param, String prefix, String suffix) {
        return param == null ? null : prefix + paramName + " " + param + suffix;
    }

    /**
     * Prints this item as a String
     *
     * @return String of format "[icon] [isCompleted ([X] or [ ])] item, (paramName: paramValue)"
     *     (e.g. "[T] [X] Dinner (from: 6pm to: 7pm)")
     */
    @Override
    public String toString() {
        String icon = this.type.icon;
        String isCompleted = this.isCompleted ? "[X]" : "[ ]";
        String item = this.item;
        String by = paramToString("by", this.by, "(", ")");
        String from = paramToString("from", this.from, "(", "");
        String to = paramToString("to", this.to, "", ")");

        return Stream.of(icon, isCompleted, item, by, from, to)
                .filter(s -> s != null)
                .reduce("", (s, t) -> s + " " + t);
    }
}

import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class ListItem {
    private final static DateTimeFormatter DATE_TIME_INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
    private final static DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ItemType type;
    private final String item;
    private boolean isCompleted; //not final so we can mark and unmark a task
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final LocalDateTime by;

    /**
     * constructs a ListItem.
     *
     * @param type the type of item, can be TODO, DEADLINE or EVENT
     * @param item the description of the list item (e.g. dinner)
     * @param isCompleted whether the item has already been completed
     * @param params other String parameters, formatted "yyyy-MM-dd HH:mm" (e.g. "2026-08-31 15:00")
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
                this.by = LocalDateTime.parse(params[0], DATE_TIME_INPUT_FORMATTER);
                break;
            case EVENT:
                this.from = LocalDateTime.parse(params[0], DATE_TIME_INPUT_FORMATTER);
                this.to = LocalDateTime.parse(params[1], DATE_TIME_INPUT_FORMATTER);
                this.by = null;
                break;
            default:
                this.from = null;
                this.to = null;
                this.by = null;
                break;
        }
    }

    /**
     * Constructs an EventItem from a comma-separated string
     *
     * @param fromFileLine comma-separated string
     *     "[icon],[isCompleted],[item name],[from / by],[to]"
     *     (e.g. "[E],true,Dinner,2026-08-31 1700,2026-08-31 1900")
     */
    public static ListItem parseLine(String fromFileLine) {
        String[] data = fromFileLine.split(",");

        ItemType type = ItemType.getTypeFromIcon(data[0]);
        boolean isCompleted = Boolean.parseBoolean(data[1]);
        String item = data[2];

        String[] params = {};
        if (data.length > 3) { //correct number of parameters is checked by the calling class
            params = Arrays.copyOfRange(data, 3, 5);
        }

        return new ListItem(type, item, isCompleted, params);
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
     * @return "[icon],[isCompleted],[item name]" (e.g. "   ,true,Dinner")
     */
    public String toFile() {
        String icon = this.type.icon;
        String isCompleted = Boolean.toString(this.isCompleted);

        //here we use the input formatter because these lines need to be parsed in the future
        String by = paramToString("", this.by, "", "", DATE_TIME_INPUT_FORMATTER);
        String from = paramToString("", this.from, "", "", DATE_TIME_INPUT_FORMATTER);
        String to = paramToString("", this.to, "", "", DATE_TIME_INPUT_FORMATTER);
        return Stream.of(icon, isCompleted, this.item, by, from, to)
                .filter(s -> s != null)
                .collect(Collectors.joining(","));
    }

    /**
     * Returns item parameters (by, from, to) as a String
     *
     * @param paramName parameter name ("by", "from", "to")
     * @param param parameter value (e.g. "Monday 5pm")
     * @param prefix any String to be added to the beginning of the result
     * @param suffix any String to be added after the end of the result
     * @param formatter format the param to this
     * @return null if the parameter is of null value,
     *     else a String of format "prefix paramName param suffix"
     *     (e.g. "(by Monday 5pm)", prefix is "(", suffix is ")" and paramName is "by ")
     */
    private static String paramToString(String paramName, LocalDateTime param, String prefix, String suffix,
            DateTimeFormatter formatter) {
        return param == null ? null : prefix + paramName + param.format(formatter) + suffix;
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
        String by = paramToString("by ", this.by, "(", ")", DATE_TIME_OUTPUT_FORMATTER);
        String from = paramToString("from ", this.from, "(", "", DATE_TIME_OUTPUT_FORMATTER);
        String to = paramToString("to ", this.to, "", ")", DATE_TIME_OUTPUT_FORMATTER);

        return Stream.of(icon, isCompleted, item, by, from, to)
                .filter(s -> s != null)
                .collect(Collectors.joining(" "));
    }
}

package orangutan.command;

import orangutan.chatlist.ItemType;
import orangutan.chatlist.ListItem;

import java.time.format.DateTimeParseException;

/**
 * Command to create a new deadline and add it to the list.
 */
class OrangutanDeadlineCommand implements OrangutanCommand {
    private final String item;
    private final String by;
    private final boolean isCompleted;

    /**
     * Creates new command.
     *
     * @param item Description of the list item.
     * @param by Date and time of the deadline, of format "yyyyddmm hhmm".
     * @param isCompleted Whether the deadline has been completed or not.
     */
    OrangutanDeadlineCommand(String item, String by, boolean isCompleted) {
        this.item = item;
        this.by = by;
        this.isCompleted = isCompleted;
    }

    /**
     * Creates deadline and appends it to the list.
     *
     * @param context OrangutanContext item storing information on the chatbot's current internal state.
     * @return Reply message, plus a printout of the new deadline.
     *      If date and time are of the wrong format, will instead return an alert message.
     */
    public String run(OrangutanContext context) {
        try {
            ListItem newDeadline = new ListItem(ItemType.DEADLINE, item, isCompleted, by);
            context.getList().addItem(newDeadline);

            return ("A deadline has been added.\n " + newDeadline);
        } catch (DateTimeParseException e) {
            return ("Alas! I do not comprehend the dates and times you have told me.\n\n"
                    + "Please ensure your dates are of format yyyymmdd hhmm (e.g. 20260831 2359)");
        }
    }
}

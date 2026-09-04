package orangutan.command;

import java.time.format.DateTimeParseException;

import orangutan.chatlist.ItemType;
import orangutan.chatlist.ListItem;


/**
 * Command to create a new event and add it to the list.
 */
class OrangutanEventCommand implements OrangutanCommand {
    private final String item;
    private final String from;
    private final String to;
    private final boolean isCompleted;

    /**
     * Creates a new command.
     *
     * @param item Description of the list item.
     * @param from Start date and time of the event, of format "yyyymmdd hhmm".
     * @param to End date and time of the event, of format "yyyymmdd hhmm".
     * @param isCompleted Whether the event has passed or not.
     */
    OrangutanEventCommand(String item, String from, String to, boolean isCompleted) {
        this.item = item;
        this.from = from;
        this.to = to;
        this.isCompleted = isCompleted;
    }

    /**
     * Creates an event and appends it to the list.
     *
     * @param context OrangutanContext item storing information on the chatbot's current internal state.
     * @return Reply message, plus a printout of the new deadline.
     *      If date and time are of the wrong format, will instead return an alert message.
     */
    public String run(OrangutanContext context) {
        try {
            ListItem newEvent = new ListItem(ItemType.EVENT, item, isCompleted, from, to);
            context.getList().addItem(newEvent);

            return ("An event has been added.\n " + newEvent);
        } catch (DateTimeParseException e) {
            return ("Alas! I do not comprehend the dates and times you have told me.\n\n"
                    + "Please ensure your dates are of format yyyymmdd hhmm (e.g. 20260831 2359)");
        }
    }
}

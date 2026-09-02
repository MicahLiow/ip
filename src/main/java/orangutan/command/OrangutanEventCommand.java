package orangutan.command;

import orangutan.chatlist.ItemType;
import orangutan.chatlist.ListItem;

import java.time.format.DateTimeParseException;

class OrangutanEventCommand implements OrangutanCommand {
    private final String item;
    private final String from;
    private final String to;
    private final boolean isCompleted;

    OrangutanEventCommand(String item, String from, String to, boolean isCompleted) {
        this.item = item;
        this.from = from;
        this.to = to;
        this.isCompleted = isCompleted;
    }

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

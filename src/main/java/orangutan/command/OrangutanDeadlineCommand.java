package orangutan.command;

import orangutan.chatlist.ItemType;
import orangutan.chatlist.ListItem;

import java.time.format.DateTimeParseException;

class OrangutanDeadlineCommand implements OrangutanCommand {
    private final String item;
    private final String by;
    private final boolean isCompleted;

    OrangutanDeadlineCommand(String item, String by, boolean isCompleted) {
        this.item = item;
        this.by = by;
        this.isCompleted = isCompleted;
    }

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

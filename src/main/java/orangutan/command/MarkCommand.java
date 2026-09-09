package orangutan.command;

import orangutan.OrangutanException;

/**
 * Command to mark an item as completed.
 */
class MarkCommand implements Command {
    private static final String NAME = "mark";
    private final String index;

    /**
     * Creates a new command.
     *
     * @param index Index of the item to be marked.
     */
    MarkCommand(String index) {
        this.index = index;
    }

    /**
     * Marks item at the given index of the list.
     *
     * @param context Context item storing information on the chatbot's current internal state.
     * @return Reply message, plus a printout of the marked item.
     *      If index is not a number, will instead return an alert message.
     * @throws OrangutanException If list is empty, or if index is out of range.
     */
    public String run(Context context) throws OrangutanException {
        ErrorChecker.checkListEmpty(context, NAME);

        try {
            int markIndex = Integer.parseInt(index);
            ErrorChecker.checkIndexOutOfBounds(context, markIndex);

            String markItem = context.getList().markItem(markIndex);
            return ("My compliments, you have completed a task.\n " + markItem);
        } catch (NumberFormatException e) {
            throw CommandErrors.numberParseError(context.getList().getLength());
        }
    }
}

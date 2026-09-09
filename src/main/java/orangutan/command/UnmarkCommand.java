package orangutan.command;

import orangutan.OrangutanException;

/**
 * Command to unmark an item on the list as to be completed.
 */
class UnmarkCommand implements Command {
    private static final String NAME = "unmark";
    private final String index;

    /**
     * Creates a new command.
     *
     * @param index Index of the item to be unmarked.
     */
    UnmarkCommand(String index) {
        this.index = index;
    }

    /**
     * Unmarks item at the given index from the list.
     *
     * @param context Context item storing information on the chatbot's current internal state.
     * @return Reply message, plus a printout of the unmarked item.
     *      If index is not a number, will instead return an alert message.
     * @throws OrangutanException If list is empty, or if index is out of range.
     */
    public String run(Context context) throws OrangutanException {
        ErrorChecker.checkListEmpty(context, NAME);

        try {
            int unmarkIndex = Integer.parseInt(index);
            ErrorChecker.checkIndexOutOfBounds(context, unmarkIndex);

            String unmarkedItem = context.getList().unmarkItem(unmarkIndex);
            return ("Brace yourself, this task has not been completed yet.\n " + unmarkedItem);
        } catch (NumberFormatException e) {
            throw CommandErrors.numberParseError(context.getList().getLength());
        }
    }
}

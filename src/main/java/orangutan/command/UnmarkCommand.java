package orangutan.command;

import orangutan.OrangutanException;

/**
 * Command to unmark an item on the list as to be completed.
 */
class UnmarkCommand implements Command {
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
        if (context.getList().getLength() == 0) {
            throw new OrangutanException("Alas! There is nothing to unmark.\n\n"
                    + "Please add some items to the list first.");
        }

        try {
            int unmarkIndex = Integer.parseInt(index);

            if (unmarkIndex < 1 || unmarkIndex > context.getList().getLength()) {
                throw new OrangutanException("Alas! This number is not in the list.\n\n"
                        + "Please keep the index between 1 and " + context.getList().getLength() + " (inclusive).");
            }

            String unmarkedItem = context.getList().unmarkItem(unmarkIndex);
            return ("Brace yourself, this task has not been completed yet.\n " + unmarkedItem);
        } catch (NumberFormatException e) {
            return ("Alas! That is not a number. Not a number I know of, at the least.\n\n"
                    + "Please input an integer between 1 and " + context.getList().getLength() + " (inclusive).");
        }
    }
}

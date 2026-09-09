package orangutan.command;

import orangutan.OrangutanException;

/**
 * Command to delete an item from the list.
 */
class DeleteCommand implements Command {
    private final String index;

    /**
     * Creates a new command.
     *
     * @param index Index of the item to be deleted.
     */
    DeleteCommand(String index) {
        this.index = index;
    }

    /**
     * Deletes item at the given index from the list.
     *
     * @param context Context item storing information on the chatbot's current internal state.
     * @return Reply message, plus a printout of the deleted item.
     *      If index is not a number, will instead return an alert message.
     * @throws OrangutanException If list is empty, or if index is out of range.
     */
    public String run(Context context) throws OrangutanException {
        if (context.getList().getLength() == 0) {
            throw CommandErrors.emptyListError("delete");
        }

        try {
            int deleteIndex = Integer.parseInt(index);

            if (deleteIndex < 1 || deleteIndex > context.getList().getLength()) {
                throw CommandErrors.listOutOfBoundsError(context.getList().getLength());
            }

            String deleteItem = context.getList().deleteItem(deleteIndex);
            return ("The task has been purged from our records.\n " + deleteItem);
        } catch (NumberFormatException e) {
            throw CommandErrors.numberParseError(context.getList().getLength());
        }
    }
}

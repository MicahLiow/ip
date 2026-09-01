package Orangutan.Command;

import Orangutan.OrangutanException;

/**
 * Command to delete an item from the list.
 */
class OrangutanDeleteCommand implements OrangutanCommand {
    private final String index;

    /**
     * Creates a new command.
     *
     * @param index index of the item to be deleted.
     */
    OrangutanDeleteCommand(String index) {
        this.index = index;
    }

    /**
     * Deletes item at the given index from the list.
     *
     * @param context OrangutanContext item storing information on the chatbot's current internal state.
     * @return reply message, plus a printout of the deleted item.
     *      If index is not a number, will instead return an alert message.
     * @throws OrangutanException if list is empty, or if index is out of range.
     */
    public String run(OrangutanContext context) throws OrangutanException {
        if (context.getList().getLength() == 0) {
            throw new OrangutanException("Alas! There is nothing to delete.\n\n" +
                    "Please add some items to the list first.");
        }

        try {
            int deleteIndex = Integer.parseInt(index);

            if (deleteIndex < 1 || deleteIndex > context.getList().getLength()) {
                throw new OrangutanException("Alas! This number is not in the list.\n\n" +
                        "Please keep the index between 1 and " + context.getList().getLength() + " (inclusive).");
            }

            String deleteItem = context.getList().deleteItem(deleteIndex);
            return("The task has been purged from our records.\n " + deleteItem);
        } catch (NumberFormatException e) {
            return ("Alas! That is not a number. Not a number I know of, at the least.\n\n" +
                    "Please input an integer  between 1 and \" + context.getList().getLength() + \" (inclusive).");
        }
    }
}

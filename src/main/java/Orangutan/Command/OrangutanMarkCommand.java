package Orangutan.Command;

import Orangutan.OrangutanException;

/**
 * Command to mark an item as completed.
 */
class OrangutanMarkCommand implements OrangutanCommand{
    private final String index;

    /**
     * Creates a new command.
     *
     * @param index Index of the item to be marked.
     */
    OrangutanMarkCommand(String index) {
        this.index = index;
    }

    /**
     * Marks item at the given index of the list.
     *
     * @param context OrangutanContext item storing information on the chatbot's current internal state.
     * @return Reply message, plus a printout of the marked item.
     *      If index is not a number, will instead return an alert message.
     * @throws OrangutanException If list is empty, or if index is out of range.
     */
    public String run(OrangutanContext context) throws OrangutanException {
        if (context.getList().getLength() == 0) {
            throw new OrangutanException("Alas! There is nothing to mark.\n\n" +
                    "Please add some items to the list first.");
        }

        try {
            int markIndex = Integer.parseInt(index);

            if (markIndex < 1 || markIndex > context.getList().getLength()) {
                throw new OrangutanException("Alas! This number is not in the list.\n\n" +
                        "Please keep the index between 1 and " + context.getList().getLength() + " (inclusive).");
            }

            String markItem = context.getList().markItem(markIndex);
            return("My compliments, you have completed a task.\n " + markItem);
        } catch (NumberFormatException e) {
            return("Alas! That is not a number. Not a number I know of, at the least.\n\n" +
                    "Please input an integer between 1 and " + context.getList().getLength() + " (inclusive).");
        }
    }
}

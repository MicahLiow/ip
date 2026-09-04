package orangutan.command;

/**
 * Command to print all list items.
 */
class OrangutanListCommand implements OrangutanCommand {
    /**
     * Prints out all list items in a numbered list.
     *
     * @param context OrangutanContext item storing information on the chatbot's current internal state.
     * @return The printout of list items, or a message if list is empty.
     */
    public String run(OrangutanContext context) {
        if (context.getList().getLength() == 0) {
            return ("The list is empty.");
        } else {
            return ("The following are the undertakings in your list:\n" + context.getList());
        }
    }
}
package orangutan.command;

import orangutan.chatlist.ChatList;

/**
 * Command to find items in list whose descriptions contain a specific string.
 */
class OrangutanFindCommand implements OrangutanCommand{
    private final String query;

    /**
     * Creates new command.
     *
     * @param query String to be searched for.
     */
    OrangutanFindCommand(String query) {
        this.query = query;
    }

    /**
     * Returns list of matching items.
     *
     * @param context OrangutanContext item storing information on the chatbot's current internal state.
     * @return Reply message, plus list of all matching items.
     *      If no items could be found, will return an appropriate reply with no list.
     */
    public String run(OrangutanContext context) {
        ChatList res = context.getList().findItem(this.query);
        if (res.getLength() == 0) {
            return ("The item you requested could not be found. Perchance it is not in our list?");
        } else {
            return ("Here are the items you have requested:\n" + res);
        }
    }
}

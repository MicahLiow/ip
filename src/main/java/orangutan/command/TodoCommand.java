package orangutan.command;

import orangutan.chatlist.ItemType;
import orangutan.chatlist.ListItem;

/**
 * Command to create a new to-do and add it to the list.
 */
class TodoCommand implements Command {
    private final String item;
    private final boolean isCompleted;

    /**
     * Creates new command.
     *
     * @param item Description of the list item.
     * @param isCompleted Whether the to-do has been completed or not.
     */
    TodoCommand(String item, boolean isCompleted) {
        this.item = item;
        this.isCompleted = isCompleted;
    }

    /**
     * Creates to-do and appends it to the list.
     *
     * @param context Context item storing information on the chatbot's current internal state.
     * @return Reply message, plus a printout of the new to-do.
     */
    public String run(Context context) {
        ListItem newTodo = new ListItem(ItemType.TODO, item, isCompleted);
        context.getList().addItem(newTodo);

        return ("A task has been added.\n " + newTodo);
    }
}

package orangutan.command;

import orangutan.chatlist.ItemType;
import orangutan.chatlist.ListItem;

/**
 * Command to create a new to-do and add it to the list.
 */
class OrangutanTodoCommand implements OrangutanCommand {
    private final String item;
    private final boolean isCompleted;

    /**
     * Creates new command.
     *
     * @param item Description of the list item.
     * @param isCompleted Whether the deadline has been completed or not.
     */
    OrangutanTodoCommand(String item, boolean isCompleted) {
        this.item = item;
        this.isCompleted = isCompleted;
    }

    /**
     * Creates to-do and appends it to the list.
     *
     * @param context OrangutanContext item storing information on the chatbot's current internal state.
     * @return Reply message, plus a printout of the new deadline.
     */
    public String run(OrangutanContext context) {
        ListItem newTodo = new ListItem(ItemType.TODO, item, isCompleted);
        context.getList().addItem(newTodo);

        return ("A task has been added.\n " + newTodo);
    }
}

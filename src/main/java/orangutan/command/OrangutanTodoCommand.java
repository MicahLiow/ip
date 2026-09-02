package orangutan.command;

import orangutan.chatlist.ItemType;
import orangutan.chatlist.ListItem;

class OrangutanTodoCommand implements OrangutanCommand{
    private final String item;
    private final boolean isCompleted;

    OrangutanTodoCommand(String item, boolean isCompleted) {
        this.item = item;
        this.isCompleted = isCompleted;
    }

    public String run(OrangutanContext context) {
        ListItem newTodo = new ListItem(ItemType.TODO, item, false);
        context.getList().addItem(newTodo);

        return ("A task has been added.\n " + newTodo);
    }
}

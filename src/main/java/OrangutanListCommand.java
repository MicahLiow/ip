public class OrangutanListCommand implements OrangutanCommand {
    public OrangutanListCommand() {
    }

    public String run(OrangutanContext context) {
        if (context.getList().getLength() == 0) {
            return ("The list is empty.");
        } else {
            return ("The following are the undertakings in your list:\n" + context.getList());
        }
    }
}
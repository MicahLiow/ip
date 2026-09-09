package orangutan.command;

import orangutan.OrangutanException;

/**
 * Command to set sorting method of the list.
 */
public class SortCommand {
    private final String param;

    SortCommand(String param) {
        this.param = param;
    }

    /**
     * Sets list sorting method:
     *      "off" means no sorting
     *      "completion" means sorting by completion status, then alphabetically for ties.
     *      "datetime" means sorting by time (start time for events, due date for deadlines),
     *              then alphabetically for ties. To-dos are placed at the top.
     */
    public String run(Context context) throws OrangutanException {
        try {
            context.getList().setSorting(SortMethod.valueOf(param.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw CommandErrors.unknownSortMethodError();
        }
        if (param.equals("none")) {
            return "Your list will no longer be ordered.";
        } else {
            return String.format("Your list will now be ordered by %s.\n%s", param, context.getList().toString());
        }
    }
}

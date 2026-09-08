package orangutan.command;

import orangutan.OrangutanException;

/**
 * Parses commands from user and runs the appropriate commands.
 */
public class Parser {
    private final Context context;

    /**
     * Creates new parser object.
     *
     * @param context Context item storing information on the chatbot's current internal state.
     */
    public Parser(Context context) {
        this.context = context;
    }

    /**
     * Parses user input, calls the corresponding command with given parameters, and returns the command output.
     *
     * @param input Command supplied by user.
     * @return Reply after command completion.
     */
    public String parseCommand(String input) {
        String[] params = input.split("/"); // e.g. {"event eat ","/from 20260831 1800 ","/to 20260831 1900"}
        String[] action = params[0].trim().split(" ", 2); // e.g. {"event", "meet with friends"}

        try {
            switch (action[0]) {
                case "init":
                    return new InitCommand().run(context);

                case "todo":
                    verifyActionTitle(action);
                    String todoItem = action[1];
                    return new TodoCommand(todoItem, false).run(context);

                case "deadline":
                    verifyActionTitle(action);

                    if (params.length < 2) {
                        throw new OrangutanException("Alas! Deadline details have not been revealed.\n\n"
                                + "Please include '/by' in your message, along with the time or date of the deadline.");
                    }

                    String by = params[1].trim().split(" ", 2)[1];
                    String deadlineItem = action[1];
                    return new DeadlineCommand(deadlineItem, by, false).run(context);

                case "event":
                    verifyActionTitle(action);

                    if (params.length < 3) {
                        throw new OrangutanException("Alas! Some event details have not been revealed.\n\n"
                                + "Please include '/from' and '/to' in your message, "
                                + "along with the start and end time or day.");
                    }

                    String from = params[1].trim().split(" ", 2)[1];
                    String to = params[2].trim().split(" ", 2)[1];
                    String eventItem = action[1];
                    return new EventCommand(eventItem, from, to, false).run(context);

                case "list":
                    return new ListCommand().run(context);

                case "find":
                    verifyActionIndex(action);
                    return new FindCommand(action[1]).run(context);

                case "delete":
                    verifyActionIndex(action);
                    return new DeleteCommand(action[1]).run(context);

                case "mark":
                    verifyActionIndex(action);
                    return new MarkCommand(action[1]).run(context);

                case "unmark":
                    verifyActionIndex(action);
                    return new UnmarkCommand(action[1]).run(context);

                case "bye":
                    return new ByeCommand().run(context);

                default:
                    throw new OrangutanException("Alas! My simian mind is unable to comprehend your words.\n\n"
                            + "Please use words I understand: "
                            + "\"todo\", \"deadline\", \"event\", \"list\", \"delete\", \"mark\", \"unmark\", \"bye\"");
            }
        } catch (OrangutanException e) {
            return (e.toString());
        }
    }

    private void verifyActionTitle(String[] action) throws OrangutanException {
        if (action.length < 2) {
            throw new OrangutanException(String.format("Alas! The name of this %s has not been revealed.\n\n"
                    + "Please include the %s name.", action[0], action[0]));
        }
    }

    private void verifyActionIndex(String[] action) throws OrangutanException {
        if (action.length < 2) {
            throw new OrangutanException(String.format("Alas! I do not know which item to %s.\n\n"
                    + "Please follow the unmark command with an integer between 1 and %d (inclusive).",
                    action[0], context.getList().getLength()));
        }
    }
}

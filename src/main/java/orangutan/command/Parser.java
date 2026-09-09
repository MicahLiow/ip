package orangutan.command;

import java.util.Arrays;

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
     * Breaks user input down into individual parameters.
     *
     * @param input Command supplied by user, guaranteed to be non-empty.
     * @return Array of String Arrays; the first element is the (command, title) pair
     *      and the second element are any date/time parameters.
     *      e.g. { {"event", "eat lunch"} , {"20260831 1800", "20260831 1900"} }
     */
    private String[][] parseCommand(String input) {
        String[] fields = input.split("/"); // {"event eat lunch ", "from 20260831 1800 ", "to 20260831 1900"}
        String[] action = Arrays.stream(fields)
                .limit(1) // ["event eat lunch "]
                .map(String::trim) // ["event eat lunch"]
                .flatMap(s -> Arrays.stream(s.split(" ", 2))) // ["event", "eat lunch"]
                .toArray(String[]::new); // {"event", "eat lunch"}
        String[] params = Arrays.stream(fields)
                .skip(1) // ["from 20260831 1800 ", "to 20260831 1900"]
                .map(String::trim) // ["from 20260831 1800", "to 20260831 1900"]
                .map(s -> Arrays.stream(s.split(" ", 2)))
                // [ ["from", "20260831 1800"] , ["to", "20260831 1900] ]
                .map(s -> s.skip(1)) // [ ["20260831 1800"] , ["20260831 1900"] ]
                .flatMap(s -> s) // ["20260831 1800" , "20260831 1900"]
                .toArray(String[]::new); // {"20260831 1800" , "20260831 1900"}

        return new String[][]{action, params};
    }

    /**
     * Parses user input, calls the corresponding command with given parameters, and returns the command output.
     *
     * @param input Command supplied by user.
     * @return Reply after command completion.
     */
    public String runCommand(String input) {
        String[][] command = parseCommand(input);
        String[] action = command[0]; // e.g. {"event", "eat"}
        String[] params = command[1]; // e.g. { {"from", 20260831 1800"} , {"to", "20260831 1900"} }

        try {
            switch (action[0]) {
                case "init":
                    return new InitCommand().run(context);

                case "todo":
                    verifyActionTitle(action);
                    String todoTitle = action[1];
                    return new TodoCommand(todoTitle, false).run(context);

                case "deadline":
                    verifyActionTitle(action);

                    if (params.length < 1) {
                        throw new OrangutanException("Alas! Deadline details have not been revealed.\n\n"
                                + "Please include '/by' in your message, along with the time or date of the deadline.");
                    }

                    String by = params[0];
                    String deadlineTitle = action[1];
                    return new DeadlineCommand(deadlineTitle, by, false).run(context);

                case "event":
                    verifyActionTitle(action);

                    if (params.length < 2) {
                        throw new OrangutanException("Alas! Some event details have not been revealed.\n\n"
                                + "Please include '/from' and '/to' in your message, "
                                + "along with the start and end time or day.");
                    }

                    String from = params[0];
                    String to = params[1];
                    String eventTitle = action[1];
                    return new EventCommand(eventTitle, from, to, false).run(context);

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

    /**
     * Verifies that action title exists.
     *
     * @param action String array, expected to be an (action, title) pair (e.g. {"todo", "lunch"})
     * @throws OrangutanException if title is missing.
     */
    private void verifyActionTitle(String[] action) throws OrangutanException {
        if (action.length < 2) {
            throw new OrangutanException(String.format("Alas! The name of this %s has not been revealed.\n\n"
                    + "Please include the %s name.", action[0], action[0]));
        }
    }

    /**
     * Verifies that action index exists.
     *
     * @param action String array, expected to be an (action, index) pair (e.g. {"mark", "1"}
     * @throws OrangutanException if index is missing.
     */
    private void verifyActionIndex(String[] action) throws OrangutanException {
        if (action.length < 2) {
            throw new OrangutanException(String.format("Alas! I do not know which item to %s.\n\n"
                    + "Please follow the unmark command with an integer between 1 and %d (inclusive).",
                    action[0], context.getList().getLength()));
        }
    }
}

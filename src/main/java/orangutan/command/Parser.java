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
                    ErrorChecker.checkTitleMissing(action);
                    String todoTitle = action[1];
                    return new TodoCommand(todoTitle, false).run(context);

                case "deadline":
                    ErrorChecker.checkTitleMissing(action);

                    if (params.length < 1) {
                        throw CommandErrors.deadlineMissingParametersError();
                    }

                    String by = params[0];
                    String deadlineTitle = action[1];
                    return new DeadlineCommand(deadlineTitle, by, false).run(context);

                case "event":
                    ErrorChecker.checkTitleMissing(action);

                    if (params.length < 2) {
                        throw CommandErrors.eventMissingParametersError();
                    }

                    String from = params[0];
                    String to = params[1];
                    String eventTitle = action[1];
                    return new EventCommand(eventTitle, from, to, false).run(context);

                case "list":
                    return new ListCommand().run(context);

                case "find":
                    ErrorChecker.verifyActionIndex(context, action);
                    return new FindCommand(action[1]).run(context);

                case "delete":
                    ErrorChecker.verifyActionIndex(context, action);
                    return new DeleteCommand(action[1]).run(context);

                case "mark":
                    ErrorChecker.verifyActionIndex(context, action);
                    return new MarkCommand(action[1]).run(context);

                case "unmark":
                    ErrorChecker.verifyActionIndex(context, action);
                    return new UnmarkCommand(action[1]).run(context);

                case "bye":
                    return new ByeCommand().run(context);

                default:
                    throw CommandErrors.unknownCommandError();
            }
        } catch (OrangutanException e) {
            return (e.toString());
        }
    }
}

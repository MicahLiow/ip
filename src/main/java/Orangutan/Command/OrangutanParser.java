package orangutan.command;

import orangutan.OrangutanException;

/**
 * Parses commands from user and runs the appropriate commands.
 */
public class OrangutanParser {
    private OrangutanContext context;

    /**
     * Creates new parser object.
     *
     * @param context OrangutanContext item storing information on the chatbot's current internal state.
     */
    public OrangutanParser(OrangutanContext context) {
        this.context = context;
    }

    /**
     * Parses user input, calls the corresponding command with given parameters, and returns the command output.
     *
     * @param input Command supplied by user.
     * @return Reply after command completion.
     */
    public String parseCommand(String input) {
        String[] queryParams = input.split("/"); // e.g. {"event eat ","/from 20260831 1800 ","/to 20260831 1900"}
        String[] commandParams = queryParams[0].trim().split(" ", 2); // e.g. {"event", "meet with friends"}

        try {
            switch (commandParams[0]) {
                case "init":
                    return new OrangutanInitCommand().run(context);

                case "todo":
                    if (commandParams.length < 2) {
                        throw new OrangutanException("Alas! The name of this to-do has not been revealed.\n\n"
                                + "Please include the to-do name.");
                    }
                    String todoItem = commandParams[1];
                    return new OrangutanTodoCommand(todoItem, false).run(context);

                case "deadline":
                    if (commandParams.length < 2) {
                        throw new OrangutanException("Alas! The name of this deadline has not been revealed.\n\n"
                                + "Please include the deadline name.");
                    }
                    if (queryParams.length < 2) {
                        throw new OrangutanException("Alas! Deadline details have not been revealed.\n\n"
                                + "Please include '/by' in your message, along with the time or date of the deadline.");
                    }

                    String by = queryParams[1].trim().split(" ", 2)[1];
                    String deadlineItem = commandParams[1];
                    return new OrangutanDeadlineCommand(deadlineItem, by, false).run(context);

                case "event":
                    if (commandParams.length < 2) {
                        throw new OrangutanException("Alas! The name of this event has not been revealed.\n\n"
                                + "Please include the event name.");
                    }
                    if (queryParams.length < 3) {
                        throw new OrangutanException("Alas! Some event details have not been revealed.\n\n"
                                + "Please include '/from' and '/to' in your message, "
                                + "along with the start and end time or day.");
                    }

                    String from = queryParams[1].trim().split(" ", 2)[1];
                    String to = queryParams[2].trim().split(" ", 2)[1];
                    String eventItem = commandParams[1];
                    return new OrangutanEventCommand(eventItem, from, to, false).run(context);

                case "list":
                    return new OrangutanListCommand().run(context);

                case "find":
                    if (commandParams.length < 2) {
                        throw new OrangutanException("Alas! I do not know what to find.\n\n"
                                + "Please follow the find command with the text I am to find.");
                    }

                    return new OrangutanFindCommand(commandParams[1]).run(context);

                case "delete":
                    if (commandParams.length < 2) {
                        throw new OrangutanException("Alas! I do not know which item to delete.\n\n"
                                + "Please follow the delete command with an integer between 1 and "
                                + context.getList().getLength() + " (inclusive).");
                    }

                    return new OrangutanDeleteCommand(commandParams[1]).run(context);

                case "mark":
                    if (commandParams.length < 2) {
                        throw new OrangutanException("Alas! I do not know which item to mark.\n\n"
                                + "Please follow the mark command with an integer between 1 and "
                                + context.getList().getLength() + " (inclusive).");
                    }

                    return new OrangutanMarkCommand(commandParams[1]).run(context);

                case "unmark":
                    if (commandParams.length < 2) {
                        throw new OrangutanException("Alas! I do not know which item to unmark.\n\n"
                                + "Please follow the unmark command with an integer between 1 and "
                                + context.getList().getLength() + " (inclusive).");
                    }

                    return new OrangutanUnmarkCommand(commandParams[1]).run(context);

                case "bye":
                    return new OrangutanByeCommand().run(context);

                default:
                    throw new OrangutanException("Alas! My simian mind is unable to comprehend your words.\n\n"
                            + "Please use words I understand: "
                            + "\"todo\", \"deadline\", \"event\", \"list\", \"delete\", \"mark\", \"unmark\", \"bye\"");
            }
        } catch (OrangutanException e) {
            return (e.toString());
        }
    }
}

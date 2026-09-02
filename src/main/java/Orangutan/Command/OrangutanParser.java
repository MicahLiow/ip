package Orangutan.Command;

import Orangutan.OrangutanException;

public class OrangutanParser {
    private OrangutanContext context;

    public OrangutanParser(OrangutanContext context) {
        this.context = context;
    }

    public String parseCommand(String input) {
        String[] query = input.split("/"); //e.g. {"event dinner ", "/from 20260831 1800 ", "/to 20260831 1900"}
        String[] command = query[0].trim().split(" ", 2); //e.g. {"event", "meet with friends"}
        String item;

        try {
            switch (command[0]) {
                case "init":
                    return new OrangutanInitCommand().run(context);
                case "todo":
                    if (command.length < 2) {
                        throw new OrangutanException("Alas! The name of this to-do has not been revealed.\n\n" +
                                "Please include the to-do name.");
                    }
                    item = command[1];
                    return new OrangutanTodoCommand(item, false).run(context);

                case "deadline":
                    if (command.length < 2) {
                        throw new OrangutanException("Alas! The name of this deadline has not been revealed.\n\n" +
                                "Please include the deadline name.");
                    }
                    if (query.length < 2) {
                        throw new OrangutanException("Alas! Deadline details have not been revealed.\n\n" +
                                "Please include '/by' in your message, along with the time or date of the deadline.");
                    }

                    String by = query[1].trim().split(" ", 2)[1];
                    item = command[1];
                    return new OrangutanDeadlineCommand(item, by, false).run(context);

                case "event":
                    if (command.length < 2) {
                        throw new OrangutanException("Alas! The name of this event has not been revealed.\n\n" +
                                "Please include the event name.");
                    }
                    if (query.length < 3) {
                        throw new OrangutanException("Alas! Some event details have not been revealed.\n\n" +
                                "Please include '/from' and '/to' in your message, along with the start and end time or day.");
                    }

                    String from = query[1].trim().split(" ", 2)[1];
                    String to = query[2].trim().split(" ", 2)[1];
                    item = command[1];
                    return new OrangutanEventCommand(item, from, to, false).run(context);

                case "list":
                    return new OrangutanListCommand().run(context);

                case "delete":
                    if (command.length < 2) {
                        throw new OrangutanException("Alas! I do not know which item to delete.\n\n" +
                                "Please follow the delete command with an integer between 1 and " + context.getList().getLength() +
                                " (inclusive).");
                    }

                    return new OrangutanDeleteCommand(command[1]).run(context);

                case "mark":
                    if (command.length < 2) {
                        throw new OrangutanException("Alas! I do not know which item to mark.\n\n" +
                                "Please follow the mark command with an integer between 1 and " + context.getList().getLength() +
                                " (inclusive).");
                    }

                    return new OrangutanMarkCommand(command[1]).run(context);

                case "unmark":
                    if (command.length < 2) {
                        throw new OrangutanException("Alas! I do not know which item to unmark.\n\n" +
                                "Please follow the unmark command with an integer between 1 and " + context.getList().getLength() +
                                " (inclusive).");
                    }

                    return new OrangutanUnmarkCommand(command[1]).run(context);

                case "find":
                    if (command.length < 2) {
                        throw new OrangutanException("Alas! I do not know what to find.\n\n"
                                + "Please follow the find command with the text I am to find.");
                    }

                    return new OrangutanFindCommand(command[1]).run(context);

                case "bye":
                    return new OrangutanByeCommand().run(context);

                default:
                    throw new OrangutanException("Alas! My simian mind is unable to comprehend your words.\n\n" +
                            "Please use words I understand: " +
                            "\"todo\", \"deadline\", \"event\", \"list\", \"delete\", \"mark\", \"unmark\", \"bye\"");

            }
        } catch(OrangutanException e) {
            return(e.toString());
        }
    }
}
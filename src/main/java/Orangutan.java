import java.util.*;
import java.lang.*;

public class Orangutan {
    public static void main(String[] args) throws OrangutanException{
        //initialize important variables
        //ascii banner adapted from https://ascii.co.uk/art/orangutan
        String line = "___________________________________________________________\n";
        ChatList list = new ChatList();

        String banner = line
                      + "  ___                             | |             \n"
                      + " / _ \\ ____ ____ ____   ____ _   _| |_ ____ ____  \n"
                      + "| . . |  __/ _  |  _ \\ / _  | | | | __/ _  |  _ \\ \n"
                      + "| (_) | |  |(_| | | | | (_| | |_| | | |(_| | | | |\n"
                      + " \\___/|_|  \\____|_| |_|\\__  |\\____|\\__\\____|_| |_|\n"
                      + "                        __/ |                     \n"
                      + "                       |___/                      \n\n"
                      + "Greetings, I am Orangutan.\nHow may I assist you on this fine day?\n" + line;
        System.out.println(banner);

        //Get user input
        Scanner input = new Scanner(System.in);
        while (true) {
            String[] query = input.nextLine().split("/");
            String[] command = query[0].trim().split(" ", 2);

            boolean exit = false;

            System.out.print(line);
            switch (command[0]) {
                case "todo":
                    try {
                        if (command.length < 2) {
                            throw new OrangutanException("Alas! The name of this todo has not been revealed.\n\n" +
                                    "Please include the todo name.");
                        }

                        TodoItem newTodo = new TodoItem(command[1]);
                        list.addItem(newTodo);

                        System.out.println("A task has been added.");
                        System.out.println("  " + newTodo);
                    } catch(OrangutanException e) {
                        System.out.println(e);
                    }
                    break;

                case "deadline":
                    try {
                        if (command.length < 2) {
                            throw new OrangutanException("Alas! The name of this deadline has not been revealed.\n\n" +
                                    "Please include the deadline name.");
                        }

                        if (query.length < 2) {
                            throw new OrangutanException("Alas! Deadline details have not been revealed.\n\n" +
                                    "Please include '\\by' in your message, along with the time or date of the deadline.");
                        }

                        String by = query[1].trim().split(" ", 2)[1];
                        DeadlineItem newDeadline = new DeadlineItem(command[1], by);
                        list.addItem(newDeadline);

                        System.out.println("A deadline has been added.");
                        System.out.println("  " + newDeadline);
                    } catch(OrangutanException e) {
                        System.out.println(e);
                    }
                    break;

                case "event":
                    try {
                        if (command.length < 2) {
                            throw new OrangutanException("Alas! The name of this event has not been revealed.\n\n" +
                                    "Please include the event name.");
                        }

                        if (query.length < 3) {
                            throw new OrangutanException("Alas! Some event details have not been revealed.\n\n" +
                                    "Please include '\\from' and '\\to' in your message, along with the start and end time or day.");
                        }

                        String from = query[1].trim().split(" ", 2)[1];
                        String to = query[2].trim().split(" ", 2)[1];
                        EventItem newEvent = new EventItem(command[1], from, to);
                        list.addItem(newEvent);

                        System.out.println("An event has been added.");
                        System.out.println("  " + newEvent);
                    } catch (OrangutanException e) {
                        System.out.println(e);
                    }
                    break;

                case "list":
                    System.out.println("The following are the undertakings in your list:");
                    System.out.println(list);
                    break;

                case "mark":
                   try {
                        if (command.length < 2) {
                            throw new OrangutanException("Alas! I do not know which item to mark.\n\n" +
                                    "Please follow the mark command with an integer between 1 and \" + list.getLength() + \" (inclusive).\"");
                        }

                        int markIndex = Integer.parseInt(command[1]);

                        if (markIndex < 1 || markIndex > list.getLength()) {
                            throw new OrangutanException("Alas! This number is not in the list.\n\n" +
                             "Please keep the index between 1 and " + list.getLength() + " (inclusive).");
                        }

                        String markedItem = list.markItem(markIndex);
                        System.out.println("My compliments, you have completed a task.");
                        System.out.println("  " + markedItem);

                    }  catch (NumberFormatException e) {
                        System.out.println("Alas! That is not a number. Not a number I know of, at the least.\n\n" +
                         "Please input an integer between 1 and \" + list.getLength() + \" (inclusive).\"");
                    } catch (OrangutanException e) {
                       System.out.println(e);
                   }
                    break;

                case "unmark":
                    try {
                        if (command.length < 2) {
                            throw new OrangutanException("Alas! I do not know which item to unmark.\n\n" +
                                    "Please follow the unmark command with an integer between 1 and \" + list.getLength() + \" (inclusive).\"");
                        }

                        int unmarkIndex = Integer.parseInt(command[1]);

                        if (unmarkIndex < 1 || unmarkIndex > list.getLength()) {
                            throw new OrangutanException("Alas! This number is not in the list.\n\n" +
                             "Please keep the index between 1 and " + list.getLength() + " (inclusive).");
                        }

                        String unmarkedItem = list.unmarkItem(unmarkIndex);

                        System.out.println("Brace yourself, this task has not been completed yet.");
                        System.out.println("  " + unmarkedItem);

                    } catch (NumberFormatException e) {
                        System.out.println("Alas! That is not a number. Not a number I know of, at the least.\n\n" +
                         "Please input an integer between 1 and \" + list.getLength() + \" (inclusive).\"");
                    } catch (OrangutanException e) {
                        System.out.println(e);
                    }
                    break;

                case "bye":
                    exit = true;

                    System.out.println("Fare thee well, and may we meet again.");
                    break;

                default:
                    System.out.println("Alas! My simian mind is unable to comprehend your words.\n\n" +
                     "Please use words I understand: \"todo\", \"deadline\", \"event\", \"list\", \"mark\", \"unmark\", \"bye\"");
            }

            System.out.println(line);
            if (exit) break;
        }
    }
}
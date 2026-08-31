import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/***************** TODO!!!!! class header comments, put all classes in packages*/

public class Orangutan {
    public static void main(String[] args) {
        //ascii banner adapted from https://ascii.co.uk/art/orangutan
        String line = "___________________________________________________________\n";

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

        //initialize list from save file if one exists. If not, initialize an empty list
        boolean runLoop = true; //whether to run the input-collection loop. will be set to false if list file cannot be read.

        String path = "./data/orangutan.txt"; //path to save file
        Path filePath = Paths.get(path);

        ChatList listItems = null;
        if (Files.exists(filePath)) {
            try {
                listItems = ChatList.readFromFile(filePath);
            } catch (IOException e) {
                runLoop = false;
                System.out.println("Alas! I have failed to access the information previously stored in data/orangutan.txt.\n\n" +
                        "Please ensure I have access to said file before returning to me.");
            } catch (DateTimeParseException e) {
                runLoop = false;
                System.out.println("Alas! I do not comprehend the dates and times stored in data/orangutan.txt.\n\n" +
                        "Please ensure stored dates are of format yyyymmdd hhmm (e.g. 20260831 2359) before returning to me.");
            }
        } else {
            listItems = new ChatList();
        }

        //Get user input
        Scanner input = new Scanner(System.in);

        while (runLoop) {
            String[] query = input.nextLine().split("/");
            String[] command = query[0].trim().split(" ", 2);



            System.out.print(line);
            try {
                switch (command[0]) {
                    case "todo":
                        if (command.length < 2) {
                            throw new OrangutanException("Alas! The name of this to-do has not been revealed.\n\n" +
                                    "Please include the to-do name.");
                        }

                        ListItem newTodo = new ListItem(ItemType.TODO, command[1], false);
                        listItems.addItem(newTodo);

                        System.out.println("A task has been added.");
                        System.out.println("  " + newTodo);
                        break;

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
                        ListItem newDeadline = new ListItem(ItemType.DEADLINE, command[1], false, by);
                        listItems.addItem(newDeadline);

                        System.out.println("A deadline has been added.");
                        System.out.println("  " + newDeadline);
                        break;

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
                        ListItem newEvent = new ListItem(ItemType.EVENT, command[1], false, from, to);
                        listItems.addItem(newEvent);

                        System.out.println("An event has been added.");
                        System.out.println("  " + newEvent);
                        break;

                    case "list":
                        if (listItems.getLength() == 0) {
                            System.out.println("The list is empty.");
                        } else {
                            System.out.println("The following are the undertakings in your list:");
                            System.out.println(listItems);
                        }
                        break;

                    case "delete":
                        if (listItems.getLength() == 0) {
                            throw new OrangutanException("Alas! There is nothing to delete.\n\n" +
                             "The list is empty; please add some items to it first.m");
                        }

                        if (command.length < 2) {
                            throw new OrangutanException("Alas! I do not know which item to delete.\n\n" +
                                    "Please follow the delete command with an integer between 1 and " + listItems.getLength() + " (inclusive).");
                        }

                        int deleteIndex = Integer.parseInt(command[1]);

                        if (deleteIndex < 1 || deleteIndex > listItems.getLength()) {
                            throw new OrangutanException("Alas! This number is not in the list.\n\n" +
                                    "Please keep the index between 1 and " + listItems.getLength() + " (inclusive).");
                        }

                        String deleteItem = listItems.deleteItem(deleteIndex);
                        System.out.println("The task has been purged from our records.");
                        System.out.println("  " + deleteItem);
                        break;

                    case "mark":
                        if (listItems.getLength() == 0) {
                            throw new OrangutanException("Alas! There is nothing to mark.\n\n" +
                                    "The list is empty; please add some items to it first.");
                        }

                        if (command.length < 2) {
                            throw new OrangutanException("Alas! I do not know which item to mark.\n\n" +
                                    "Please follow the mark command with an integer between 1 and " + listItems.getLength() + " (inclusive).");
                        }

                        int markIndex = Integer.parseInt(command[1]);

                        if (markIndex < 1 || markIndex > listItems.getLength()) {
                            throw new OrangutanException("Alas! This number is not in the list.\n\n" +
                                    "Please keep the index between 1 and " + listItems.getLength() + " (inclusive).");
                        }

                        String markedItem = listItems.markItem(markIndex);
                        System.out.println("My compliments, you have completed a task.");
                        System.out.println("  " + markedItem);
                        break;

                    case "unmark":
                        if (listItems.getLength() == 0) {
                            throw new OrangutanException("Alas! There is nothing to unmark.\n\n" +
                                    "The list is empty; please add some items to it first.");
                        }

                        if (command.length < 2) {
                            throw new OrangutanException("Alas! I do not know which item to unmark.\n\n" +
                                    "Please follow the unmark command with an integer between 1 and " + listItems.getLength() + " (inclusive).");
                        }

                        int unmarkIndex = Integer.parseInt(command[1]);

                        if (unmarkIndex < 1 || unmarkIndex > listItems.getLength()) {
                            throw new OrangutanException("Alas! This number is not in the list.\n\n" +
                                    "Please keep the index between 1 and " + listItems.getLength() + " (inclusive).");
                        }

                        String unmarkedItem = listItems.unmarkItem(unmarkIndex);

                        System.out.println("Brace yourself, this task has not been completed yet.");
                        System.out.println("  " + unmarkedItem);
                        break;

                    case "bye":
                        //writes listItems to data/orangutan.txt, then exits the loop
                        try {
                            runLoop = false; //will only exit if we can successfully write to file
                            listItems.writeToFile(filePath); //will automatically create directories and files as needed
                            System.out.println("Fare thee well, and may we meet again.");
                        } catch (IOException e) {
                            runLoop = true;
                            System.out.println("Alas! I was not able to write your list to data/orangutan.txt.\n\n" +
                                    "Please ensure I have access to said files and folders before we bid farewell.");
                        }

                        break;

                    default:
                        System.out.println("Alas! My simian mind is unable to comprehend your words.\n\n" +
                                "Please use words I understand: " +
                                "\"todo\", \"deadline\", \"event\", \"list\", \"delete\", \"mark\", \"unmark\", \"bye\"");
                }

            }  catch (NumberFormatException e) {
                System.out.println("Alas! That is not a number. Not a number I know of, at the least.\n\n" +
                        "Please input an integer between 1 and " + listItems.getLength() + " (inclusive).");
            } catch (DateTimeParseException e) {
                System.out.println("Alas! I do not comprehend the dates and times stored in data/orangutan.txt.\n\n" +
                        "Please ensure stored dates are of format yyyymmdd hhmm (e.g. 20260831 2359)");
            } catch (OrangutanException e) {
                System.out.println(e);
            }

            System.out.println(line);
        }
    }
}
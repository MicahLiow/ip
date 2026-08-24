import java.util.*;
import java.lang.*;

public class Orangutan {
    public static void main(String[] args) {
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

            System.out.println(line);
            switch (command[0]) {
                case "todo":
                    TodoItem newTodo = new TodoItem(command[1]);
                    list.addItem(newTodo);

                    System.out.println("A task has been added.");
                    System.out.println("  " + newTodo);
                    break;
                case "deadline":
                    String by = query[1].trim().split(" ", 2)[1];
                    DeadlineItem newDeadline = new DeadlineItem(command[1], by);
                    list.addItem(newDeadline);

                    System.out.println("A deadline has been added.");
                    System.out.println("  " + newDeadline);
                    break;
                case "event":
                    String from = query[1].trim().split(" ", 2)[1];
                    String to = query[2].trim().split(" ", 2)[1];
                    EventItem newEvent = new EventItem(command[1], from, to);
                    list.addItem(newEvent);

                    System.out.println("An event has been added.");
                    System.out.println("  " + newEvent);
                    break;
                case "list":
                    System.out.println("The following are the undertakings in your list:");
                    System.out.println(list);
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(command[1]);
                    String markedItem = list.markItem(markIndex);

                    System.out.println("My compliments, you have completed a task.");
                    System.out.println("  " + markedItem);
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(command[1]);
                    String unmarkedItem = list.unmarkItem(unmarkIndex);

                    System.out.println("Brace yourself, this task has not been completed yet.");
                    System.out.println("  " + unmarkedItem);
                    break;
                case "bye":
                    exit = true;

                    System.out.println("Fare thee well, and may we meet again.");
                    break;
                default:
                    String joinedQuery = String.join("/", query);
                    list.addItem(new ListItem(joinedQuery));

                    System.out.println("I have committed to memory: " + joinedQuery);
            }

            System.out.println(line);
            if (exit) break;
        }
    }
}
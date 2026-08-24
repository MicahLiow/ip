import java.util.*;

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
            String[] query = input.nextLine().split(" ");
            String command = query[0];
            int param = -1;

            if (query.length > 1) {
                param = Integer.parseInt(query[1]);
            }

            String reply = "";
            boolean exit = false;

            switch (command) {
                case "bye":
                    exit = true;
                    reply = "Fare thee well, and may we meet again.";
                    break;
                case "list":
                    reply = "The following are the undertakings in your list:\n" + list.toString();
                    break;
                case "mark":
                    String markedItem = list.markItem(param);
                    reply = "My compliments, you have completed a task.\n  " + markedItem;
                    break;
                case "unmark":
                    String unmarkedItem = list.unmarkItem(param);
                    reply = "Brace yourself, this task has not been completed yet.\n  " + unmarkedItem;
                    break;
                default:
                    String joinedQuery = String.join(" ", query);
                    list.addItem(joinedQuery);
                    reply = "I have committed to memory: " + joinedQuery;
            }

            System.out.println(line + reply + "\n" + line);

            if (exit) break;
        }
    }
}
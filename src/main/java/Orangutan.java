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
            String query = input.nextLine();
            String reply = "";
            boolean exit = false;

            switch (query) {
                case "bye":
                    exit = true;
                    reply = "Fare thee well, and may we meet again.";
                    break;
                case "list":
                    reply = list.toString();
                    break;
                default:
                    list.addItem(query);
                    reply = "I have committed to memory: " + query;
            }

            System.out.println(line + reply + "\n" + line);

            if (exit) {break;}
        }
    }
}
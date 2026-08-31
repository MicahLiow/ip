package Orangutan;

import Orangutan.Command.OrangutanParser;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

class OrangutanUi {
    private static String LINE = "___________________________________________________________";
    //ascii banner adapted from https://ascii.co.uk/art/orangutan
    private static String BANNER = "  ___                             | |             \n"
            + " / _ \\ ____ ____ ____   ____ _   _| |_ ____ ____  \n"
            + "| . . |  __/ _  |  _ \\ / _  | | | | __/ _  |  _ \\ \n"
            + "| (_) | |  |(_| | | | | (_| | |_| | | |(_| | | | |\n"
            + " \\___/|_|  \\____|_| |_|\\__  |\\____|\\__\\____|_| |_|\n"
            + "                        __/ |                     \n"
            + "                       |___/                      \n";

    private final PrintStream out;
    private final Scanner in;

    OrangutanUi(PrintStream out, InputStream in) {
        this.out = out;
        this.in = new Scanner(in);
    }

    /**
     * Prints the welcome message.
     */
    void printWelcome() {
        String welcomeMessage = "Greetings, I am Orangutan.Orangutan. How may I assist you on this fine day?";
        out.println(String.join("\n", LINE, BANNER, welcomeMessage, LINE));
    }

    /**
     * Receives input from user and prints the reply.
     *
     * @param parser Orangutan.Command.OrangutanParser object, for parsing the command.
     */
    void getInput(OrangutanParser parser) {
        out.print(" > ");
        String input = this.in.nextLine();
        String output = parser.parseCommand(input);

        out.println(LINE);
        out.println(output);
        out.println(LINE);
    }
}

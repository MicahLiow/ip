package orangutan;

import java.nio.file.Path;
import java.nio.file.Paths;

import orangutan.command.Context;
import orangutan.command.Parser;

/**
 * Main class for Orangutan Chatbot.
 */
public class Orangutan {
    private Context context;
    private Ui ui;
    private Parser parser;

    /**
     * Initializes a new session.
     * Loads saved list (if any) from file, and prints welcome banner.
     *
     * @param path Path to store / load lists.
     */
    public Orangutan(String path) {
        Path filePath = Paths.get(path);
        context = new Context(); // list: null, isRunLoop: false, filePath: null
        ui = new Ui(System.out, System.in);
        parser = new Parser(context);

        context.setFilePath(filePath);

        ui.printWelcome();
        System.out.println(parser.parseCommand("init")); // sets context.isRunLoop to true if successful

    }

    /**
     * Runs user input loop.
     * Continuously retrieves user input and outputs chatbot reply, until exit conditions are met
     *      (usually via the bye command).
     */
    public void run() {
        while (context.isRunLoop()) {
            ui.getInput(parser);
        }
    }

    /**
     * Runs an Orangutan session.
     */
    public static void main(String[] args) {
        new Orangutan("./data/orangutan.txt").run();
    }
}

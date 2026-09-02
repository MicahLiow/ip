package orangutan;

import orangutan.command.OrangutanContext;
import orangutan.command.OrangutanParser;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class for Orangutan Chatbot.
 */
public class Orangutan {
    private OrangutanContext context;
    private OrangutanUi ui;
    private OrangutanParser parser;

    /**
     * Initializes a new session.
     * Loads saved list (if any) from file, and prints welcome banner.
     *
     * @param path Path to store / load lists.
     */
    public Orangutan(String path) {
        Path filePath = Paths.get(path);
        this.context = new OrangutanContext(); // list: null, isRunLoop: false, filePath: null
        this.ui = new OrangutanUi(System.out, System.in);
        this.parser = new OrangutanParser(context);

        context.setFilePath(filePath);

        ui.printWelcome();
        System.out.println(parser.parseCommand("init")); // sets context.isRunLoop to true if successful

    }

    /**
     * Runs user input loop.
     * Continuously retrieves user input and outputs chatbot reply, until exit conditions are met (usually via the bye command).
     */
    public void run() {
        while (context.getIsRunLoop()) {
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

import java.nio.file.Path;
import java.nio.file.Paths;

public class Orangutan {
    private OrangutanContext context;
    private OrangutanUi ui;
    private OrangutanParser parser;

    public Orangutan(String path) {
        Path filePath = Paths.get(path);
        this.context = new OrangutanContext(); //list: null, runLoop: false, filePath: null
        this.ui = new OrangutanUi(System.out, System.in);
        this.parser = new OrangutanParser(context);

        context.setFilePath(filePath);

        ui.printWelcome();
        System.out.println(parser.parseCommand("init")); //sets context.runLoop to true if successful

    }

    public void run() {
        while(context.getRunLoop()) {
            ui.getInput(parser);
        }
    }

    public static void main(String[] args) {
        new Orangutan("./data/orangutan.txt").run();
    }
}

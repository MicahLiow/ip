import java.io.IOException;

class OrangutanByeCommand implements OrangutanCommand{
    OrangutanByeCommand() {
    }

    public String run(OrangutanContext context) throws OrangutanException {
        try {
            context.setRunLoop(false);
            context.getStorage().writeToFile(context.getFilePath(), context.getList());
            return("Fare thee well, and may we meet again.");
        } catch (IOException e) {
            context.setRunLoop(true);
            throw new OrangutanException("Alas! I was not able to write your list to data/orangutan.txt.\n\n" +
                    "Please ensure I have access to said files and folders before we bid farewell.");
        }
    }
}

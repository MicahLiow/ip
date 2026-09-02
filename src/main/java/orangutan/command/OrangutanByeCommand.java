package orangutan.command;

import orangutan.OrangutanException;

import java.io.IOException;

<<<<<<< HEAD
class OrangutanByeCommand implements OrangutanCommand{
=======
class OrangutanByeCommand implements OrangutanCommand {
>>>>>>> branch-A-CodingStandard
    OrangutanByeCommand() {
    }

    public String run(OrangutanContext context) throws OrangutanException {
        try {
<<<<<<< HEAD
            context.setRunLoop(false);
            context.getStorage().writeToFile(context.getFilePath(), context.getList());
            return("Fare thee well, and may we meet again.");
        } catch (IOException e) {
            context.setRunLoop(true);
            throw new OrangutanException("Alas! I was not able to write your list to data/orangutan.txt.\n\n" +
                    "Please ensure I have access to said files and folders before we bid farewell.");
=======
            context.setIsRunLoop(false);
            context.getStorage().writeToFile(context.getFilePath(), context.getList());
            return ("Fare thee well, and may we meet again.");
        } catch (IOException e) {
            context.setIsRunLoop(true);
            throw new OrangutanException("Alas! I was not able to write your list to data/orangutan.txt.\n\n"
                    + "Please ensure I have access to said files and folders before we bid farewell.");
>>>>>>> branch-A-CodingStandard
        }
    }
}

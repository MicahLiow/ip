package orangutan.command;

import orangutan.chatlist.ChatList;
import orangutan.OrangutanException;

import java.io.IOException;
import java.nio.file.Files;
import java.time.format.DateTimeParseException;

class OrangutanInitCommand {
    OrangutanInitCommand() {
    }

    public String run(OrangutanContext context) throws OrangutanException {
        //initialize list from save file if one exists. If not, initialize an empty list
        //only reachable in the initial state, before user input is queried.
        //this is only reachable when context.runLoop is false, since runLoop is initialized to false
        //if loading data is a success, runLoop set to true and we start querying user for input.
        if (!context.getRunLoop()) {
            if (Files.exists(context.getFilePath())) {
                try {
                    context.setRunLoop(true);
                    context.setList(context.getStorage().readFromFile(context.getFilePath()));
                    return("");
                } catch (IOException e) {
                    context.setRunLoop(false);
                    throw new OrangutanException("Alas! I have failed to access the information previously stored in data/orangutan.txt.\n\n" +
                            "Please ensure I have access to said file before returning to me.");
                } catch (DateTimeParseException e) {
                    context.setRunLoop(false);
                    throw new OrangutanException("Alas! I do not comprehend the dates and times stored in data/orangutan.txt.\n\n" +
                            "Please ensure stored dates are of format yyyymmdd hhmm (e.g. 20260831 2359) before returning to me.");
                }
            } else {
                context.setRunLoop(true);
                context.setList(new ChatList());
                return ("");
            }
        } else {
            //if this was in a called while runLoop is true, that means the user called it
            //so orangutan pretends not to know it
            throw new OrangutanException("Alas! My simian mind is unable to comprehend your words.\n\n" +
                    "Please use words I understand: " +
                    "\"todo\", \"deadline\", \"event\", \"list\", \"delete\", \"mark\", \"unmark\", \"bye\"");
        }
    }
}

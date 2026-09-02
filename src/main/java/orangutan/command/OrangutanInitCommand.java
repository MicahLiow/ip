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
        // initialize list from save file if one exists. If not, initialize an empty list
        // only reachable in the initial state, before user input is queried.
        // this is only reachable when context.isRunLoop is false, since isRunLoop is initialized to false
        // if loading data is a success, isRunLoop set to true and we start querying user for input.
        if (!context.getIsRunLoop()) {
            if (Files.exists(context.getFilePath())) {
                try {
                    context.setIsRunLoop(true);
                    context.setList(context.getStorage().readFromFile(context.getFilePath()));
                    return ("");
                } catch (IOException e) {
                    context.setIsRunLoop(false);
                    throw new OrangutanException("Alas! I have failed to access the information previously stored in "
                            + "data/orangutan.txt.\n\n"
                            + "Please ensure I have access to said file before returning to me.");
                } catch (DateTimeParseException e) {
                    context.setIsRunLoop(false);
                    throw new OrangutanException("Alas! I do not comprehend the dates and times"
                            + "stored in data/orangutan.txt.\n\n"
                            + "Please ensure stored dates are of format yyyymmdd hhmm (e.g. 20260831 2359) "
                            + "before returning to me.");
                }
            } else {
                context.setIsRunLoop(true);
                context.setList(new ChatList());
                return ("");
            }
        } else {
            // if this was in a called while isRunLoop is true, that means the user called it
            // so orangutan pretends not to know it
            throw new OrangutanException("Alas! My simian mind is unable to comprehend your words.\n\n"
                    + "Please use words I understand: "
                    + "\"todo\", \"deadline\", \"event\", \"list\", \"delete\", \"mark\", \"unmark\", \"bye\"");
        }
    }
}

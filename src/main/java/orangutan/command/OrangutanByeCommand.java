package orangutan.command;

import orangutan.OrangutanException;

import java.io.IOException;

/**
 * Command to save list to file and exit chatbot.
 */
class OrangutanByeCommand implements OrangutanCommand {
    OrangutanByeCommand() {
    }

    /**
     * Saves list to file and exits chatbot.
     *
     * @param context OrangutanContext item storing information on the chatbot's current internal state.
     * @return Goodbye message, after successfully writing to file.
     * @throws OrangutanException When write to file fails. In this case the bot does not exit and continues to take input.
     */
    public String run(OrangutanContext context) throws OrangutanException {
        try {
            context.setIsRunLoop(false);
            context.getStorage().writeToFile(context.getFilePath(), context.getList());
            return ("Fare thee well, and may we meet again.");
        } catch (IOException e) {
            context.setIsRunLoop(true);
            throw new OrangutanException("Alas! I was not able to write your list to data/orangutan.txt.\n\n"
                    + "Please ensure I have access to said files and folders before we bid farewell.");
        }
    }
}

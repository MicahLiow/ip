package orangutan.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import orangutan.OrangutanException;

/**
 * Utility class for throwing OrangutanExceptions with the appropriate messages.
 */
class CommandErrors {
    static OrangutanException fileWriteError(String path) {
        String msg = String.format("Alas! I was not able to write your list to %s.\n\n"
                + "Please ensure I have access to said files and folders before we bid farewell.", path);
        return new OrangutanException(msg);
    }

    static OrangutanException fileReadError(String path) {
        String msg = String.format("Alas! I have failed to access the information previously stored in %s.\n\n"
                + "Please ensure I have access to said file before returning to me.", path);
        return new OrangutanException(msg);
    }

    static OrangutanException dateTimeParseError(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String msg = String.format("Alas! I do not comprehend the dates and times you have told me.\n\n"
                + "Please ensure your dates are of format %s (e.g. %s).",
                format.toLowerCase(), LocalDateTime.now().format(formatter));
        return new OrangutanException(msg);
    }

    static OrangutanException dateTimeLoadError(String path, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String msg = String.format("Alas! I do not comprehend the dates and times stored in %s.\n\n"
                + "Please ensure all dates are of format %s (e.g. %s).",
                path, format.toLowerCase(), LocalDateTime.now().format(formatter));
        return new OrangutanException(msg);
    }

    static OrangutanException emptyListError(String action) {
        String msg = String.format("Alas! There is nothing to %s.\n\n"
                + "Please add some items to the list first.", action);
        return new OrangutanException(msg);
    }

    static OrangutanException listOutOfBoundsError(int length) {
        String msg = String.format("Alas! This number is not in the list.\n\n"
                + "Please keep the index between 1 and %d (inclusive).", length);
        return new OrangutanException(msg);
    }

    static OrangutanException numberParseError(int length) {
        String msg = String.format("Alas! That is not a number. Not a number I know of, at the least.\n\n"
                + "Please input an integer between 1 and %d (inclusive).", length);
        return new OrangutanException(msg);
    }

    static OrangutanException deadlineMissingParametersError() {
        String msg = String.format("Alas! Some deadline details have not been revealed.\n\n"
                + "Please include /by in your message, along with the due date and time.");
        return new OrangutanException(msg);
    }

    static OrangutanException eventMissingParametersError() {
        String msg = String.format("Alas! Some event details have not been revealed.\n\n"
                + "Please include /from and /to in your message, along with the start and end dates and times.");
        return new OrangutanException(msg);
    }

    static OrangutanException missingTitleError(String action) {
        String msg = String.format("Alas! The name of this %s has not been revealed.\n\n"
                + "Please include the %s name.", action, action);
        return new OrangutanException(msg);
    }

    static OrangutanException missingIndexError(String action, int index) {
        String msg = String.format("Alas! I do not know which item to %s.\n\n"
                + "Please follow the unmark command with an integer between 1 and %d (inclusive).", action, index);
        return new OrangutanException(msg);
    }

    static OrangutanException unknownCommandError() {
        String msg = "Alas! My simian mind is unable to comprehend your instructions.\n\n"
                + "Please use instructions I understand: "
                + "\"todo\", \"deadline\", \"event\", \"list\", \"delete\", \"mark\", \"unmark\", \"find\", \"bye\"";
        return new OrangutanException(msg);
    }

}

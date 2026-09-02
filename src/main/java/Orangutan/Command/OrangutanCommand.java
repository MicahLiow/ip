package Orangutan.Command;

import Orangutan.OrangutanException;

/**
 * Interface for the running of commands input by the user.
 */
interface OrangutanCommand {
    /**
     * Executes this command, making changes to the chatbot's internal state as necessary.
     *
     * @param context OrangutanContext item storing information on the chatbot's current internal state.
     * @return String message, usually the chatbot's reply to the user after successfully completing the action.
     * @throws OrangutanException If the chatbot failed to complete the action. In this case it continues to take input.
     */
    String run(OrangutanContext context) throws OrangutanException;
}

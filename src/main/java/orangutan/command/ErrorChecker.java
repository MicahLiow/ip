package orangutan.command;

import orangutan.OrangutanException;

class ErrorChecker {
    static void checkListEmpty(Context context, String action) throws OrangutanException {
        if (context.getList().getLength() == 0) {
            throw CommandErrors.emptyListError(action);
        }
    }

    static void checkIndexOutOfBounds(Context context, int index) throws OrangutanException {
        if (index < 1 || index > context.getList().getLength()) {
            throw CommandErrors.listOutOfBoundsError(context.getList().getLength());
        }
    }

    /**
     * Verifies that action title exists.
     *
     * @param action String array, expected to be an (action, title) pair (e.g. {"todo", "lunch"})
     * @throws OrangutanException if title is missing.
     */
    static void checkTitleMissing(String[] action) throws OrangutanException {
        if (action.length < 2) {
            throw CommandErrors.missingTitleError(action[0]);
        }
    }

    /**
     * Verifies that action index exists.
     *
     * @param action String array, expected to be an (action, index) pair (e.g. {"mark", "1"}
     * @throws OrangutanException if index is missing.
     */
    static void verifyActionIndex(Context context, String[] action) throws OrangutanException {
        if (action.length < 2) {
            throw CommandErrors.missingIndexError(action[0], context.getList().getLength());
        }
    }
}

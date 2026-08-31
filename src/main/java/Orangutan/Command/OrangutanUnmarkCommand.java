package Orangutan.Command;

import Orangutan.OrangutanException;

class OrangutanUnmarkCommand implements OrangutanCommand{
    private final String index;

    OrangutanUnmarkCommand(String index) {
        this.index = index;
    }

    public String run(OrangutanContext context) throws OrangutanException {
        if (context.getList().getLength() == 0) {
            throw new OrangutanException("Alas! There is nothing to unmark.\n\n" +
                    "Please add some items to the list first.");
        }

        try {
            int unmarkIndex = Integer.parseInt(index);

            if (unmarkIndex < 1 || unmarkIndex > context.getList().getLength()) {
                throw new OrangutanException("Alas! This number is not in the list.\n\n" +
                        "Please keep the index between 1 and " + context.getList().getLength() + " (inclusive).");
            }

            String unmarkedItem = context.getList().unmarkItem(unmarkIndex);
            return("Brace yourself, this task has not been completed yet.\n " + unmarkedItem);
        } catch (NumberFormatException e) {
            return("Alas! That is not a number. Not a number I know of, at the least.\n\n" +
                    "Please input an integer between 1 and " + context.getList().getLength() + " (inclusive).");
        }
    }
}

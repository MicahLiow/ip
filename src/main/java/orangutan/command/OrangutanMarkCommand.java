package orangutan.command;

import orangutan.OrangutanException;

class OrangutanMarkCommand implements OrangutanCommand{
    private final String index;

    OrangutanMarkCommand(String index) {
        this.index = index;
    }

    public String run(OrangutanContext context) throws OrangutanException {
        if (context.getList().getLength() == 0) {
            throw new OrangutanException("Alas! There is nothing to mark.\n\n" +
                    "Please add some items to the list first.");
        }

        try {
            int markIndex = Integer.parseInt(index);

            if (markIndex < 1 || markIndex > context.getList().getLength()) {
                throw new OrangutanException("Alas! This number is not in the list.\n\n" +
                        "Please keep the index between 1 and " + context.getList().getLength() + " (inclusive).");
            }

            String markItem = context.getList().markItem(markIndex);
            return("My compliments, you have completed a task.\n " + markItem);
        } catch (NumberFormatException e) {
            return("Alas! That is not a number. Not a number I know of, at the least.\n\n" +
                    "Please input an integer between 1 and " + context.getList().getLength() + " (inclusive).");
        }
    }
}

package Orangutan.ChatList;

/**
 * Enum type denoting the three main types of list items: to-do, deadline, event.
 * Also contains the icon field, which is a short string depicting the item type.
 */
public enum ItemType {
    TODO ("[T]"),
    DEADLINE ("[D]"),
    EVENT ("[E]");

    public final String icon;

    ItemType(String icon) {
        this.icon = icon;
    }

    /**
     * returns an Orangutan.ChatList.ChatList.ItemType based on its icon.
     *
     * @param icon three-digit String, identical to the one found in the "icon" field (see above).
     * @return the Orangutan.ChatList.ChatList.ItemType corresponding to the icon.
     */
    public static ItemType getTypeFromIcon(String icon) {
        return switch (icon) {
            case "[T]" -> TODO;
            case "[D]" -> DEADLINE;
            case "[E]" -> EVENT;
            default -> null;
        };
    }
}

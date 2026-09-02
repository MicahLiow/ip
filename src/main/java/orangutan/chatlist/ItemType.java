package orangutan.chatlist;

public enum ItemType {
    TODO("[T]"),
    DEADLINE("[D]"),
    EVENT("[E]");

    private final String icon;

    ItemType(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

    /**
     * returns an Orangutan.ChatList.ChatList.ItemType based on its icon
     *
     * @param icon three-digit String, identical to the one found in the "icon" field (see above).
     * @return the Orangutan.ChatList.ChatList.ItemType corresponding to the icon
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

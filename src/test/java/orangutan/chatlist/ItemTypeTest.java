package orangutan.chatlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTypeTest {
    @Test
    public void todoIconTest() {
        assertEquals("[T]", ItemType.TODO.icon);
    }

    @Test
    public void deadlineIconTest() {
        assertEquals("[D]", ItemType.DEADLINE.icon);
    }

    @Test
    public void eventIconTest() {
        assertEquals("[E]", ItemType.EVENT.icon);
    }

    @Test
    public void parseTodoIconTest() {
        assertEquals(ItemType.TODO, ItemType.getTypeFromIcon("[T]"));
    }

    @Test
    public void parseDeadlineIconTest() {
        assertEquals(ItemType.DEADLINE, ItemType.getTypeFromIcon("[D]"));
    }

    @Test
    public void parseEventIconTest() {
        assertEquals(ItemType.EVENT, ItemType.getTypeFromIcon("[E]"));
    }
}

package orangutan.chatlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListItemTest {
    @Test
    public void todoTest() {
        ListItem test = new ListItem(ItemType.TODO, "abc", false);
        assertEquals("[T] [ ] abc", test.toString());
    }

    @Test
    public void deadlineTest() {
        ListItem test = new ListItem(ItemType.DEADLINE, "abc", false, "20260831 1900");
        assertEquals("[D] [ ] abc (by 2026-08-31 19:00)", test.toString());
    }

    @Test
    public void eventTest() {
        ListItem test = new ListItem(ItemType.EVENT, "abc", false, "20260831 1900", "20260831 2200");
        assertEquals("[E] [ ] abc (from 2026-08-31 19:00 to 2026-08-31 22:00)", test.toString());
    }

    @Test
    public void unCompletedTest() {
        ListItem test = new ListItem(ItemType.TODO, "abc", false);
        assertEquals("[T] [ ] abc", test.toString());
    }

    @Test
    public void completedTest() {
        ListItem test = new ListItem(ItemType.TODO, "abc", true);
        assertEquals("[T] [X] abc", test.toString());
    }

    @Test
    public void markTest() {
        ListItem test = new ListItem(ItemType.TODO, "abc", false);
        test.mark();
        assertEquals("[T] [X] abc", test.toString());
    }

    @Test
    public void unmarkTest() {
        ListItem test = new ListItem(ItemType.TODO, "abc", true);
        test.unmark();
        assertEquals("[T] [ ] abc", test.toString());
    }

    @Test
    public void parseTodoTest() {
        ListItem test = ListItem.parseLine("[T],false,abc");
        assertEquals("[T] [ ] abc", test.toString());
    }

    @Test
    public void parseDeadlineTest() {
        ListItem test = ListItem.parseLine("[D],false,abc,20260831 1700");
        assertEquals("[D] [ ] abc (by 2026-08-31 17:00)", test.toString());
    }

    @Test
    public void parseEventTest() {
        ListItem test = ListItem.parseLine("[E],false,abc,20260831 1700,20260831 1900");
        assertEquals("[E] [ ] abc (from 2026-08-31 17:00 to 2026-08-31 19:00)", test.toString());
    }

    @Test
    public void parseUncompletedTest() {
        ListItem test = ListItem.parseLine("[T],false,abc");
        assertEquals("[T] [ ] abc", test.toString());
    }

    @Test
    public void parseCompletedTest() {
        ListItem test = ListItem.parseLine("[T],true,abc");
        assertEquals("[T] [X] abc", test.toString());
    }

    @Test
    public void todoToFileTest() {
        String test = new ListItem(ItemType.TODO, "abc", false).toFile();
        assertEquals("[T],false,abc", test);
    }

    @Test
    public void deadlineToFileTest() {
        String test = new ListItem(ItemType.DEADLINE, "abc", false, "20260831 1900").toFile();
        assertEquals("[D],false,abc,20260831 1900", test);
    }

    @Test
    public void eventToFileTest() {
        String test = new ListItem(ItemType.EVENT, "abc", false, "20260831 1900", "20260831 2200")
                .toFile();
        assertEquals("[E],false,abc,20260831 1900,20260831 2200", test);
    }

    @Test
    public void uncompletedToFileTest() {
        String test = new ListItem(ItemType.TODO, "abc", false).toFile();
        assertEquals("[T],false,abc", test);
    }

    @Test
    public void completedToFileTest() {
        String test = new ListItem(ItemType.TODO, "abc", true).toFile();
        assertEquals("[T],true,abc", test);
    }
}

package orangutan.chatlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ListItemTest {
    @Test
    public void todoTest() {
        ListItem test = new ListItem(ItemType.TODO, "ab c", false);
        assertEquals("[T] [ ] ab c", test.toString());
    }

    @Test
    public void deadlineTest() {
        ListItem test = new ListItem(ItemType.DEADLINE, "ab c", false, "20260831 1900");
        assertEquals("[D] [ ] ab c (by 2026-08-31 19:00)", test.toString());
    }

    @Test
    public void eventTest() {
        ListItem test = new ListItem(ItemType.EVENT, "ab c", false, "20260831 1900", "20260831 2200");
        assertEquals("[E] [ ] ab c (from 2026-08-31 19:00 to 2026-08-31 22:00)", test.toString());
    }

    @Test
    public void unCompletedTest() {
        ListItem test = new ListItem(ItemType.TODO, "ab c", false);
        assertEquals("[T] [ ] ab c", test.toString());
    }

    @Test
    public void completedTest() {
        ListItem test = new ListItem(ItemType.TODO, "ab c", true);
        assertEquals("[T] [X] ab c", test.toString());
    }

    @Test
    public void markTest() {
        ListItem test = new ListItem(ItemType.TODO, "ab c", false);
        test.mark();
        assertEquals("[T] [X] ab c", test.toString());
    }

    @Test
    public void unmarkTest() {
        ListItem test = new ListItem(ItemType.TODO, "ab c", true);
        test.unmark();
        assertEquals("[T] [ ] ab c", test.toString());
    }

    @Test
    public void parseTodoTest() {
        ListItem test = ListItem.parseLine("[T],false,ab c");
        assertEquals("[T] [ ] ab c", test.toString());
    }

    @Test
    public void parseDeadlineTest() {
        ListItem test = ListItem.parseLine("[D],false,ab c,20260831 1700");
        assertEquals("[D] [ ] ab c (by 2026-08-31 17:00)", test.toString());
    }

    @Test
    public void parseEventTest() {
        ListItem test = ListItem.parseLine("[E],false,ab c,20260831 1700,20260831 1900");
        assertEquals("[E] [ ] ab c (from 2026-08-31 17:00 to 2026-08-31 19:00)", test.toString());
    }

    @Test
    public void parseUncompletedTest() {
        ListItem test = ListItem.parseLine("[T],false,ab c");
        assertEquals("[T] [ ] ab c", test.toString());
    }

    @Test
    public void parseCompletedTest() {
        ListItem test = ListItem.parseLine("[T],true,ab c");
        assertEquals("[T] [X] ab c", test.toString());
    }

    @Test
    public void todoToFileTest() {
        String test = new ListItem(ItemType.TODO, "ab c", false).toFile();
        assertEquals("[T],false,ab c", test);
    }

    @Test
    public void deadlineToFileTest() {
        String test = new ListItem(ItemType.DEADLINE, "ab c", false, "20260831 1900").toFile();
        assertEquals("[D],false,ab c,20260831 1900", test);
    }

    @Test
    public void eventToFileTest() {
        String test = new ListItem(ItemType.EVENT, "ab c", false, "20260831 1900", "20260831 2200")
                .toFile();
        assertEquals("[E],false,ab c,20260831 1900,20260831 2200", test);
    }

    @Test
    public void uncompletedToFileTest() {
        String test = new ListItem(ItemType.TODO, "ab c", false).toFile();
        assertEquals("[T],false,ab c", test);
    }

    @Test
    public void completedToFileTest() {
        String test = new ListItem(ItemType.TODO, "ab c", true).toFile();
        assertEquals("[T],true,ab c", test);
    }
}

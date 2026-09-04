package orangutan.chatlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class ChatListTest {
    @Test
    public void toStreamTest() {
        ListItem todo = new ListItem(ItemType.TODO, "abc", false);
        ListItem deadline = new ListItem(ItemType.DEADLINE, "abc", false, "20260831 1900");
        ListItem event = new ListItem(ItemType.EVENT, "abc", true, "20260831 1900", "20260831 2200");
        ChatList test = new ChatList(new ArrayList<ListItem>(List.of(todo, deadline, event)));
        Stream<ListItem> testStream = test.toStream();
        assertEquals(Stream.of(todo, deadline, event).toList(), testStream.toList());
    }

    @Test
    public void addItemTest() {
        ListItem deadline = new ListItem(ItemType.DEADLINE, "abc", false, "20260831 1900");
        ListItem event = new ListItem(ItemType.EVENT, "abc", true, "20260831 1900", "20260831 2200");
        ChatList test = new ChatList(new ArrayList<ListItem>(List.of(deadline)));
        test.addItem(event);
        assertEquals("1. [D] [ ] abc (by 2026-08-31 19:00)\n2. [E] [X] abc (from 2026-08-31 19:00 to 2026-08-31 22:00)",
                test.toString());
    }

    @Test
    public void deleteItemTest() {
        ListItem deadline = new ListItem(ItemType.DEADLINE, "abc", false, "20260831 1900");
        ListItem event = new ListItem(ItemType.EVENT, "abc", true, "20260831 1900", "20260831 2200");
        ChatList test = new ChatList(new ArrayList<ListItem>(List.of(deadline, event)));
        test.deleteItem(2);
        assertEquals("1. [D] [ ] abc (by 2026-08-31 19:00)", test.toString());
    }

    @Test
    public void markItemTest() {
        ListItem event = new ListItem(ItemType.EVENT, "abc", false, "20260831 1900", "20260831 2200");
        ChatList test = new ChatList(new ArrayList<ListItem>(List.of(event)));
        test.markItem(1);
        assertEquals("1. [E] [X] abc (from 2026-08-31 19:00 to 2026-08-31 22:00)", test.toString());
    }

    @Test
    public void unmarkItemTest() {
        ListItem event = new ListItem(ItemType.EVENT, "abc", true, "20260831 1900", "20260831 2200");
        ChatList test = new ChatList(new ArrayList<ListItem>(List.of(event)));
        test.unmarkItem(1);
        assertEquals("1. [E] [ ] abc (from 2026-08-31 19:00 to 2026-08-31 22:00)", test.toString());
    }

    @Test
    public void getLengthTest() {
        ListItem todo = new ListItem(ItemType.TODO, "abc", false);
        ListItem deadline = new ListItem(ItemType.DEADLINE, "abc", false, "20260831 1900");
        ListItem event = new ListItem(ItemType.EVENT, "abc", true, "20260831 1900", "20260831 2200");
        ChatList test = new ChatList(new ArrayList<ListItem>(List.of(todo, deadline, event)));
        assertEquals(3, test.getLength());
    }
}

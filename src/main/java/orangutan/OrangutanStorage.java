package orangutan;

import orangutan.chatlist.ChatList;
import orangutan.chatlist.ListItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Handles storing and loading of list data to and from file.
 */
public class OrangutanStorage {
    public OrangutanStorage() {
    }

    /**
     * Reads new ChatList from save file to context.
     *
     * @throws IOException If program does not have read access to the directory.
     */
    public ChatList readFromFile(Path filePath) throws IOException {
        ChatList list = new ChatList();

        if (Files.exists(filePath)) {
            try (Stream<String> lines = Files.lines(filePath)) {
                lines.forEach(line -> list.addItem(ListItem.parseLine(line)));
            }
        }

        return list;
    }

    /**
     * Writes ChatList from context to a save file.
     * If file already exists, overwrites current contents.
     * If file and/or directory does not exist, creates a new one at the given path.
     *
     * @throws IOException If program does not have write access.
     */
    public void writeToFile(Path filePath, ChatList list) throws IOException {

        String toWrite = list.toStream().map(item -> item.toFile()).collect(Collectors.joining("\n"));

        Files.createDirectories(filePath.getParent()); // create directory structure (does nothing if already exists)
        Files.writeString(filePath, toWrite); // write to file (creates file if does not exist)
    }
}

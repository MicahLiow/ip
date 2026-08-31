import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrangutanStorage {
    public OrangutanStorage() {
    }

    /**
     * Reads new ChatList from save file to context.
     */
    public ChatList readFromFile(Path filePath) throws IOException {
        ChatList list = new ChatList();

        if (Files.exists(filePath)) {
            try (Stream<String> lines = Files.lines(filePath)){
                lines.forEach(line -> list.addItem(ListItem.parseLine(line)));
            }
        }

        return list;
    }

    /**
    * Write ChatList from context to a save file.
    * If file already exists, will overwrite current contents.
    * If file and/or directory does not exist, will create a new one.
    */
    public void writeToFile(Path filePath, ChatList list) throws IOException {

        String toWrite = list.toStream().map(i -> i.toFile()).collect(Collectors.joining("\n"));

        Files.createDirectories(filePath.getParent()); //create directory structure (does nothing if already exists)
        Files.writeString(filePath, toWrite); //write to file (creates file if does not exist)
    }
}

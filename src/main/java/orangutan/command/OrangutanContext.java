package orangutan.command;

import orangutan.chatlist.ChatList;
import orangutan.OrangutanStorage;

import java.nio.file.Path;

/**
 * Stores important pieces of Orangutan's internal state, to be accessed and modified by other commands.
 */
public class OrangutanContext {
    private ChatList list;
    private boolean isRunLoop;
    private Path filePath;
    private OrangutanStorage storage;

    /**
     * Creates a new OrangutanContext.
     *
     * @param list List storing any list items submitted by the user.
     * @param isRunLoop Flag for whether to continue (or begin) taking user input.
     * @param filePath Path to save the list after chatbot exits, or to retrieve it when chatbot is started.
     * @param storage Utility class that manages the storing and loading of data to and from file.
     */
    public OrangutanContext(ChatList list, boolean isRunLoop, Path filePath, OrangutanStorage storage) {
        this.list = list;
        this.isRunLoop = isRunLoop;
        this.filePath = filePath;
        this.storage = storage;
    }

    /**
     * Constructs a context where isRunLoop is false, and all other fields are null.
     */
    public OrangutanContext() {
        list = null;
        isRunLoop = false;
        filePath = null;
        storage = new OrangutanStorage();
    }

    public ChatList getList() {
        return list;
    }

    public void setList(ChatList list) {
        this.list = list;
    }

    public boolean isRunLoop() {
        return isRunLoop;
    }

    public void setRunLoop(boolean isRunLoop) {
        this.isRunLoop = isRunLoop;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public OrangutanStorage getStorage() {
        return storage;
    }

    public void setStorage(OrangutanStorage storage) {
        this.storage = storage;
    }
}

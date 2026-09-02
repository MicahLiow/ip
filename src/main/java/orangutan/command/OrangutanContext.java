package orangutan.command;

import orangutan.chatlist.ChatList;
import orangutan.OrangutanStorage;

import java.nio.file.Path;

public class OrangutanContext {
    private ChatList list;
    private boolean isRunLoop;
    private Path filePath;
    private OrangutanStorage storage;

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
        this.list = null;
        this.isRunLoop = false;
        this.filePath = null;
        this.storage = new OrangutanStorage();
    }

    public ChatList getList() {
        return list;
    }

    public void setList(ChatList list) {
        this.list = list;
    }

    public boolean getIsRunLoop() {
        return isRunLoop;
    }

    public void setIsRunLoop(boolean isRunLoop) {
        this.isRunLoop = isRunLoop;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public OrangutanStorage getStorage() {
        return this.storage;
    }

    public void setStorage(OrangutanStorage storage) {
        this.storage = storage;
    }
}

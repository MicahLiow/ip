package Orangutan.Command;

import Orangutan.ChatList.ChatList;
import Orangutan.OrangutanStorage;

import java.nio.file.Path;

public class OrangutanContext {
    private ChatList list;
    private boolean runLoop;
    private Path filePath;
    private OrangutanStorage storage;

    public OrangutanContext(ChatList list, boolean runLoop, Path filePath, OrangutanStorage storage) {
        this.list = list;
        this.runLoop = runLoop;
        this.filePath = filePath;
        this.storage = storage;
    }

    /**
     * Constructs a context where runLoop is false, and all other fields are null.
     */
    public OrangutanContext() {
        this.list = null;
        this.runLoop = false;
        this.filePath = null;
        this.storage = new OrangutanStorage();
    }

    public ChatList getList() {
        return list;
    }

    public void setList(ChatList list) {
        this.list = list;
    }

    public boolean getRunLoop() {
        return runLoop;
    }

    public void setRunLoop(boolean runLoop) {
        this.runLoop = runLoop;
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

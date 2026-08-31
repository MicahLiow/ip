import java.nio.file.Path;

class OrangutanContext {
    private ChatList list;
    private boolean runLoop;
    private Path filePath;
    private OrangutanStorage storage;

    OrangutanContext(ChatList list, boolean runLoop, Path filePath, OrangutanStorage storage) {
        this.list = list;
        this.runLoop = runLoop;
        this.filePath = filePath;
        this.storage = storage;
    }

    /**
     * Constructs a context where runLoop is false, and all other fields are null.
     */
    OrangutanContext() {
        this.list = null;
        this.runLoop = false;
        this.filePath = null;
        this.storage = new OrangutanStorage();
    }

    ChatList getList() {
        return list;
    }

    void setList(ChatList list) {
        this.list = list;
    }

    boolean getRunLoop() {
        return runLoop;
    }

    void setRunLoop(boolean runLoop) {
        this.runLoop = runLoop;
    }

    Path getFilePath() {
        return filePath;
    }

    void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    OrangutanStorage getStorage() {
        return this.storage;
    }

    void setStorage(OrangutanStorage storage) {
        this.storage = storage;
    }
}

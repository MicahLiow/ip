package orangutan;

public class OrangutanException extends Exception {
    public OrangutanException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return getLocalizedMessage();
    }
}
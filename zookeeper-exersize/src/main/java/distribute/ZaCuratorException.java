package distribute;

public class ZaCuratorException extends RuntimeException {

    private static final long serialVersionUID = 8869515829960904906L;

    public ZaCuratorException() {
        super();
    }

    public ZaCuratorException(String message) {
        super(message);
    }

    public ZaCuratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZaCuratorException(Throwable cause) {
        super(cause);
    }

    protected ZaCuratorException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

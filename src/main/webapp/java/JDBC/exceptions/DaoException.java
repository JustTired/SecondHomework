package JDBC.exceptions;

public class DaoException extends RuntimeException {
    public DaoException(String str, Throwable throwable) {
        super(str + throwable);
    }
}

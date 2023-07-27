package db.excepetion;

public class DbIntegrityExcepetion extends RuntimeException {

    public DbIntegrityExcepetion(String msg) {
        super(msg);
    }
}

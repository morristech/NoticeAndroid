package lin.jiang.notice.domain.exception;

/**
 * Created by Summer on 2016/5/8.
 */
public class ResponeException extends Exception {
    public int code;
    public String msg;
    public ResponeException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

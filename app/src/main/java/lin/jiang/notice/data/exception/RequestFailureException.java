package lin.jiang.notice.data.exception;

/**
 * Created by Summer on 2016/5/5.
 */
public class RequestFailureException extends Exception {
    public int code;
    public String msg;
    public RequestFailureException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

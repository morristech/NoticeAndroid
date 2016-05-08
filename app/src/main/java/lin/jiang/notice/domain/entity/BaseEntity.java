package lin.jiang.notice.domain.entity;

/**
 * Created by Summer on 2016/5/6.
 */
public class BaseEntity {
    private boolean result;
    private String msg;

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isResult() {

        return result;
    }

    public String getMsg() {
        return msg;
    }
}

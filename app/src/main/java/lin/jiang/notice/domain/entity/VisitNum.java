package lin.jiang.notice.domain.entity;

/**
 * Created by Summer on 2016/5/10.
 */
public class VisitNum {

    /**
     * result : true
     * msg : 请求成功
     * data : {"num":14}
     */

    private boolean result;
    private String msg;
    /**
     * num : 14
     */

    private DataBean data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    @Override
    public String toString() {
        return "VisitNum{" +
                "result=" + result +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

package lin.jiang.notice.domain.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/5/6.
 */
public class CommentList implements Serializable {

    /**
     * msg : 请求成功
     * result : true
     * data : [{"tool":"nexus","userId":6,"datetime":"2016-05-06T13:51:37+00:00","id":23,"message":"efjksdkjfsdkfsbdfksf就开始的发挥科技时代风貌你","articleId":516}]
     */

    private String msg;
    private boolean result;
    /**
     * tool : nexus
     * userId : 6
     * datetime : 2016-05-06T13:51:37+00:00
     * id : 23
     * message : efjksdkjfsdkfsbdfksf就开始的发挥科技时代风貌你
     * articleId : 516
     */

    private List<Comment> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<Comment> getData() {
        return data;
    }

    public void setData(List<Comment> data) {
        this.data = data;
    }

    public static class Comment implements Serializable {
        private String tool;
        private String userId;
        private String datetime;
        private int id;
        private String message;
        private int articleId;

        public String getTool() {
            return tool;
        }

        public void setTool(String tool) {
            this.tool = tool;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getArticleId() {
            return articleId;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }
    }
}

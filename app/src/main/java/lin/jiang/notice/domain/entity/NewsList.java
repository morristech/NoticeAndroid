package lin.jiang.notice.domain.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/5/5.
 */
public class NewsList implements Serializable {

    /**
     * result : true
     * data : [{"type":1,"author":"杜锋","time":"2016-05-05T02:52:23+00:00","source":1,"url":"http://www.cdut.edu.cn/xww/news/146241376077534686.html","title":"地灾国家重点实验室2016年第一次公众开放周活动安排","id":617},{"type":1,"author":"罗岢","time":"2016-05-05T02:52:23+00:00","source":1,"url":"http://www.cdut.edu.cn/xww/news/146240986329119217.html","title":"【致青春】核自科技打造惊艳一刻","id":616},{"type":1,"author":"罗岢","time":"2016-05-05T01:52:22+00:00","source":1,"url":"http://www.cdut.edu.cn/xww/news/146240949461981930.html","title":"【致青春】有一种纽带叫\u201c商院蓝\u201d","id":615},{"type":1,"author":"罗岢","time":"2016-05-04T10:22:22+00:00","source":1,"url":"http://www.cdut.edu.cn/xww/news/146235334038588097.html","title":"【致青春】那一抹难忘的\u201c管科红\u201d","id":614},{"type":1,"author":"罗岢","time":"2016-05-04T10:22:22+00:00","source":1,"url":"http://www.cdut.edu.cn/xww/news/146235537876034394.html","title":"【致青春】是什么让我们热泪盈眶？","id":613},{"type":1,"author":"林路","time":"2016-05-04T09:52:24+00:00","source":1,"url":"http://www.cdut.edu.cn/xww/news/146234652511961136.html","title":"计划财务处开展业务学习暂停报账和收费工作的通知","id":612},{"type":1,"author":"王小波","time":"2016-05-04T09:52:23+00:00","source":1,"url":"http://www.cdut.edu.cn/xww/news/146234547666652682.html","title":"成都理工大学60周年校庆旅游与城乡规划学院庆祝活动日程公告","id":611},{"type":1,"author":"罗岢","time":"2016-05-04T08:52:22+00:00","source":1,"url":"http://www.cdut.edu.cn/xww/news/146225008876029229.html","title":"【院长访谈录】李璞：聚焦\u201c三强\u201d目标","id":610},{"type":1,"author":"林路","time":"2016-05-04T07:52:22+00:00","source":1,"url":"http://www.cdut.edu.cn/xww/news/146232263646394344.html","title":"计划财务处关于\u201c往年未冲销借款\u201d相关事宜的通知","id":609},{"type":1,"author":"周庆磊","time":"2016-05-04T07:52:22+00:00","source":1,"url":"http://www.cdut.edu.cn/xww/news/146226616311964662.html","title":"成都理工大学60周年校庆地球科学学院庆典公告","id":608}]
     * msg : 请求成功
     */

    private boolean result;
    private String msg;
    /**
     * type : 1
     * author : 杜锋
     * time : 2016-05-05T02:52:23+00:00
     * source : 1
     * url : http://www.cdut.edu.cn/xww/news/146241376077534686.html
     * title : 地灾国家重点实验室2016年第一次公众开放周活动安排
     * id : 617
     */

    private List<News> data;

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

    public List<News> getData() {
        return data;
    }

    public void setData(List<News> data) {
        this.data = data;
    }

    public static class News implements Serializable {
        private String type;
        private String author;
        private String time;
        private String source;
        private String url;
        private String title;
        private int id;
        private String img;

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg() {
            return img;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "News{" +
                    "type='" + type + '\'' +
                    ", author='" + author + '\'' +
                    ", time='" + time + '\'' +
                    ", source='" + source + '\'' +
                    ", url='" + url + '\'' +
                    ", title='" + title + '\'' +
                    ", id=" + id +
                    ", img='" + img + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsList{" +
                "result=" + result +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

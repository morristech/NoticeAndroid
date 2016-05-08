package lin.jiang.notice.domain.entity;

import java.io.Serializable;

/**
 * Created by Summer on 2016/5/6.
 */
public class NewsDetail implements Serializable {

    /**
     * msg : 请求成功
     * result : true
     * data : {"content":{"id":412,"content":"[{\"align\": \"center\", \"content\": \"关于\u201c两学一做\u201d\u2014\u2014学《准则》《条例》专题文艺汇演第一轮初选的通知\"}, {\"content\": \"各基层党委（党总支）：\"}, {\"content\": \"根据《学习宣传〈中国共产党廉洁自律准则〉和〈中国共产党纪律处分条例〉相关活动实施方案》要求，\u201c两学一做\u201d\u2014\u2014学《准则》《条例》专题文艺汇演将于5月5日在学校俱乐部进行第一轮节目初选，现将有关事项通知如下：\"}, {\"content\": \"一、微视频类节目需于5月4日下午5点前将节目视频提交至宣传部307办公室；\"}, {\"content\": \"二、语言类节目演出单位需自行准备7份纸质版台词带到初选现场，供评委现场根据舞台表现提出修改意见，台词电子版于5月3日下午5点前发送至85026759@qq.com；\"}, {\"content\": \"三、各节目所需伴奏音乐、道具、服装自行准备（初选可不着演出服装），伴奏音乐请与5月3日下午5点前提交至校团委103办公室；\"}, {\"content\": \"联系人：顾华宁 李佳\"}, {\"content\": \"联系电话：84078928  84078762\"}, {\"align\": \"right\", \"content\": \"党委宣传部 校团委\"}, {\"align\": \"right\", \"content\": \"2016年4月27日\"}]","dateTime":"2016-04-27T06:14:35+00:00"},"article":{"url":"http://www.cdut.edu.cn/xww/news/146173775665091572.html","author":"顾华宁","title":"关于\u201c两学一做\u201d\u2014\u2014学《准则》《条例》专题文艺汇演第一轮初选的通知","id":575,"time":"2016-04-27T06:32:12+00:00","source":1,"type":1},"file":{"id":59,"url":"http://www.cdut.edu.cn/xww/UserFiles/File/20160427/20160427141548_624.doc","type":"doc","name":"专题文艺汇演节目汇总表"}}
     */

    private String msg;
    private boolean result;
    /**
     * content : {"id":412,"content":"[{\"align\": \"center\", \"content\": \"关于\u201c两学一做\u201d\u2014\u2014学《准则》《条例》专题文艺汇演第一轮初选的通知\"}, {\"content\": \"各基层党委（党总支）：\"}, {\"content\": \"根据《学习宣传〈中国共产党廉洁自律准则〉和〈中国共产党纪律处分条例〉相关活动实施方案》要求，\u201c两学一做\u201d\u2014\u2014学《准则》《条例》专题文艺汇演将于5月5日在学校俱乐部进行第一轮节目初选，现将有关事项通知如下：\"}, {\"content\": \"一、微视频类节目需于5月4日下午5点前将节目视频提交至宣传部307办公室；\"}, {\"content\": \"二、语言类节目演出单位需自行准备7份纸质版台词带到初选现场，供评委现场根据舞台表现提出修改意见，台词电子版于5月3日下午5点前发送至85026759@qq.com；\"}, {\"content\": \"三、各节目所需伴奏音乐、道具、服装自行准备（初选可不着演出服装），伴奏音乐请与5月3日下午5点前提交至校团委103办公室；\"}, {\"content\": \"联系人：顾华宁 李佳\"}, {\"content\": \"联系电话：84078928  84078762\"}, {\"align\": \"right\", \"content\": \"党委宣传部 校团委\"}, {\"align\": \"right\", \"content\": \"2016年4月27日\"}]","dateTime":"2016-04-27T06:14:35+00:00"}
     * article : {"url":"http://www.cdut.edu.cn/xww/news/146173775665091572.html","author":"顾华宁","title":"关于\u201c两学一做\u201d\u2014\u2014学《准则》《条例》专题文艺汇演第一轮初选的通知","id":575,"time":"2016-04-27T06:32:12+00:00","source":1,"type":1}
     * file : {"id":59,"url":"http://www.cdut.edu.cn/xww/UserFiles/File/20160427/20160427141548_624.doc","type":"doc","name":"专题文艺汇演节目汇总表"}
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 412
         * content : [{"align": "center", "content": "关于“两学一做”——学《准则》《条例》专题文艺汇演第一轮初选的通知"}, {"content": "各基层党委（党总支）："}, {"content": "根据《学习宣传〈中国共产党廉洁自律准则〉和〈中国共产党纪律处分条例〉相关活动实施方案》要求，“两学一做”——学《准则》《条例》专题文艺汇演将于5月5日在学校俱乐部进行第一轮节目初选，现将有关事项通知如下："}, {"content": "一、微视频类节目需于5月4日下午5点前将节目视频提交至宣传部307办公室；"}, {"content": "二、语言类节目演出单位需自行准备7份纸质版台词带到初选现场，供评委现场根据舞台表现提出修改意见，台词电子版于5月3日下午5点前发送至85026759@qq.com；"}, {"content": "三、各节目所需伴奏音乐、道具、服装自行准备（初选可不着演出服装），伴奏音乐请与5月3日下午5点前提交至校团委103办公室；"}, {"content": "联系人：顾华宁 李佳"}, {"content": "联系电话：84078928  84078762"}, {"align": "right", "content": "党委宣传部 校团委"}, {"align": "right", "content": "2016年4月27日"}]
         * dateTime : 2016-04-27T06:14:35+00:00
         */

        private ContentBean content;
        /**
         * url : http://www.cdut.edu.cn/xww/news/146173775665091572.html
         * author : 顾华宁
         * title : 关于“两学一做”——学《准则》《条例》专题文艺汇演第一轮初选的通知
         * id : 575
         * time : 2016-04-27T06:32:12+00:00
         * source : 1
         * type : 1
         */

        private ArticleBean article;
        /**
         * id : 59
         * url : http://www.cdut.edu.cn/xww/UserFiles/File/20160427/20160427141548_624.doc
         * type : doc
         * name : 专题文艺汇演节目汇总表
         */

        private FileBean file;

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public ArticleBean getArticle() {
            return article;
        }

        public void setArticle(ArticleBean article) {
            this.article = article;
        }

        public FileBean getFile() {
            return file;
        }

        public void setFile(FileBean file) {
            this.file = file;
        }

        public static class ContentBean implements Serializable {
            private int id;
            private String content;
            private String dateTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDateTime() {
                return dateTime;
            }

            public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
            }
        }

        public static class ArticleBean implements Serializable {
            private String url;
            private String author;
            private String title;
            private int id;
            private String time;
            private int source;
            private int type;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
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

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getSource() {
                return source;
            }

            public void setSource(int source) {
                this.source = source;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class FileBean implements Serializable {
            private int id;
            private String url;
            private String type;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}

package lin.jiang.notice.domain.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 2016/5/6.
 */
public class PicList implements Serializable {

    /**
     * msg : 请求成功
     * result : true
     * data : [{"img":"http://www.cdut.edu.cn/style/images/focus.jpg","target":"http://photo.cdut.edu.cn/photo/index.php?c=34"},{"img":"http://www.cdut.edu.cn/style/images/focus1.jpg","target":"http://mp.weixin.qq.com/s?timestamp=1461809191&src=3&ver=1&signature=9DuXlwArphoUzXmLRnpBtw3K0bPh*gqIpgCQ1Sx2CE9gZdLhk-7ySRgN6O2zwRuyO93LRVxXdpzxPycKT*IHkBMTWp9*k*svzAX1fAuDxHhVBxkZ-9R0UftCKv55gdNGcyH1p2Pt5mHjQvG-*IhqUPqeDZCKfiEHVsR-KVVUj7I="},{"img":"http://www.cdut.edu.cn/style/images/focus2.jpg","target":"http://www.cdut.edu.cn/xww/news/146131083319774679.html"},{"img":"http://www.cdut.edu.cn/style/images/focus3.jpg","target":"http://www.cdut.edu.cn/xww/news/146156208911926524.html"},{"img":"http://www.cdut.edu.cn/style/images/focus4.jpg","target":"http://720yun.com/t/69722wOdata?pano_id=223340"}]
     */

    private String msg;
    private boolean result;
    /**
     * img : http://www.cdut.edu.cn/style/images/focus.jpg
     * target : http://photo.cdut.edu.cn/photo/index.php?c=34
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private String img;
        private String target;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }
    }
}

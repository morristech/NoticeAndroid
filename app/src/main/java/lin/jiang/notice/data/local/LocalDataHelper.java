package lin.jiang.notice.data.local;

import lin.jiang.notice.App;
import lin.jiang.notice.domain.entity.NewsList;
import lin.jiang.notice.util.L;
import lin.jiang.notice.util.StringUtil;

public class LocalDataHelper {

    public static final String NEWS_LIST_HOT = "news_list_hot";
    public static final String NEWS_LIST_LATEST = "news_list_latest";
    public static final String NEWS_LIST_01 = "news_list_01";
    public static final String NEWS_LIST_03 = "news_list_03";

    public static void saveNewsList(String _type, String _source, NewsList list) {
        if (StringUtil.isBlank(_type) && StringUtil.isBlank(_source)) {
            L.d("saveNewsList: NEWS_LIST_LATEST");
            LocalUtil.putObject(App.instance(), NEWS_LIST_LATEST, list);
            return;
        }
        if (!StringUtil.isBlank(_type)) {
            if ("1".equals(_type)) {
                L.d("saveNewsList: NEWS_LIST_01");
                LocalUtil.putObject(App.instance(), NEWS_LIST_01, list);
            } else if ("3".equals(_type) || "2".equals(_type)) {
                L.d("saveNewsList: NEWS_LIST_02/3");
                LocalUtil.putObject(App.instance(), NEWS_LIST_03, list);
            }
        }
    }

    public static NewsList getNewsList(String _type, String _source) {
        NewsList newsList = null;
        if (StringUtil.isBlank(_type) && StringUtil.isBlank(_source)) {
            Object object = LocalUtil.getObject(App.instance(), NEWS_LIST_LATEST);
            if (object != null) {
                newsList = (NewsList) object;
            }
            return newsList;
        }
        if (!StringUtil.isBlank(_type)) {
            if ("1".equals(_type)) {
                Object object = LocalUtil.getObject(App.instance(), NEWS_LIST_01);
                if (object != null) {
                    newsList = (NewsList) object;
                }
                return newsList;
            } else if ("3".equals(_type) || "2".equals(_type)) {
                Object object = LocalUtil.getObject(App.instance(), NEWS_LIST_03);
                if (object != null) {
                    newsList = (NewsList) object;
                }
                return newsList;
            }
        }
        return null;
    }

    public static void saveNewsHot(NewsList list) {
        LocalUtil.putObject(App.instance(), NEWS_LIST_HOT, list);
    }

    public static NewsList getNewsHot() {
        NewsList newsList = null;
        Object object = LocalUtil.getObject(App.instance(), NEWS_LIST_HOT);
        if (object != null) {
            newsList = (NewsList) object;
        }
        return newsList;
    }

}

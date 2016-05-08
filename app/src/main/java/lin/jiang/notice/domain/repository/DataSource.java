package lin.jiang.notice.domain.repository;

import lin.jiang.notice.domain.entity.NewsDetail;
import lin.jiang.notice.domain.entity.NewsList;

public interface DataSource {

    interface Callback<T> {
        public static final int CODE_IO = 1101;
        public static final int CODE_LOCAL_CACHE = 1102;


        void onSuccess(T data);

        void onError(int code,String msg);
    }

    void getNewsList(boolean force, String _type, String _source, String _startId, String _pageNum, Callback<NewsList> callback);

    void getNewsHot(boolean force, Callback<NewsList> callback);

    void getNewsDetail(String aid, Callback<NewsDetail> callback);
}
package lin.jiang.notice.domain.repository;

import lin.jiang.notice.domain.entity.BaseEntity;
import lin.jiang.notice.domain.entity.CommentList;
import lin.jiang.notice.domain.entity.NewsDetail;
import lin.jiang.notice.domain.entity.NewsList;
import lin.jiang.notice.domain.entity.VisitNum;

public interface DataSource {

    interface Callback<T> {
        public static final int CODE_IO = 1101;
        public static final int CODE_LOCAL_CACHE = 1102;


        void onSuccess(T data);

        void onError(int code, String msg);
    }

    void getNewsList(boolean force, String _type, String _source, String _startId, String _pageNum, Callback<NewsList> callback);

    void getNewsHot(boolean force, Callback<NewsList> callback);

    void getNewsDetail(String aid, Callback<NewsDetail> callback);

    void getVisitNum(String aid, Callback<VisitNum> callback);

    void getCommentList(String _articleId, String _startId, String _pageNum, Callback<CommentList> callback);

    void addComment(String _articleId, String _tool, String _msg, Callback<BaseEntity> callback);

    void search(String _param, String _pageNum, Callback<NewsList> callback);
}
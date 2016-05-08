package lin.jiang.notice.data;

import java.io.IOException;

import lin.jiang.notice.data.exception.RequestFailureException;
import lin.jiang.notice.data.local.LocalDataHelper;
import lin.jiang.notice.data.remote.Api;
import lin.jiang.notice.domain.entity.NewsDetail;
import lin.jiang.notice.domain.entity.NewsList;
import lin.jiang.notice.domain.repository.DataSource;

public class DataSourceImpl implements DataSource {

    @Override
    public void getNewsList(boolean force, String _type, String _source, String _startId, String _pageNum, Callback<NewsList> callback) {
        NewsList newsList = null;
        if (force) {
            try {
                newsList = Api.instance().getNewsList(_type, _source, _startId, _pageNum);
                callback.onSuccess(newsList);
                if (_startId == null && newsList != null) {
                    LocalDataHelper.saveNewsList(_type, _source, newsList);
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.onError(Callback.CODE_IO, "IOException");
            } catch (RequestFailureException e) {
                e.printStackTrace();
                callback.onError(e.code, e.msg);
            }
        } else {
            Object object = LocalDataHelper.getNewsList(_type, _source);
            if (object != null) {
                newsList = (NewsList) object;
                callback.onSuccess(newsList);
            } else {
                callback.onError(Callback.CODE_LOCAL_CACHE, "本地无缓存");
            }
        }
    }

    @Override
    public void getNewsHot(boolean force, Callback<NewsList> callback) {
        NewsList newsList = null;
        if (force) {
            try {
                newsList = Api.instance().getNewsHot();
                callback.onSuccess(newsList);
                if (newsList != null) {
                    LocalDataHelper.saveNewsHot(newsList);
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.onError(Callback.CODE_IO, "IOException");
            } catch (RequestFailureException e) {
                e.printStackTrace();
                callback.onError(e.code, e.msg);
            }
        } else {
            Object object = LocalDataHelper.getNewsHot();
            if (object != null) {
                newsList = (NewsList) object;
                callback.onSuccess(newsList);
            } else {
                callback.onError(Callback.CODE_LOCAL_CACHE, "本地无缓存");
            }
        }
    }

    @Override
    public void getNewsDetail(String aid, Callback<NewsDetail> callback) {
        NewsDetail newsDetail = null;
        try {
            newsDetail = Api.instance().getNewsDetail(aid);
            callback.onSuccess(newsDetail);
        } catch (IOException e) {
            e.printStackTrace();
            callback.onError(Callback.CODE_IO, "IOException");
        } catch (RequestFailureException e) {
            e.printStackTrace();
            callback.onError(e.code, e.msg);
        }
    }
}

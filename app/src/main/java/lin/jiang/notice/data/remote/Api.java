package lin.jiang.notice.data.remote;

import java.io.IOException;

import lin.jiang.notice.App;
import lin.jiang.notice.data.exception.RequestFailureException;
import lin.jiang.notice.domain.entity.BaseEntity;
import lin.jiang.notice.domain.entity.CommentList;
import lin.jiang.notice.domain.entity.NewsDetail;
import lin.jiang.notice.domain.entity.NewsList;
import lin.jiang.notice.domain.entity.PicList;
import lin.jiang.notice.util.DeviceUtil;
import lin.jiang.notice.util.L;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Summer on 2016/5/5.
 */
public class Api {
    public static final String BASE_URL = "http://115.159.63.67:8000/";
    private static Api api;
    private Retrofit retrofit;

    public Api() {
        retrofit = getRetrofit();
    }

    /**
     * 获取信息列表
     */
    public interface NewsListApi {
        @GET("news/list/")
        Call<NewsList> getNewsList(@Query("_type") String _type, @Query("_source") String _source, @Query("_startId") String _startId, @Query("_pageNum") String _pageNum);
    }

    /**
     * 获取推荐/热门信息
     */
    public interface NewsHotlApi {
        @GET("news/hot/")
        Call<NewsList> getNewsHot();
    }

    /**
     * 获取信息详情
     */
    public interface NewsDetailApi {
        @GET("news/")
        Call<NewsDetail> getNewsDetail(@Query("_aid") String _aid);
    }

    /**
     * 搜索信息
     */
    public interface NewsSearchlApi {
        @GET("news/search/")
        Call<NewsList> searchNews(@Query("_param") String _param, @Query("_pageNum") String _pageNum);
    }

    /**
     * 添加评论
     */
    public interface AddCommentApi {
        @GET("comment/")
        Call<BaseEntity> addComment(@Query("_articleId") String _articleId, @Query("_tool") String _tool, @Query("_msg") String _msg);
    }

    /**
     * 获取评论列表
     */
    public interface CommentListApi {
        @GET("comment/list/")
        Call<CommentList> getCommentList(@Query("_articleId") String _articleId, @Query("_userId") String _userId, @Query("_startId") String _startId, @Query("_pageNum") String _pageNum);
    }

    /**
     * 获取图片列表
     */
    public interface PicApi {
        @GET("pic/")
        Call<PicList> getPicList();
    }

    synchronized public static Api instance() {
        if (api == null) {
            api = new Api();
        }
        return api;
    }

    private Retrofit getRetrofit() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("ID", App.instance().getDeviceId())
                        .addHeader("PLATFORM", DeviceUtil.getPlatform())
                        .addHeader("MODEL", DeviceUtil.getModel())
                        .build();
                long t1 = System.nanoTime();
                Response response = chain.proceed(request);
                long t2 = System.nanoTime();

                double time = (t2 - t1) / 1e6d;
                L.i("*************************************************************************");
                L.d("url: " + request.url());
                L.d("header: " + request.headers());
                L.d(time + "ms");
                L.d("response: " + response.toString());
                L.i("***************************************************************************");
                return response;
            }
        };
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit1;
    }

    /**
     * @param _type    可选：1新闻2公告3通知
     * @param _source  可选：1index2图书馆3教务处5信科院
     * @param _startId  可选：分页起始ID
     * @param _pageNum  可选：每页数量
     * @return
     * @throws IOException
     * @throws RequestFailureException
     */
    public NewsList getNewsList(String _type, String _source, String _startId, String _pageNum) throws IOException, RequestFailureException {
        retrofit2.Response<NewsList> response = retrofit.create(NewsListApi.class).getNewsList(_type, _source, _startId, _pageNum).execute();
        if (response.isSuccessful()) {
            L.d(response.body());
            return response.body();
        } else {
            throw new RequestFailureException(response.code(), response.message());
        }
    }

    /**
     * 根据评论和访问量综合
     * @return
     * @throws IOException
     * @throws RequestFailureException
     */
    public NewsList getNewsHot() throws IOException, RequestFailureException {
        retrofit2.Response<NewsList> response = retrofit.create(NewsHotlApi.class).getNewsHot().execute();
        if (response.isSuccessful()) {
            L.d(response.body());
            return response.body();
        } else {
            throw new RequestFailureException(response.code(), response.message());
        }
    }

    /**
     *
     * @param _aid  必填
     * @return
     * @throws IOException
     * @throws RequestFailureException
     */
    public NewsDetail getNewsDetail(String _aid) throws IOException, RequestFailureException {
        retrofit2.Response<NewsDetail> response = retrofit.create(NewsDetailApi.class).getNewsDetail(_aid).execute();
        if (response.isSuccessful()) {
            L.d(response.body());
            return response.body();
        } else {
            throw new RequestFailureException(response.code(), response.message());
        }
    }

    /**
     *
     * @param _param    必填：关键词
     * @param _pageNum  可选：分页数
     * @return
     * @throws IOException
     * @throws RequestFailureException
     */
    public NewsList searchNews(String _param, String _pageNum) throws IOException, RequestFailureException {
        retrofit2.Response<NewsList> response = retrofit.create(NewsSearchlApi.class).searchNews(_param, _pageNum).execute();
        if (response.isSuccessful()) {
            L.d(response.body());
            return response.body();
        } else {
            throw new RequestFailureException(response.code(), response.message());
        }
    }

    /**
     *
     * @param _articleId    必填：信息ID
     * @param _tool 可选：
     * @param _msg  必填：评论内容
     * @return
     * @throws IOException
     * @throws RequestFailureException
     */
    public BaseEntity addComment(String _articleId, String _tool, String _msg) throws IOException, RequestFailureException {
        retrofit2.Response<BaseEntity> response = retrofit.create(AddCommentApi.class).addComment(_articleId, _tool, _msg).execute();
        if (response.isSuccessful()) {
            L.d(response.body());
            return response.body();
        } else {
            throw new RequestFailureException(response.code(), response.message());
        }
    }

    /**
     *
     * @param _articleId    可选：某篇信息的ID
     * @param _userId   可选：指定某个User的所有评论，建议在_articleId为null时传入该参数！
     * @param _startId  可选：分页起始ID
     * @param _pageNum  可选：分页数
     * @return
     * @throws IOException
     * @throws RequestFailureException
     */
    public CommentList getCommentList(String _articleId, String _userId, String _startId, String _pageNum) throws IOException, RequestFailureException {
        retrofit2.Response<CommentList> response = retrofit.create(CommentListApi.class).getCommentList(_articleId, _userId, _startId, _pageNum).execute();
        if (response.isSuccessful()) {
            L.d(response.body());
            return response.body();
        } else {
            throw new RequestFailureException(response.code(), response.message());
        }
    }

    public PicList getPicList() throws IOException, RequestFailureException {
        retrofit2.Response<PicList> response = retrofit.create(PicApi.class).getPicList().execute();
        if (response.isSuccessful()) {
            L.d(response.body());
            return response.body();
        } else {
            throw new RequestFailureException(response.code(), response.message());
        }
    }
}

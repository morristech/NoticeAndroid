package lin.jiang.notice.presentation.newsdetail;

import android.app.Activity;

import lin.jiang.notice.domain.entity.NewsDetail;
import lin.jiang.notice.domain.exception.ResponeException;
import lin.jiang.notice.domain.excutor.UseCaseHandler;
import lin.jiang.notice.domain.interactor.CommentListUseCase;
import lin.jiang.notice.domain.interactor.CommentUseCase;
import lin.jiang.notice.domain.interactor.NewsDetailUseCase;
import lin.jiang.notice.domain.interactor.UseCase;
import lin.jiang.notice.domain.interactor.VisitNumUseCase;
import lin.jiang.notice.util.DeviceUtil;
import lin.jiang.notice.util.InjectUtil;

public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View mView;
    private final NewsDetailUseCase mNewsDetailUseCase;
    private final VisitNumUseCase mVisitNumUseCase;
    private final CommentListUseCase mCommentListUseCase;
    private final CommentUseCase mCommentUseCase;
    private int id;

    public DetailPresenter(int aid, DetailContract.View mView) {
        id = aid;
        this.mView = mView;
        this.mView.setPresenter(this);
        mNewsDetailUseCase = new NewsDetailUseCase(InjectUtil.getDataSource());
        mVisitNumUseCase = new VisitNumUseCase(InjectUtil.getDataSource());
        mCommentListUseCase = new CommentListUseCase(InjectUtil.getDataSource());
        mCommentUseCase = new CommentUseCase(InjectUtil.getDataSource());
    }

    @Override
    public void getDetailJson() {
        NewsDetailUseCase.RequestValues requestValues = new NewsDetailUseCase.RequestValues(id);
        UseCaseHandler.getInstance().execute(mNewsDetailUseCase, requestValues, new UseCase.UseCaseCallback<NewsDetailUseCase.ResponseValue>() {
            @Override
            public void onSuccess(NewsDetailUseCase.ResponseValue response) {
                mView.notifyDetailJsonSuccess(response.newsDetail);
            }

            @Override
            public void onError(ResponeException e) {
//                if (e.code == 1101 || e.code == 1103) {
//                    mView.showErrorView();
//                } else{
                mView.showHint(e.msg);
//                }
            }
        });
    }

    @Override
    public void loadHtml(boolean isOrigin) {
        if (isOrigin) {

            return;
        }
        mView.getWebView().loadUrl("http://115.159.63.67:8000/news/html/?_aid=" + id);
    }

    @Override
    public void getVisitNumJson() {
        VisitNumUseCase.RequestValues requestValues = new VisitNumUseCase.RequestValues(id);
        UseCaseHandler.getInstance().execute(mVisitNumUseCase, requestValues, new UseCase.UseCaseCallback<VisitNumUseCase.ResponseValue>() {
            @Override
            public void onSuccess(VisitNumUseCase.ResponseValue response) {
                if (response.visitNum.isResult()) {
                    mView.notifyVisitNumJsonSuccess(response.visitNum);
                }
            }

            @Override
            public void onError(ResponeException e) {
            }
        });
    }

    @Override
    public void loadCommentListJson() {
        CommentListUseCase.RequestValues requestValues = new CommentListUseCase.RequestValues("" + id, null);
        UseCaseHandler.getInstance().execute(mCommentListUseCase, requestValues, new UseCase.UseCaseCallback<CommentListUseCase.ResponseValue>() {
            @Override
            public void onSuccess(CommentListUseCase.ResponseValue response) {
                if (response.commentList.isResult())
                    mView.notifyCommentListJsonSuccess(response.commentList.getData());
            }

            @Override
            public void onError(ResponeException e) {

            }
        });
    }

    @Override
    public void addComment(String msg) {
        CommentUseCase.RequestValues requestValues = new CommentUseCase.RequestValues("" + id, DeviceUtil.getModel(), msg);
        UseCaseHandler.getInstance().execute(mCommentUseCase, requestValues, new UseCase.UseCaseCallback<CommentUseCase.ResponseValue>() {
            @Override
            public void onSuccess(CommentUseCase.ResponseValue response) {
                if (response.baseEntity.isResult())
                    mView.notifyAddCommentSuccess();
                else
                    mView.showHint(response.baseEntity.getMsg());
            }

            @Override
            public void onError(ResponeException e) {
                mView.showHint(e.msg);
            }
        });
    }

    @Override
    public void doShare(Activity activity,NewsDetail newsDetail) {
        if (newsDetail == null ||!newsDetail.isResult()|| newsDetail.getData()==null) return;
        String content = newsDetail.getData().getContent().getContent();
        if (content.length()>30) {
            content = newsDetail.getData().getContent().getContent().substring(0, 29);
        }
        ShareBoardActivity.enter(activity,new ShareBoardActivity.ShareData(newsDetail.getData().getArticle().getTitle(),
                content,null,"http://115.159.63.67:8000/news/html/?_aid=" + id));
    }

    @Override
    public void seeFile() {

    }

    @Override
    public void initData() {
        getDetailJson();
        getVisitNumJson();
        loadCommentListJson();
        loadHtml(false);
    }
}

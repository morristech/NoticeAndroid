package lin.jiang.notice.presentation.newsdetail;

import android.app.Activity;
import android.webkit.WebView;

import java.util.List;

import lin.jiang.notice.domain.entity.CommentList;
import lin.jiang.notice.domain.entity.NewsDetail;
import lin.jiang.notice.domain.entity.VisitNum;
import lin.jiang.notice.presentation.base.BasePresenter;
import lin.jiang.notice.presentation.base.BaseView;

/**
 * Created by Summer on 2016/5/7.
 */
public class DetailContract {

    interface Presenter extends BasePresenter {
        void getDetailJson();
        void loadHtml(boolean isOrigin);
        void getVisitNumJson();
        void loadCommentListJson();
        void addComment(String msg);
        void doShare(Activity activity, NewsDetail newsDetail);
        void seeFile();
    }

    interface View extends BaseView<Presenter> {
        void showLoadingView();
        void showErrorView();
        void showNoneView();
        void showDataView();

        void showOriginView();
        void showAddCommentView();
        void showFileView();

        void notifyDetailJsonSuccess(NewsDetail newsDetail);
        void notifyCommentListJsonSuccess(List<CommentList.Comment> commentList);
        void notifyVisitNumJsonSuccess(VisitNum visitNum);
        void notifyAddCommentSuccess();
        void notifyShareSuccess();
        void showHint(String msg);

        WebView getWebView();
    }
}

package lin.jiang.notice.presentation.newsdetail;

/**
 * Created by Summer on 2016/5/7.
 */
public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View mView;

    public DetailPresenter(DetailContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void getDetailJson() {

    }

    @Override
    public void loadHtml(boolean isOrigin) {

    }

    @Override
    public void getVisitNumJson() {

    }

    @Override
    public void loadCommentListJson() {

    }

    @Override
    public void addComment() {

    }

    @Override
    public void doShare() {

    }

    @Override
    public void seeFile() {

    }

    @Override
    public void initData() {

    }
}

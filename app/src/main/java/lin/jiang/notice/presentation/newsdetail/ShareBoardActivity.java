package lin.jiang.notice.presentation.newsdetail;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

import java.io.Serializable;

import lin.jiang.notice.R;
import lin.jiang.notice.presentation.base.BaseActivtiy;
import lin.jiang.notice.util.StringUtil;

public class ShareBoardActivity extends BaseActivtiy implements OnClickListener {

    public static class ShareData implements Serializable {
        public String title;
        public String content;// 限定30字
        public String img;
        public String url;

        public ShareData(String title, String content, String img, String url) {
            this.title = title;
            this.content = content;
            this.img = img;
            this.url = url;
        }
    }

    private UMImage UMimg;
    private ShareData shareData;

    public static void enter(Activity activity,ShareData shareData) {
        Intent intent = new Intent(activity, ShareBoardActivity.class);
        intent.putExtra("DATA", shareData);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_share_borad);
        getArea();
        getIntentData();
        findViewById(R.id.share_cancel).setOnClickListener(this);
        findViewById(R.id.share_wx).setOnClickListener(this);
        findViewById(R.id.share_pyq).setOnClickListener(this);
        findViewById(R.id.share_qq).setOnClickListener(this);
        findViewById(R.id.share_wb).setOnClickListener(this);
        findViewById(R.id.share_url).setOnClickListener(this);
    }

    private void getIntentData() {
        shareData = (ShareData) getIntent().getSerializableExtra("DATA");
        if (shareData==null) throw new IllegalArgumentException("ShareData can not be null!");
        String img = shareData.img;
        if (StringUtil.isEmpty(img)) {
            UMimg = new UMImage(this, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        } else if (!img.startsWith("http")) {
            try {
                UMimg = new UMImage(this, BitmapFactory.decodeFile(img));
            } catch (Exception e) {
                UMimg = new UMImage(this, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            }

        } else {
            UMimg = new UMImage(this, img);
        }
    }

    /**
     * 设置底部弹出框的大小
     */
    @SuppressWarnings("deprecation")
    public void getArea() {
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Log.d("result", "onActivityResult");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_cancel:
                onBackPressed();
                break;
            case R.id.share_wx:
                doShare(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.share_pyq:
                doShare(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            case R.id.share_qq:
                doShare(SHARE_MEDIA.QQ);
                break;
            case R.id.share_wb:
                doShare(SHARE_MEDIA.SINA);
                break;
            case R.id.share_url:
                doCopyURL();
                break;
        }
    }

    private void doCopyURL() {
        ClipboardManager cbm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("url", "" + shareData.url);
        cbm.setPrimaryClip(clipData);
        toast("成功复制到剪切板");
        onBackPressed();
    }

    private void doShare(SHARE_MEDIA platform) {
        String title = shareData.title;
        String text = shareData.content;
        ShareAction action = new ShareAction(this);
        action.setPlatform(platform).setCallback(listener);
        if (platform == SHARE_MEDIA.SINA) {// title设置无效，url需要放在内容
            text += " " + shareData.url;
            action
                    .withText(text)
                    .withMedia(UMimg);
        } else if (platform == SHARE_MEDIA.QQ) {// 必须包含title
            action
                    .withText(text)
                    .withMedia(UMimg)
                    .withTargetUrl(shareData.url)
                    .withTitle(title);
        } else if (platform == SHARE_MEDIA.WEIXIN) {// 可以存在title
            action
                    .withText(text)
                    .withMedia(UMimg)
                    .withTargetUrl(shareData.url)
                    .withTitle(title);
        } else if (platform == SHARE_MEDIA.WEIXIN_CIRCLE) {// 需要title
            action
                    .withTitle(title)
                    .withText(title)
                    .withMedia(UMimg)
                    .withTargetUrl(shareData.url);
        }
        action.share();
    }

    private UMShareListener listener = new UMShareListener() {

        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (platform == SHARE_MEDIA.SMS) return;
            toast("分享成功!");
            onBackPressed();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable arg1) {
            if (platform == SHARE_MEDIA.SMS) return;
            toast("分享失败!");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
        }
    };
}

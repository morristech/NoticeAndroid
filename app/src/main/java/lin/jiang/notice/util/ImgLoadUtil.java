package lin.jiang.notice.util;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import lin.jiang.notice.App;
import lin.jiang.notice.R;

/**
 * Created by Summer on 2016/5/7.
 */
public class ImgLoadUtil {

    public static final void load(String path, ImageView target) {
        Picasso.with(App.instance()).load(path).fit().centerCrop().placeholder(R.drawable._ic_holder_pic).into(target);
    }
}

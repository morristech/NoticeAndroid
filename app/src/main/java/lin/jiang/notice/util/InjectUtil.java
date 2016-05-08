package lin.jiang.notice.util;

import lin.jiang.notice.data.DataSourceImpl;

/**
 * Created by Summer on 2016/5/5.
 */
public class InjectUtil {
    public static DataSourceImpl getDataSource() {
        return new DataSourceImpl();
    }
}

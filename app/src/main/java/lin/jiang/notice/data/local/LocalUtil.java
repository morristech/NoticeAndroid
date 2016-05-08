package lin.jiang.notice.data.local;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lin.jiang.notice.util.L;

/**
 * Created by Summer on 2016/5/6.
 */
public class LocalUtil {
    public static boolean putObject(Context context, String key, Object value) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(key, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(value);
            L.d("putObject: true");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            L.d("putObject: false,catch");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        L.d("putObject: false");
        return false;
    }

    public static Object getObject(Context context, String key) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.openFileInput(key);
            ois = new ObjectInputStream(fis);
            try {
                return ois.readObject();
            } catch (ClassNotFoundException e) {
                L.e("ClassNotFoundException:  readObject");
                return null;
            }
        } catch (FileNotFoundException e) {
            L.e("FileNotFoundException:  openFileInput");
            return null;
        } catch (IOException e) {
            L.e("IOException:  new ObjectInputStream(fis)");
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    L.e("IOException:  fis.close");
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    L.e("IOException:  ois.close");
                }
            }
        }
    }
}

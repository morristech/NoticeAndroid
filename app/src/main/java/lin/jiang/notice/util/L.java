package lin.jiang.notice.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.MissingFormatArgumentException;
import java.util.Set;

import android.util.Log;

import lin.jiang.notice.BuildConfig;

/**
 * @author YangLinjiang
 */
public class L {
	private static boolean DEBUG = BuildConfig.DEBUG;
	private static String PreTag = null;
	public static void setPreTag(String pre) {
		PreTag = pre;
	}
	private enum LogType {
		Verbose, Debug, Info, Warn, Error, Wtf;
	}
    private final static String[] types = {"int", "java.lang.String", "boolean", "char",
            "float", "double", "long", "short", "byte"};
    
    

	/**
	 * @return
	 */
	private static String generateTag() {
		StackTraceElement stack = Thread.currentThread().getStackTrace()[4];
		String tag = "%s(L:%d)";
		String className = stack.getClassName();
		className = className.substring(className.lastIndexOf(".") + 1);
		tag = String.format(tag, className, stack.getLineNumber());
		tag = PreTag == null ? tag : PreTag + ":" + tag;
		return tag;
	}
	
	private static void logString(LogType type, String msg, Object... args) {
        if (!DEBUG) {
            return;
        }
        String tag = generateTag();
        if (args.length > 0) {
            try {
                msg = String.format(msg, args);
            } catch (MissingFormatArgumentException e) {

            }
        }
        switch (type) {
            case Verbose:
                Log.v(tag, msg);
                break;
            case Debug:
                Log.d(tag, msg);
                break;
            case Info:
                Log.i(tag, msg);
                break;
            case Warn:
                Log.w(tag, msg);
                break;
            case Error:
                Log.e(tag, msg);
                break;
            case Wtf:
                Log.wtf(tag, msg);
                break;
            default:
                break;
        }
    }
	
	/**
	 * @param type
	 * @param object String/Collection/Map
	 */
	private static void logObject(LogType type, Object object) {
        if (object != null) {
            final String simpleName = object.getClass().getSimpleName();
            if (object instanceof String) {
                logString(type, (String) object);
            } else if (object instanceof Collection) {
                Collection collection = (Collection) object;
                String msg = "%s size = %d [\n";
                msg = String.format(msg, simpleName, collection.size());
                if (!collection.isEmpty()) {
                    Iterator<Object> iterator = collection.iterator();
                    int flag = 0;
                    while (iterator.hasNext()) {
                        String itemString = "[%d]:%s%s";
                        Object item = iterator.next();
                        msg += String.format(itemString, flag, objectToString(item),
                                flag++ < collection.size() - 1 ? ",\n" : "\n");
                    }
                }
                logString(type, msg + "\n]");
            } else if (object instanceof Map) {
                String msg = simpleName + " {\n";
                Map<Object, Object> map = (Map<Object, Object>) object;
                Set<Object> keys = map.keySet();
                for (Object key : keys) {
                    String itemString = "[%s -> %s]\n";
                    Object value = map.get(key);
                    msg += String.format(itemString, objectToString(key),
                            objectToString(value));
                }
                logString(type, msg + "}");
            } else {
                logString(type, objectToString(object));
            }
        } else {
            logString(type, objectToString(object));
        }
    }
	
	/**
     * @param object
     * @return
     */
    @SuppressWarnings("finally")
	public static <T> String objectToString(T object) {
        if (object == null) {
            return "Object{object is null}";
        }
        if (object.toString().startsWith(object.getClass().getName() + "@")) {
            StringBuilder builder = new StringBuilder(object.getClass().getSimpleName() + "{");
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                boolean flag = false;
                for (String type : types) {
                    if (field.getType().getName().equalsIgnoreCase(type)) {
                        flag = true;
                        Object value = null;
                        try {
                            value = field.get(object);
                        } catch (IllegalAccessException e) {
                            value = e;
                        }finally {
                            builder.append(String.format("%s=%s, ", field.getName(),
                                    value == null ? "null" : value.toString()));
                            break;
                        }
                    }
                }
                if(!flag){
                    builder.append(String.format("%s=%s, ", field.getName(), "Object"));
                }
            }
            return builder.replace(builder.length() - 2, builder.length() - 1, "}").toString();
        } else {
            return object.toString();
        }
    }

	// Info----------------------------------------
	public static void i(String msg) {
		if (!DEBUG) {
			return;
		}
		Log.i(generateTag(), msg);
	}
	public static void i(Object obj) {
		if (!DEBUG) {
			return;
		}
		logObject(LogType.Info, obj);
	}
	// Debug--------------------------------------
	public static void d(String msg) {
		if (!DEBUG) {
			return;
		}
		Log.d(generateTag(), msg);
	}
	public static void d(Object obj) {
		if (!DEBUG) {
			return;
		}
		logObject(LogType.Debug, obj);
	}
	// Verbose-----------------------------------
	public static void v(String msg) {
		if (!DEBUG) {
			return;
		}
		Log.v(generateTag(), msg);
	}
	public static void v(Object obj) {
		if (!DEBUG) {
			return;
		}
		logObject(LogType.Verbose, obj);
	}
	// Warning-----------------------------------
	public static void w(String msg) {
		if (!DEBUG) {
			return;
		}
		Log.w(generateTag(), msg);
	}
	public static void w(Object obj) {
		if (!DEBUG) {
			return;
		}
		logObject(LogType.Warn, obj);
	}
	// Error---------------------------------------
	public static void e(String msg) {
		if (!DEBUG) {
			return;
		}
		Log.e(generateTag(), msg);
	}
	public static void e(Object obj) {
		if (!DEBUG) {
			return;
		}
		logObject(LogType.Error, obj);
	}
}

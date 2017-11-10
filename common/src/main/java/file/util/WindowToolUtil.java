package file.util;

import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/11/8
 * @description
 */
public class WindowToolUtil {

    public static void useRuntimeExec(String path) throws IOException {
        /*
         * 若打开的目录或文件名中不包含空格,就用下面的方式
         */
        Runtime.getRuntime().exec("cmd /c start " + path);
    }
}

package file.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/11/8
 * @description
 */
public class OpenLocalFile {
    public static void main(String[] args) throws IOException {
//        useProcessBuilder();
//        useAWTDesktop();
        useRuntimeExec();
    }


    /**
     * 借助java.lang.ProcessBuilder打开
     */
    private static void useProcessBuilder() throws IOException{
        //new ProcessBuilder("notepad.exe", "C:/Users/Jadyer/Desktop/test file/readme.txt").start();
        List<String> commands = new ArrayList<String>();
        commands.add("D:/Program Files/WPS/9.1.0.4047/office6/wps.exe");
        commands.add("C:/Users/Jadyer/Desktop/test file/myResume.doc");
        new ProcessBuilder(commands).start();
    }


    private static void useRuntimeExec() throws IOException{
        /*
         * 若打开的目录或文件名中不包含空格,就用下面的方式
         */
        Runtime.getRuntime().exec("cmd /c start E:/绘图1.vsd");
        /*
         * (可以'运行'或'Win+R',然后输入'cmd /?'查看帮助信息)
         */
//        Runtime.getRuntime().exec(new String[]{"cmd.exe","d:","D:/test.xlsx"});
        /*
         * 借助本地安装程序打开
         * 若打开的目录或文件名中包含空格,它就无能为力了..不过本地程序的安装目录允许含空格
         */
//        String etCommand = "D:/Program Files/WPS/8.1.0.3526/office6/et.exe";
//        String filePath = "D:/mylocal/测试用例.xls";
//        Runtime.getRuntime().exec(etCommand + " " + filePath);
    }
}

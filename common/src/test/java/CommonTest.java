import file.util.FileUtil;
import file.util.WindowToolUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static file.util.ConvertFile.getTextFromExcel2007;
import static file.util.ConvertFile.getTextFromPdf;
import static file.util.ConvertFile.getTextFromWord;


/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/11/7
 * @description
 */
public class CommonTest {

    @Test
    @Ignore
    public void WindowToolTest() throws IOException {
        WindowToolUtil.useRuntimeExec("D:/test.xlsx");
    }

    @Test
    @Ignore
    public void PdfTest() {
        System.out.println(getTextFromPdf("C:\\Users\\Administrator\\Documents\\Tencent Files\\1468195034\\FileRecv\\test.pdf"));
    }

    @Test
    @Ignore
    public void ExcleTest() {
        System.out.println(getTextFromExcel2007("D:\\my.xlsx"));
    }

    @Test
    @Ignore
    public void WordTest() {
        System.out.println(getTextFromWord("C:\\Users\\Administrator\\Documents\\Tencent Files\\1468195034\\FileRecv\\TM_用户需求说明书_省级不动产登记产权证书监管系统V1.0.doc"));
    }

    @Test
    public void fileTest() {
        Map<String,List<String>> differentFile = FileUtil.differentFile(new File("D:\\target\\test.txt"), new File("D:\\target\\test1.txt"));

        for(String s : differentFile.get("source")) {
            System.out.println(s);
        }

        for(String s : differentFile.get("target")) {
            System.out.println(s);
        }
    }
}

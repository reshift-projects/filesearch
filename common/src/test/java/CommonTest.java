import com.alibaba.fastjson.JSON;
import file.model.file.FileDescription;
import file.model.file.SystemDescription;
import file.util.WindowToolUtil;
import file.util.file.FileDetailDescription;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static file.util.ConvertFile.getTextFromExcel2007;
import static file.util.ConvertFile.getTextFromPdf;
import static file.util.ConvertFile.getTextFromWord;
import static file.util.FileUtil.CopyFile;


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

    @Ignore
    @Test
    public void fileTest() throws IOException {
        FileDetailDescription fileDetailDescription = FileDetailDescription.analysisFile(new File("F:\\test\\test.txt"));
        FileDetailDescription fileDetailDescription1 = FileDetailDescription.analysisFile(new File("F:\\test\\test1.txt"));
        FileDetailDescription.contrastFileDesciption(fileDetailDescription, fileDetailDescription1);
    }


    @Test
    @Ignore
    public void xmlFileDescriptionTest() throws JAXBException {

      JAXBContext context = JAXBContext.newInstance(FileDescription.class,SystemDescription.class);    // 获取上下文对象
      Marshaller marshaller = context.createMarshaller(); // 根据上下文获取marshaller对象

      marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  // 设置编码字符集
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化XML输出，有分行和缩进

      marshaller.marshal(getSimpleDepartment("test1"),System.out);   // 打印到控制台

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      marshaller.marshal(getSimpleDepartment("test2"), baos);
      String xmlObj = new String(baos.toByteArray());         // 生成XML字符串
      System.out.println(xmlObj);
    }

    @Ignore
    @Test
    public void jsonFileDescriptinTest() {
      List<FileDescription> fileDescriptions = new ArrayList<FileDescription>();
      fileDescriptions.add(getSimpleDepartment("test1"));
      fileDescriptions.add(getSimpleDepartment("test2"));
      String data = JSON.toJSONString(fileDescriptions);
      List<FileDescription> fileDescription = JSON.parseArray(data, FileDescription.class);
      System.out.println(fileDescription.get(1).getFilename());
      System.out.println(data);

    }



  private static FileDescription getSimpleDepartment(String fileName) {
    List<SystemDescription> systemDescriptions = new ArrayList<SystemDescription>();

    SystemDescription systemDescription = new SystemDescription();

    systemDescription.setNode("window@tianjian");
    systemDescription.setPath("F:\\workspace\\file\\text.txt");
    systemDescription.setSystem("window");
    systemDescriptions.add(systemDescription);

    FileDescription fileDescription = new FileDescription();

    fileDescription.setSystemDescription(systemDescriptions);

    fileDescription.setFilename(fileName);

    fileDescription.setSignature("test");

    return fileDescription;
  }

  @Test
  public void copyFileTest() throws IOException {
    CopyFile(new File("F:\\workspace\\file\\test.txt"), new File("F:\\workspace\\file\\json.branch"), "F:\\workspace\\file", "window");
  }


}

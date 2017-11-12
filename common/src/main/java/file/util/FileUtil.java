package file.util;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import file.ScanFileUtil;
import file.model.file.FileDescription;
import file.model.file.FileMetadata;
import file.model.file.SystemDescription;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianian</a>
 * @version 1.0, 2017/8/11
 * @description 文件工具
 */
@Service
public class FileUtil {

  /**
   * @param file 需要获取的文件
   * @return 文件的字节数组
   * @throws IOException
   */
    public static byte[] getFileAsBytes(File file) throws IOException {
        ByteSource byteSource = null ;

        byteSource = Files.asByteSource(file);

        return byteSource.read();
    }


  /**
   * @param file 需要保存的文件路径
   * @param content 文件内容
   * @return 是否保存成功
   */
    public static boolean saveFileByByte(File file, byte[] content) {

        try {

            Files.write(content, file);

            return true;

        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }
    }

  /**
   * @param file 获取文件的字符串
   * @return 文件字符串
   * @throws IOException
   */
    public static String getFileAsString(File file) throws IOException {

        BufferedReader reader = null ;

        String fileString = null;

        try {

            reader = Files.newReader(file, Charsets.UTF_8);

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } finally {

            reader.close();

            return fileString;

        }
    }

    public static boolean removeFile(File file) {

        return file.delete();

    }

  /**
   * @param file 需要循环遍历的文件夹
   * @param fileFilter 文件过滤接口
   * @param allFile 已获得的文件
   * @return 以获取的所有文件
   */
    public static List<File> getFilesByPath(File file, FileFilter fileFilter, List<File> allFile) {

        if(!file.exists()) {
            return null;
        }

        File[] files = file.listFiles();

        if(files != null &&files.length > 0) {
            for(File fileDetail : files) {

                if(fileDetail.isFile()) {

                    if(fileFilter != null && !fileFilter.accept(fileDetail)) {
                        continue;
                    }

                    System.out.println(fileDetail.getAbsolutePath());
                    allFile.add(fileDetail);
                }

                if(fileDetail.isDirectory()) {
                    getFilesByPath(fileDetail, fileFilter, allFile);

                }
            }
        }
        return allFile;
    }

  /**
   * @param file 需要保存到ES服务的文件
   * @return 文件描述
   * @throws ParseException
   * @throws IOException
   */
    public static List<FileMetadata> saveFileToEs(File file) throws ParseException, IOException {
        List<FileMetadata> fileMetadatas = new ArrayList<FileMetadata>();
        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setDate(ScanFileUtil.getChangeTime(file));
        String fileName = file.getName();
        fileMetadata.setFileName(fileName);
        String[] sufix = fileName.split("\\.");
        if(sufix.length > 0){
            fileMetadata.setType(sufix[sufix.length-1]);
        }
        if(fileName.endsWith(".java") || fileName.endsWith(".txt") || fileName.endsWith(".xml")) {
            System.out.println(getTxtEncode(new FileInputStream(file)));
            System.out.println("GBK" + new String(getFileAsBytes(file), "GBK"));
            System.out.println("UTF-8" + new String(getFileAsBytes(file), "UTF-8"));
            fileMetadata.setData(new String(getFileAsBytes(file), "GBK"));
        }
        fileMetadata.setDirector(file.isDirectory());
        fileMetadata.setSize(file.length());
        fileMetadata.setPath(file.getAbsolutePath());
        fileMetadata.setParentDir(file.getParent());
        fileMetadatas.add(fileMetadata);
        System.out.println(ElasticServer.saveDataToEs(fileMetadata.getEsInsertData(), "window_search", "system"));
        System.out.println("tianjian.filesearch.server.service.rest");
        return fileMetadatas;


    }

  /**
   * @param in 文件输入流
   * @return 文件编码
   * @throws IOException
   */
    public static String getTxtEncode(FileInputStream in) throws IOException{

        String dc  = Charset.defaultCharset().name();
        UnicodeInputStream uin = new UnicodeInputStream(in,dc);

        if("UTF-8".equals(uin.getEncoding())){
            uin.close();
            return "UTF-8";
        }
        uin.close();

        byte[] head = new byte[3];
        in.read(head);
        String code = "GBK";
        if (head[0] == -1 && head[1] == -2 )
            code = "UTF-16";
        if (head[0] == -2 && head[1] == -1 )
            code = "Unicode";
        //带BOM
        if(head[0]==-17 && head[1]==-69 && head[2] ==-65)
            code = "UTF-8";
        if("Unicode".equals(code)){
            code = "UTF-16";
        }
        return code;
    }


  /**
   * @param content 需要保存的文件内容
   * @param file 需要保存的文件
   * @return 保存后的文件
   * @throws IOException
   */
    public static File saveStringToFile(String content, File file) throws IOException {
        FileUtils.writeStringToFile(file, content,"UTF-8");
        return file;
    }

  /**
   * @param filepath 文件存放路径相当于svn仓库
   * @throws IOException IOE异常
   */
    public static void CopyFile(File checkFile, File vcsFile,  String filepath, String nodename) throws IOException {

      String data = new String(getFileAsBytes(vcsFile));

      List<FileDescription> fileDescriptors = JSON.parseArray(data, FileDescription.class);

      FileDescription fileDetail = null;

      String contain = getFileMD5String(checkFile);

      for(FileDescription fileDescriptor : fileDescriptors) {
        if(fileDescriptor.getFilename().equals(checkFile.getName())) {
          List<SystemDescription> systemDescriptions = fileDescriptor.getSystemDescription();
          for(SystemDescription systemDescription : systemDescriptions) {
            if(systemDescription.getNode().equals(nodename) && systemDescription.getPath().equals(checkFile.getAbsolutePath())) {
              fileDetail = fileDescriptor;
              break;
            }
          }

        }
      }

      if(fileDetail != null) {
        if(fileDetail.getSignature().equals(contain)) {
          return ;
        }
      }
      if(fileDetail == null) {
        SystemDescription systemDescription = new SystemDescription();
        fileDetail = new FileDescription();
        fileDetail.setFilename(checkFile.getName());
        fileDetail.setSignature(contain);
        systemDescription.setNode(nodename);
        systemDescription.setPath("F:\\workspace\\file\\test.txt");
        systemDescription.setSystem("window7");
        fileDetail.addSystemDescription(systemDescription);
        fileDescriptors.add(fileDetail);
      } else {
        fileDetail.setSignature(contain);
        File exist = new File(filepath + "\\" + fileDetail.getSignature());
        exist.delete();
      }

      FileUtils.writeByteArrayToFile(vcsFile, JSON.toJSONString(fileDescriptors).getBytes());

      Files.copy(checkFile, new File(filepath + "\\" + contain));



    }

  /**
   * @param file 需要MD5加密的文件
   * @return 文件MD5加密的结果
   * @throws IOException
   */
    public static String getFileMD5String(File file) throws IOException {
      String fileString = new String(getFileAsBytes(file));
      return Hashing.md5().newHasher().putString(fileString, Charsets.UTF_8).hash().toString();
    }





}

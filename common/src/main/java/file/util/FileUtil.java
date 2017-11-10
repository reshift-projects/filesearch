package file.util;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import file.ScanFileUtil;
import file.model.FileMetadata;
import file.util.file.FileContent;
import file.util.file.FileDetailDescription;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianian</a>
 * @version 1.0, 2017/8/11
 * @description
 */
@Service
public class FileUtil {

    public static byte[] getFileAsBytes(File file) throws IOException {
        ByteSource byteSource = null ;

        byteSource = Files.asByteSource(file);

        return byteSource.read();
    }


    public static boolean saveFileByByte(File file, byte[] content) {

        try {

            Files.write(content, file);

            return true;

        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }
    }

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

    public static List<FileMetadata> saveFileToEs(File f) throws ParseException, IOException {
        List<FileMetadata> fileMetadatas = new ArrayList<FileMetadata>();
        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setDate(ScanFileUtil.getChangeTime(f));
        String fileName = f.getName();
        fileMetadata.setFileName(fileName);
        String[] sufix = fileName.split("\\.");
        if(sufix.length > 0){
            fileMetadata.setType(sufix[sufix.length-1]);
        }
        if(fileName.endsWith(".java") || fileName.endsWith(".txt") || fileName.endsWith(".xml")) {
            System.out.println(getTxtEncode(new FileInputStream(f)));
            System.out.println("GBK" + new String(getFileAsBytes(f), "GBK"));
            System.out.println("UTF-8" + new String(getFileAsBytes(f), "UTF-8"));
            fileMetadata.setData(new String(getFileAsBytes(f), "GBK"));
        }
        fileMetadata.setDirector(f.isDirectory());
        fileMetadata.setSize(f.length());
        fileMetadata.setPath(f.getAbsolutePath());
        fileMetadata.setParentDir(f.getParent());
        fileMetadatas.add(fileMetadata);
        System.out.println(ElasticServer.saveDataToEs(fileMetadata.getEsInsertData(), "window_search", "system"));
        System.out.println("rest");
        return fileMetadatas;


    }

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



    public static File saveStringToFile(String content, File file) throws IOException {
        FileUtils.writeStringToFile(file, content,"UTF-8");
        return file;
    }

    // 计算文件的 MD5 值
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[8192];
        int len;
        try {
            digest =MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }





}

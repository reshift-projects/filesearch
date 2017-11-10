package file.util.file;

import file.util.FileUtil;

import java.io.*;
import java.util.*;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/11/10
 * @description 文件分析
 */
public class FileDetailDescription {

    private List<FileContent> fileContents;
    private String fileName;
    private String MD5Signature;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMD5Signature() {
        return MD5Signature;
    }

    public void setMD5Signature(String MD5Signature) {
        this.MD5Signature = MD5Signature;
    }

    public List<FileContent> getFileContents() {
        return fileContents;
    }

    public void setFileContents(List<FileContent> fileContents) {
        this.fileContents = fileContents;
    }

    public void saveFileContent(FileContent fileContent) {
        if(fileContents == null) {
            fileContents = new ArrayList<FileContent>();
        }
        fileContents.add(fileContent);
    }

    public static FileDetailDescription analysisFile(File file) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        FileDetailDescription fileDetailDescription = new FileDetailDescription();
        fileDetailDescription.setFileName(file.getName());
        fileDetailDescription.setMD5Signature(FileUtil.getFileMD5(file));
        BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        try {
            String str = "";
            fis = new FileInputStream(file);// FileInputStream
            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
            int orderNumber = 0;
            int rowNumber = 0;
            while ((str = br.readLine()) != null) {
                FileContent fileContent = new FileContent();
                if(!"".equals(str)) {
                    orderNumber++;
                    fileContent.setOrderNumber(orderNumber);
                }
                rowNumber++;
                fileContent.setContent(str);
                fileContent.setRowNumber(rowNumber);
                fileContent.setContent(str);
                fileDetailDescription.saveFileContent(fileContent);
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileDetailDescription;
    }

    public static void contrastFileDesciption(FileDetailDescription source, FileDetailDescription target) {
        List<FileContent> contains = new ArrayList<FileContent>();
        List<List<Integer>> datas = new ArrayList<List<Integer>>();
        Map<FileContent, List<Integer>> detailsDiffer = new LinkedHashMap<FileContent, List<Integer>>();
        List<FileContent> fileContents = source.getFileContents();
        for(FileContent fileContent : fileContents) {
            List<Integer> data = new ArrayList<Integer>();
            if(!fileContent.getContent().equals("")){
                for(FileContent fileContent1 : target.getFileContents()) {
                    if(fileContent1.getContent().equals(fileContent.getContent())) {
                        data.add(fileContent1.getOrderNumber());
                    }
                }

            }
            datas.add(data);
        }

        for(FileContent key : detailsDiffer.keySet()) {



        }

    }
}

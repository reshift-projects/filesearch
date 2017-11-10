package file.util;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/11/8
 * @description
 */
public class ConvertFile {

    /**
     *
     * @Title: getTextFromWord
     * @Description: 读取word
     * @param filePath
     *            文件路径
     * @return: String 读出的Word的内容
     */
    public static String getTextFromWord(String filePath) {
        String result = null;
        File file = new File(filePath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            @SuppressWarnings("resource")
            WordExtractor wordExtractor = new WordExtractor(fis);
            result = wordExtractor.getText();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     *
     * @Title: getTextFromPdf
     * @Description: 读取pdf文件内容
     * @param filePath
     * @return: 读出的pdf的内容
     */
    public static String getTextFromPdf(String filePath) {
        String result = null;
        FileInputStream is = null;
        PDDocument document = null;
        try {
            is = new FileInputStream(filePath);
            PDFParser parser = new PDFParser(is);
            parser.parse();
            document = parser.getPDDocument();
            PDFTextStripper stripper = new PDFTextStripper();
            result = stripper.getText(document);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    /**
     * @param filePath
     *            文件路径
     * @return 读出的Excel的内容
     */
    @SuppressWarnings({"resource", "deprecation"})
    public static String getTextFromExcel(String filePath) {
        StringBuffer buff = new StringBuffer();
        try {
            // 创建对Excel工作簿文件的引用
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filePath));
            // 创建对工作表的引用。
            for (int numSheets = 0; numSheets < wb
                    .getNumberOfSheets(); numSheets++) {
                if (null != wb.getSheetAt(numSheets)) {
                    HSSFSheet aSheet = wb.getSheetAt(numSheets);// 获得一个sheet
                    for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet
                            .getLastRowNum(); rowNumOfSheet++) {
                        if (null != aSheet.getRow(rowNumOfSheet)) {
                            HSSFRow aRow = aSheet.getRow(rowNumOfSheet); // 获得一个行
                            for (int cellNumOfRow = 0; cellNumOfRow <= aRow
                                    .getLastCellNum(); cellNumOfRow++) {
                                if (null != aRow.getCell(cellNumOfRow)) {
                                    HSSFCell aCell = aRow.getCell(cellNumOfRow);// 获得列值
                                    switch (aCell.getCellType()) {
                                        case HSSFCell.CELL_TYPE_FORMULA :
                                            break;
                                        case HSSFCell.CELL_TYPE_NUMERIC :
                                            buff.append(
                                                    aCell.getNumericCellValue())
                                                    .append('\t');
                                            break;
                                        case HSSFCell.CELL_TYPE_STRING :
                                            buff.append(
                                                    aCell.getStringCellValue())
                                                    .append('\t');
                                            break;
                                    }
                                }
                            }
                            buff.append('\n');
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buff.toString();
    }

    @SuppressWarnings("deprecation")
    public static String getTextFromExcel2007(String filePath) {
        StringBuffer buff = new StringBuffer();
        try {
            // 创建对Excel工作簿文件的引用
            @SuppressWarnings("resource")
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));
            // 创建对工作表的引用。
            for (int numSheets = 0; numSheets < wb
                    .getNumberOfSheets(); numSheets++) {
                if (null != wb.getSheetAt(numSheets)) {
                    XSSFSheet aSheet = wb.getSheetAt(numSheets);// 获得一个sheet
                    for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet
                            .getLastRowNum(); rowNumOfSheet++) {
                        if (null != aSheet.getRow(rowNumOfSheet)) {
                            XSSFRow aRow = aSheet.getRow(rowNumOfSheet); // 获得一个行
                            for (int cellNumOfRow = 0; cellNumOfRow <= aRow
                                    .getLastCellNum(); cellNumOfRow++) {
                                if (null != aRow.getCell(cellNumOfRow)) {
                                    XSSFCell aCell = aRow.getCell(cellNumOfRow);// 获得列值
                                    switch (aCell.getCellType()) {
                                        case HSSFCell.CELL_TYPE_FORMULA :
                                            break;
                                        case HSSFCell.CELL_TYPE_NUMERIC :
                                            buff.append(
                                                    aCell.getNumericCellValue())
                                                    .append('\t');
                                            break;
                                        case HSSFCell.CELL_TYPE_STRING :
                                            buff.append(
                                                    aCell.getStringCellValue())
                                                    .append('\t');
                                            break;
                                    }
                                }
                            }
                            buff.append('\n');
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buff.toString();
    }

}

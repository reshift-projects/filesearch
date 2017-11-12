package file.service.impl;

import file.service.ConverFileService;
import file.util.ConvertFile;

import java.io.File;

/**
 * Created by tianjian on 2017/11/12.
 */
public class PDFConverFileService extends ConverFileService {
    @Override
    public String extractFile(File file) {
        return ConvertFile.getTextFromPdf(file);
    }
    public PDFConverFileService(String type) {
        super(type);
    }
}

package file.service.impl;

import file.service.ConverFileService;
import file.util.ConvertFile;

import java.io.File;

/**
 * Created by tianjian on 2017/11/12.
 */
public class WordConverFileService extends ConverFileService {

    public WordConverFileService(String type) {
        super(type);
    }

    @Override
    public String extractFile(File file) {
        return ConvertFile.getTextFromWord(file);
    }
}

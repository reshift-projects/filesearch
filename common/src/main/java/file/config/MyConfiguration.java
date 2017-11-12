package file.config;

import file.service.ConverFileService;
import file.service.impl.Excle2007ConverFileService;
import file.service.impl.ExcleConverFileService;
import file.service.impl.PDFConverFileService;
import file.service.impl.WordConverFileService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by tianjian on 2017/11/12.
 */
@Configurable
@Service
public class MyConfiguration {
    @Bean
    public ConverFileService getConverFileService() {
        ConverFileService pdfConver = new PDFConverFileService("PDF");
        ConverFileService excle2007Conver = new Excle2007ConverFileService("xls");
        ConverFileService excleConver = new ExcleConverFileService("xls");
        ConverFileService wordConver = new WordConverFileService("doc");
        pdfConver.buildConverFileService(excle2007Conver);
        excle2007Conver.buildConverFileService(excleConver);
        excleConver.buildConverFileService(wordConver);
        return pdfConver;
    }

}

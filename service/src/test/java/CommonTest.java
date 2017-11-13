import com.alibaba.fastjson.JSON;
import file.model.file.FileDescription;
import file.model.file.FileDetailDescription;
import file.model.file.SystemDescription;
import file.model.message.RequestMessage;
import file.util.WindowToolUtil;
import org.junit.Ignore;
import org.junit.Test;
import tianjian.filesearch.server.service.MessageService;
import tianjian.filesearch.server.service.impl.DealMessageProxy;
import tianjian.filesearch.server.service.impl.InitFileMessageService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    public void proxyDealTest() throws Exception {

        DealMessageProxy ctx = new DealMessageProxy();
        MessageService messageService = new InitFileMessageService("10002");
        MessageService messageService1 = new InitFileMessageService("10003");
        MessageService messageService2 = new InitFileMessageService("10004");
        MessageService messageService3 = new InitFileMessageService("10005");
        ctx.addDealMessage(messageService);
        ctx.addDealMessage(messageService1);
        ctx.addDealMessage(messageService2);
        ctx.addDealMessage(messageService3);
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setCode("10003");
        requestMessage.setToken("token");
        requestMessage.setMessage("message");
        requestMessage.setNode("window");
        requestMessage.setTime(new Date());
        System.out.println(JSON.toJSONString(ctx.dealMessage(requestMessage)));
    }


}

package tianjian.filesearch.server.config;

import file.model.message.RequestMessage;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import tianjian.filesearch.server.service.DealMessage;
import tianjian.filesearch.server.service.MessageService;
import tianjian.filesearch.server.service.impl.DealMessageProxy;
import tianjian.filesearch.server.service.impl.InitFileMessageService;

/**
 * Created by tianjian on 2017/11/12.
 */
@Configurable
@Service
public class MyConfiguration {
    @Bean
    public DealMessage getMessageServcie() {
        DealMessageProxy ctx = new DealMessageProxy();
        MessageService messageService = new InitFileMessageService("10002");
        MessageService messageService1 = new InitFileMessageService("10003");
        ctx.addDealMessage(messageService);
        ctx.addDealMessage(messageService1);
        return ctx;
    }



}

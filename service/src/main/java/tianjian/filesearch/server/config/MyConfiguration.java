package tianjian.filesearch.server.config;

import file.model.message.RequestMessage;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import tianjian.filesearch.server.service.MessageService;
import tianjian.filesearch.server.service.impl.InitFileMessageService;

/**
 * Created by tianjian on 2017/11/12.
 */
@Configurable
@Service
public class MyConfiguration {
    @Bean
    public MessageService getMessageServcie() {
        MessageService messageService = new InitFileMessageService("10002");
        MessageService messageService1 = new InitFileMessageService("10003");
        messageService.buildMessageService(messageService1);
        return messageService;
    }



}

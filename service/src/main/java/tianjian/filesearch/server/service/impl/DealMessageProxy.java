package tianjian.filesearch.server.service.impl;

import file.model.message.RequestMessage;
import file.model.message.ResponseMessage;
import tianjian.filesearch.server.service.DealMessage;
import tianjian.filesearch.server.service.MessageService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/11/13
 * @description
 */
public class DealMessageProxy implements DealMessage{
    List<MessageService> messageServices = new ArrayList<MessageService>();

    @Override
    public ResponseMessage dealMessage(RequestMessage requestMessage) throws Exception {
        buildStreamMessageService();
        return messageServices.get(0).dealProxyMessage(requestMessage);
    }

    public void addDealMessage(MessageService messageService) {
        messageServices.add(messageService);
    }

    public MessageService buildStreamMessageService() {
        MessageService init = messageServices.get(0);
        if(messageServices.size() >1) {
            for(int i = 0; i < messageServices.size(); i++) {
                init.buildMessageService(messageServices.get(i));
                init = messageServices.get(i);
            }
        }
        return init;
    }


}

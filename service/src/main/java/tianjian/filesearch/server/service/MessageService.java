package tianjian.filesearch.server.service;

import file.model.message.RequestMessage;
import file.model.message.ResponseMessage;

/**
 * Created by tianjian on 2017/11/12.
 */
public abstract class MessageService implements DealMessage{
    private String code;
    private MessageService messageService;
    public ResponseMessage dealProxyMessage(RequestMessage requestMessage) throws Exception {
        if(requestMessage.getCode().equals(code)) {
            return dealMessage(requestMessage);
        } else if(messageService != null){
            return messageService.dealProxyMessage(requestMessage);
        } else {
            throw new Exception("there is no handle");
        }
     }


    public MessageService(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public MessageService getMessageService() {
        return messageService;
    }

    public void buildMessageService(MessageService messageService) {
        if(messageService.getCode().equals(code)) {
            return ;
        }
        this.messageService = messageService;
    }
}

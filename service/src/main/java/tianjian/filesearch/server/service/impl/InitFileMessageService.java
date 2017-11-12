package tianjian.filesearch.server.service.impl;

import file.model.message.RequestMessage;
import file.model.message.ResponseMessage;
import tianjian.filesearch.server.service.MessageService;

import java.util.Date;

/**
 * Created by tianjian on 2017/11/12.
 */
public class InitFileMessageService extends MessageService {

    public InitFileMessageService(String code) {
        super(code);
    }

    @Override
    public ResponseMessage dealMessage(RequestMessage requestMessage) {
        System.out.println(requestMessage.getCode());
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(requestMessage.getCode());
        responseMessage.setTime(new Date());
        responseMessage.setData("hhahah");
        return responseMessage;
    }
}

package tianjian.filesearch.server.service;

import file.model.message.RequestMessage;
import file.model.message.ResponseMessage;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/11/13
 * @description
 */
public interface DealMessage {
    ResponseMessage dealMessage(RequestMessage requestMessage) throws Exception;
}

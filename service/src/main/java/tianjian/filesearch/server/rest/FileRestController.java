package tianjian.filesearch.server.rest;

import com.alibaba.fastjson.JSON;
import file.model.message.RequestMessage;
import file.model.message.ResponseMessage;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tianjian.filesearch.server.service.MessageService;
import tianjian.filesearch.server.service.impl.InitFileMessageService;

import java.util.Date;


@RestController
@RequestMapping("/rest")
public class FileRestController {

  @Autowired
  MessageService messageService;

  @RequestMapping(value = "/server", method = RequestMethod.GET)
  public String restController(String message) throws Exception {
    //RequestMessage requestMessage = JSON.parseObject(message, RequestMessage.class);
    RequestMessage requestMessage = new RequestMessage();
    requestMessage.setCode("10002");
    requestMessage.setMessage("hahah");
    requestMessage.setTime(new Date());
    return JSON.toJSONString(messageService.dealProxyMessage(requestMessage));
  }



}

package tianjian.filesearch.server.rest;

import com.alibaba.fastjson.JSON;
import file.model.message.RequestMessage;
import file.model.message.ResponseMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest")
public class FileRestController {

  @RequestMapping(value = "/server", method = RequestMethod.POST)
  public String restController(String message) {
    RequestMessage requestMessage = JSON.parseObject(message, RequestMessage.class);
    return JSON.toJSONString(new ResponseMessage());
  }



}

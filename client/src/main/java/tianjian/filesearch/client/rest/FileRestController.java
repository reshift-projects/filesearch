package tianjian.filesearch.client.rest;

import file.model.message.RequestMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tianjian.filesearch.server.service.rest")
public class FileRestController {

  @RequestMapping("/client")
  public void restController(String requestMessage) {


  }



}

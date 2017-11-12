package file.model.message;

import java.util.Date;

public class ResponseMessage {
  /**
   * 响应消息编码
   */
  private String code;

  /**
   * 响应消息内容
   */
  private String data;

  /**
   * 响应时间
   */
  private Date time;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }
}

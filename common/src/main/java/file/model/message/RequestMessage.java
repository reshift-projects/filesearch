package file.model.message;

import java.util.Date;

public class RequestMessage {
  /**
   * 业务编码
   */
  private String code;

  /**
   * 业务请求消息
   */
  private String message;

  /**
   * 用户信息
   */
  private String token;

  /**
   * 请求时间
   */
  private Date time;

  /**
   * 机器编码（分布式文件控制需要）
   */
  private String node;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }
}

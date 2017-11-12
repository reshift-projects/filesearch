package file.model.file;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="system")
public class SystemDescription {
  private String node;    // 职员名称
  private String system;        // 职员年龄
  private String path; // 是否为烟民

  public String getNode() {
    return node;
  }

  @XmlAttribute
  public void setNode(String node) {
    this.node = node;
  }

  public String getSystem() {
    return system;
  }

  @XmlAttribute
  public void setSystem(String system) {
    this.system = system;
  }

  public String getPath() {
    return path;
  }

  @XmlAttribute
  public void setPath(String path) {
    this.path = path;
  }
}

package file.model.file;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="file")
public class FileDescription {
  private String filename;
  private String signature;
  private List<SystemDescription> systemDescription;           // 其实staff是单复同型，这里是加's'是为了区别staff

  public String getFilename() {
    return filename;
  }

  @XmlAttribute
  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getSignature() {
    return signature;
  }

  @XmlAttribute
  public void setSignature(String signature) {
    this.signature = signature;
  }

  public List<SystemDescription> getSystemDescription() {
    return systemDescription;
  }

  @XmlElement(name="system")
  public void setSystemDescription(List<SystemDescription> systemDescription) {
    this.systemDescription = systemDescription;
  }

  public void addSystemDescription(SystemDescription sys) {
   if(systemDescription == null) {
     systemDescription = new ArrayList<SystemDescription>();
   }
   systemDescription.add(sys);
  }
}

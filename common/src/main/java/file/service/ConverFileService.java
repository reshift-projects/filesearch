package file.service;

import java.io.File;

/**
 * Created by tianjian on 2017/11/12.
 */
public abstract class ConverFileService {
    private String type;
    private ConverFileService converFileService;
    public abstract String extractFile(File file);
    public String extractFileProxy(File file) throws Exception {
        if(file.getName().endsWith(type)) {
            return extractFile(file);
        } else if(converFileService != null){
            return converFileService.extractFileProxy(file);
        } else {
            throw new Exception("there is no handle");
        }
    }

    public ConverFileService(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void buildConverFileService(ConverFileService converFileService) {
        if(!converFileService.getType().equals(type)) {
            this.converFileService = converFileService;
        }
    }


}

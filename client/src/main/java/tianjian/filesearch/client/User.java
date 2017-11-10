package tianjian.filesearch.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/11/8
 * @description
 */
@Component
public class User {
    @Autowired
    private AsyncTask asyncTask;


    public void test() {
        asyncTask.start();
        asyncTask.start();
        asyncTask.start();
    }
}

package tianjian.filesearch.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/11/8
 * @description
 */
@Component
public class AsyncTask {

    @Autowired


    @Async("processExecutor")
    public void start() {
        double random = Math.random();
        int sleepInt = (int) (random  * 2000);
        try {
            System.out.println("=================processing=================");
            Thread.sleep(sleepInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

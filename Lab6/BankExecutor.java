package Lab6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankExecutor {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);    

    public void submitAction(BankAction action){
        executor.submit(action);
    }

    public void shoutdown(){
        executor.shutdown();
    }
}

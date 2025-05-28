package Lab6;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountManager {
    private final Map<Integer, Account> accounts = new ConcurrentHashMap<>();
    private final AtomicInteger atomicId = new AtomicInteger(1);
    
    private AccountManager() {}

    private static class Holder {
        private static final AccountManager INSTANCE = new AccountManager();
    }

    public static AccountManager getInstance() {
        return Holder.INSTANCE;
    }

    public int openAccount(){
        int id = atomicId.getAndIncrement();
        accounts.put(id, new Account(id));
        System.out.println(id + "|" + id);
        return id;
    }

    public void closeAccount(int id){
        accounts.remove(id - 1);
    }

    public Account getAccount(int id){
        System.out.println(id + "|" + id);
        System.out.println(accounts.size());
        return accounts.get(id);
    }

    public int getAmmount(){
        return accounts.size();
    }
    public int getLastId(){
        return atomicId.get() - 1;
    }
}

package Repositories;

import Contexts.AccountContext;
import RESTshared.AccountDTO;

import java.util.ArrayList;

public class AccountRepository {

    private AccountContext accountContext;

    public AccountRepository(AccountContext accountContext){
        this.accountContext = accountContext;
    }

    public boolean createAccount(String username, String password){
        return this.accountContext.createAccount(username, password);
    }

    public ArrayList<AccountDTO> getAccounts(){
        return this.accountContext.getAccounts();
    }

    public boolean updateAccount(int id){
        return this.accountContext.updateAccount(id);
    }

    public boolean deleteAccount(int id){
        return this.accountContext.deleteAccount(id);
    }
}

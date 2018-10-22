package RESTLogin.Contexts;

import RESTLogin.RESTshared.AccountDTO;
import java.util.ArrayList;

public interface AccountContext {

    boolean createAccount(String username, String password);
    ArrayList<AccountDTO> getAccounts();
    boolean deleteAccount(int id);
    boolean updateAccount(int id);
}

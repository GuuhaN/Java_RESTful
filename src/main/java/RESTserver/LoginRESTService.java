package RESTserver;

import Contexts.AccountContext;
import Contexts.AccountSqlContext;
import RESTshared.AccountDTO;
import Repositories.AccountRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/Account")
public class LoginRESTService {

    AccountContext accountContext;
    AccountRepository accountRepository;
    public LoginRESTService(){
        this.accountContext = new AccountSqlContext();
        this.accountRepository = new AccountRepository(accountContext);
    }

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response loginAccount(AccountDTO accountDTO){

        if(accountDTO == null)
            return Response.status(400).build();

        ArrayList<AccountDTO> allAccounts = accountRepository.getAccounts();
        for(int i = 0; i < allAccounts.size(); i++)
        {
            if(allAccounts.get(i).getUsername().equals(accountDTO.getUsername()) && allAccounts.get(i).getPassword().equals(accountDTO.getPassword())){
                System.out.println("LOGGED IN !");
                return Response.ok().build();
            }
            else
                return Response.status(400).build();
        }
        return Response.status(500).build();
    }
}

package RESTLogin.RESTserver;

import RESTLogin.Contexts.AccountContext;
import RESTLogin.Contexts.AccountSqlContext;
import RESTLogin.RESTshared.AccountDTO;
import RESTLogin.Repositories.AccountRepository;

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
            return Response.status(400).entity(RESTResponseHelper.getErrorResponseString()).build();

        ArrayList<AccountDTO> allAccounts = accountRepository.getAccounts();
        for(int i = 0; i < allAccounts.size(); i++)
        {
            if(allAccounts.get(i).getUsername().equals(accountDTO.getUsername()) && allAccounts.get(i).getPassword().equals(accountDTO.getPassword())){
                System.out.println("LOGGED IN !");
                return Response.status(200).entity(RESTResponseHelper.getSuccessResponse(true)).build();
            }
            else
                return Response.status(400).entity(RESTResponseHelper.getSuccessResponse(false)).build();
        }
        return Response.status(500).entity(RESTResponseHelper.getErrorResponseString()).build();
    }
}

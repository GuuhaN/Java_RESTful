package RESTLogin.RESTclient;

import RESTLogin.RESTshared.AccountDTO;
import RESTLogin.RESTshared.LoginResponse;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class AccountRESTLogin {

    Gson gson = new Gson();
    final String baseUrl = "http://localhost:8099/Account";

    // Login client request setup
    public boolean login(String username, String password) {

        AccountDTO accountDTO = new AccountDTO(0, username, password);
        final String query = baseUrl + "/login";
        LoginResponse loginResponse = newPostRequest(accountDTO, query);
        return loginResponse.isSuccess();
    }

    // Post Request
    public LoginResponse newPostRequest(AccountDTO accountDTO, String query){
        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");
        StringEntity params;
        params = new StringEntity(gson.toJson(accountDTO), ContentType.APPLICATION_JSON);
        httpPost.setEntity(params);

        return executeUriRequest(httpPost);
    }

    // Executing the Request and returns response
    public LoginResponse executeUriRequest(HttpUriRequest httpUriRequest){
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpUriRequest)) {
            System.out.println("[Status Line] : " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            System.out.println("[Entity] : " + entityString);
            LoginResponse loginResponse = gson.fromJson(entityString, LoginResponse.class);
            return loginResponse;
        } catch (IOException e) {
            System.out.println(e.toString());
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setSuccess(false);
            return loginResponse;
        }
    }
}

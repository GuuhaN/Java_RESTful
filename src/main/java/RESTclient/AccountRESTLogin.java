package RESTclient;

import RESTshared.AccountDTO;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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

    // Base URL of the RESTful server, check the port if its equivalent to the actual server
    final String baseUrl = "http://localhost:8099/Account";

    public boolean login(String username, String password) {

        AccountDTO accountDTO = new AccountDTO(0, username, password);
        final String query = baseUrl + "/login";
        newPostRequest(accountDTO, query);
        return false;
    }

    // Get request
    public void newGetRequest(String query){
        HttpGet httpGet = new HttpGet(query);
        httpGet.addHeader("content-type", "application/json");
        executeUriRequest(httpGet);
    }

    // Post request
    public void newPostRequest(AccountDTO accountDTO, String query){
        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");
        StringEntity params;
        params = new StringEntity(gson.toJson(accountDTO), ContentType.APPLICATION_JSON);
        httpPost.setEntity(params);

        System.out.println(httpPost.getEntity());
        executeUriRequest(httpPost);
    }

    public void executeUriRequest(HttpUriRequest httpUriRequest){
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpUriRequest)) {
            System.out.println("[Status Line] : " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            System.out.println("[Entity] : " + entityString);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}

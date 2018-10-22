package RESTLogin.RESTserver;

import RESTLogin.RESTshared.LoginResponse;
import com.google.gson.Gson;

public class RESTResponseHelper {

    private static final Gson gson = new Gson();

    public static String getErrorResponseString() {
        LoginResponse response = new LoginResponse();
        response.setSuccess(false);
        String output = gson.toJson(response);
        System.out.println("[Server response] " + output);
        return output;
    }

    public static String getSuccessResponse(boolean success) {
        LoginResponse response = new LoginResponse();
        response.setSuccess(success);
        String output = gson.toJson(response);
        System.out.println("[Server response] " + output);
        return output;
    }
}
package RESTshared;

public class AccountDTO {

    public int accountId;
    public String username, password;

    // Empty constructor needed for DTO
    public AccountDTO() {
        // Nothing
    }

    public AccountDTO(int accountId, String username, String password){
        this.accountId = accountId;
        this.username = username;
        this.password = password;
    }

    public int getAccountId(){
        return this.accountId;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

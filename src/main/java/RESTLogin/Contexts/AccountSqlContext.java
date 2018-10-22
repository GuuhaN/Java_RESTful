package RESTLogin.Contexts;

import RESTLogin.RESTshared.AccountDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountSqlContext implements AccountContext {

    Connection connection;
    Statement stmt;
    final String connectionUrl = "jdbc:sqlserver://mssql.fhict.local;Database=dbi387964;user=dbi387964;password=Floofloxke7;"; // Prefix jdbc is essential

    @Override
    public boolean createAccount(String username, String password) {
        return false;
    }

    @Override
    public ArrayList<AccountDTO> getAccounts() {
        ArrayList<AccountDTO> accounts = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Connecting to database ...");
            connection = DriverManager.getConnection(connectionUrl);
            stmt = connection.createStatement();
            String sql = "SELECT * FROM UserAccount";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id  = rs.getInt("id");
                String username  = rs.getString("Username");
                String passord = rs.getString("Password");
                accounts.add(new AccountDTO(id, username, passord));
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return accounts;
    }

    @Override
    public boolean deleteAccount(int id) {
        return false;
    }

    @Override
    public boolean updateAccount(int id) {
        return false;
    }
}

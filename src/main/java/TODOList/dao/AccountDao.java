package TODOList.dao;

import TODOList.models.Account;
import TODOList.models.AccountVerifying;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Account validateAccount(Account account){
        String sql = "select * from accounts where username='" + account.getUsername() + "'";

        List<Account> accounts = jdbcTemplate.query(sql, new AccountMapper());

        if(accounts.isEmpty()) return null;

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(!passwordEncoder.matches(account.getPassword(), accounts.get(0).getPassword())) return null;

        return accounts.get(0);
    }

    public boolean validateCookies(Account account){
        String sql = "select * from accounts where username='" + account.getUsername() + "' and password='" + account.getPassword() + "'";
        List<Account> accounts = jdbcTemplate.query(sql, new AccountMapper());

        return !accounts.isEmpty();
    }

    public void deleteCookies(HttpServletResponse response){
        Cookie userCookie = new Cookie("username", null);
        userCookie.setMaxAge(0);
        response.addCookie(userCookie);

        Cookie passCookie = new Cookie("password", null);
        passCookie.setMaxAge(0);
        response.addCookie(passCookie);
    }


    public boolean activateAccount(String activateCode){
        String sql = "select * from accountsVerifying where activateCode='" + activateCode + "'";
        List<AccountVerifying> accountsVerifying = jdbcTemplate.query(sql, new AccountVerifyingMapper());

        if(accountsVerifying.isEmpty())
            return false;

        try{
            sql = "update accounts set activated=1 where id="+accountsVerifying.get(0).getAccountId();
            jdbcTemplate.execute(sql);

            sql = "delete from accountsVerifying WHERE activateCode='" + activateCode + "'";
            jdbcTemplate.execute(sql);

            return true;

        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }

        return true;
    }

}

class AccountMapper implements RowMapper<Account> {
    public Account mapRow(ResultSet rs, int arg1) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setUsername(rs.getString("username"));
        account.setPassword(rs.getString("password"));
        account.setEmail(rs.getString("email"));
        account.setFirstName(rs.getString("firstName"));
        account.setSecondName(rs.getString("secondName"));
        account.setActivated(rs.getInt("activated"));
        return account;
    }
}

class AccountVerifyingMapper implements RowMapper<AccountVerifying> {
    public AccountVerifying mapRow(ResultSet rs, int arg1) throws SQLException {
        AccountVerifying accountVerifying = new AccountVerifying();
        accountVerifying.setId(rs.getInt("id"));
        accountVerifying.setAccountId(rs.getInt("accountId"));
        accountVerifying.setActivateCode(rs.getString("activateCode"));
        return accountVerifying;
    }
}

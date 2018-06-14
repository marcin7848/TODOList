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
import java.util.Random;

public class AccountDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SendEmail sendEmail;

    public Account getAccount(int id){
        String sql = "select * from accounts where id='" + id + "'";
        List<Account> accounts = jdbcTemplate.query(sql, new AccountMapper());
        return accounts.get(0);
    }

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

    public Account validateCookiesReturnAcc(Account account){
        String sql = "select * from accounts where username='" + account.getUsername() + "' and password='" + account.getPassword() + "'";
        List<Account> accounts = jdbcTemplate.query(sql, new AccountMapper());

        return accounts.get(0);
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
        String sql = "select * from accountsVerifying where activateCode='" + activateCode + "' and type = 1";
        List<AccountVerifying> accountsVerifying = jdbcTemplate.query(sql, new AccountVerifyingMapper());

        if(accountsVerifying.isEmpty())
            return false;

        sql = "update accounts set activated=1 where id="+accountsVerifying.get(0).getAccountId();
        jdbcTemplate.execute(sql);

        sql = "delete from accountsVerifying WHERE activateCode='" + activateCode + "'";
        jdbcTemplate.execute(sql);

        return true;
    }

    public boolean checkExistanceAccount(Account account){
        String sql = "select * from accounts where username='" + account.getUsername() + "' or email='" + account.getEmail() + "'";
        List<Account> accounts = jdbcTemplate.query(sql, new AccountMapper());

        return !accounts.isEmpty();
    }

    public String hashPassword(String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public String generateRandomString(int length){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@$%^*()><";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    public int registerAccount(Account account){
        if(checkExistanceAccount(account))
            return 2; //account's already existed

        String sql = "insert into accounts (username, email, password, firstName, secondName, activated)" +
                " VALUES (?, ?, ?, ?, ?, 0)";

        jdbcTemplate.update(sql, account.getUsername(), account.getEmail(),
                hashPassword(account.getPassword()), account.getFirstName(), account.getSecondName());

        sql = "select * from accounts where username='" + account.getUsername() + "' and email='" + account.getEmail() + "'";
        List<Account> accounts = jdbcTemplate.query(sql, new AccountMapper());

        sql = "insert into accountsVerifying (accountId, activateCode, type) VALUES (?, ?, 1)";
        String random = generateRandomString(40);
        jdbcTemplate.update(sql, accounts.get(0).getId(), random);

        sendEmail.send(accounts.get(0).getEmail(), "Rejestracja TODOList", "To activate your account use this code: \n"+random);

        return 1; //created
    }


    public int editAccount(Account account, String firstName, String secondName, String password, String email){
        String sql = "select * from accounts where id='" + account.getId() + "'";
        List<Account> accounts = jdbcTemplate.query(sql, new AccountMapper());

        if(!email.equals(accounts.get(0).getEmail())){
            sql = "select * from accounts where email='" + email + "'";
            List<Account> emailAcc = jdbcTemplate.query(sql, new AccountMapper());
            if(!emailAcc.isEmpty())
                return 2; //email's exist
        }

        sql = "update accounts SET firstName=?, secondName=?, email=? WHERE id='" + account.getId() + "'";
        jdbcTemplate.update(sql, firstName, secondName, email);

        if(password != null && !password.equals("")){
            sql = "update accounts SET password=? WHERE id='" + account.getId() + "'";
            jdbcTemplate.update(sql, hashPassword(password));
        }

        return 1;
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
        accountVerifying.setType(rs.getInt("type"));
        return accountVerifying;
    }
}

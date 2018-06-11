package TODOList.dao;

import TODOList.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
}

class AccountMapper implements RowMapper<Account> {
    public Account mapRow(ResultSet rs, int arg1) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setUsername(rs.getString("username"));
        account.setPassword(rs.getString("password"));
        account.setEmail(rs.getString("email"));
        return account;
    }
}

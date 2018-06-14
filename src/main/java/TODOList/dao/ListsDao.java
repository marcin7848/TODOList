package TODOList.dao;

import TODOList.models.Account;
import TODOList.models.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ListsDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addList(Account account, String name, String colour){
        String sql = "select * from lists where accountId='" + account.getId() + "' and name='" + name + "'";
        List<Lists> lists = jdbcTemplate.query(sql, new ListsMapper());

        if(!lists.isEmpty())
            return 2; //List's already existed

        sql = "insert into lists (accountId, name, colour)" +
                " VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, account.getId(), name, colour);

        return 1;
    }
}

class ListsMapper implements RowMapper<Lists> {
    public Lists mapRow(ResultSet rs, int arg1) throws SQLException {
        Lists lists = new Lists();
        lists.setId(rs.getInt("id"));
        lists.setAccountId(rs.getInt("accountId"));
        lists.setName(rs.getString("name"));
        lists.setColour(rs.getString("colour"));
        return lists;
    }
}
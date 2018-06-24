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

    public Lists getList(Account account, int id){
        String sql = "select * from lists where accountId='" + account.getId() + "' and id='" + id + "'";
        List<Lists> lists = jdbcTemplate.query(sql, new ListsMapper());

        if(lists.isEmpty())
            return null; //List doesn't exist

        return lists.get(0);
    }

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

    public int editList(Account account, Lists lists, String name, String colour){
        Lists lists1 = getList(account, lists.getId());
        if(lists1 == null)
            return 2; //List doesn't exist

        String sql = "update lists SET name=?, colour=? WHERE id='" + lists1.getId() + "' and accountId='" + account.getId() + "'";
        jdbcTemplate.update(sql, name, colour);

        return 1;
    }

    public int deleteList(Account account, int id){
        Lists lists = getList(account, id);
        if (lists == null)
            return 2; //List doesn't exist

        String sql = "delete from lists WHERE id='" + lists.getId() + "'";
        jdbcTemplate.execute(sql);

        sql = "delete from tasks WHERE listId='" + lists.getId() + "'";
        jdbcTemplate.execute(sql);

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
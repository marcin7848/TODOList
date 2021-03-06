package TODOList.dao;

import TODOList.models.Account;
import TODOList.models.Lists;
import TODOList.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
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

        sql = "select * from tasks where listId='" + id + "' ORDER BY date";
        List<Task> tasks = jdbcTemplate.query(sql, new TaskMapper());
        lists.get(0).setTasks(tasks);

        return lists.get(0);
    }
    public Lists getList(Account account, String name){
        String sql = "select * from lists where accountId='" + account.getId() + "' and name='" + name + "'";
        List<Lists> lists = jdbcTemplate.query(sql, new ListsMapper());

        if(lists.isEmpty())
            return null; //List doesn't exist

        sql = "select * from tasks where listId='" + lists.get(0).getId() + "' ORDER BY date";
        List<Task> tasks = jdbcTemplate.query(sql, new TaskMapper());
        lists.get(0).setTasks(tasks);

        return lists.get(0);
    }


    public List<Lists> getLists(Account account){
        String sql = "select * from lists where accountId='" + account.getId() + "' ORDER by numOrder";
        List<Lists> lists = jdbcTemplate.query(sql, new ListsMapper());

        if(lists.isEmpty())
            return null; //List doesn't exist

        int i=0;
        for(Lists list1: lists){
            sql = "select * from tasks where listId='" + list1.getId() + "' ORDER BY date";
            List<Task> tasks = jdbcTemplate.query(sql, new TaskMapper());
            lists.get(i).setTasks(tasks);
            i++;
        }

        return lists;
    }

    public int addList(Account account, String name, String colour, int numOrder, boolean showed){
        String sql = "select * from lists where accountId='" + account.getId() + "' and name='" + name + "'";
        List<Lists> lists = jdbcTemplate.query(sql, new ListsMapper());

        if(!lists.isEmpty())
            return 2; //List's already existed


        List<Lists> checkNum = getLists(account);
        if(checkNum != null) {
            numOrder = checkNum.size() + 1; //temporary - add new list as the last
            if (checkNum.size() + 1 != numOrder)
                return 3; //bad numOrder
        }else{
            numOrder = 1;
        }
        sql = "insert into lists (accountId, name, colour, numOrder, showed)" +
                " VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, account.getId(), name, colour, numOrder, showed);

        return 1;
    }

    public int editList(Account account, Lists lists, String name, String colour){
        Lists lists1 = getList(account, lists.getId());
        if(lists1 == null)
            return 2; //List doesn't exist

        if(!lists1.getName().equals(name)){
            Lists lists2 = getList(account, name);
            if(lists2 != null)
                return 3; //List name exists
        }

        String sql = "update lists SET name=?, colour=? WHERE id='" + lists1.getId() + "' and accountId='" + account.getId() + "'";
        jdbcTemplate.update(sql, name, colour);

        return 1;
    }

    public int deleteList(Account account, int id){
        Lists lists = getList(account, id);
        if (lists == null)
            return 2; //List doesn't exist

        String sql = "update lists SET numOrder=numOrder-1 WHERE accountId='" + account.getId() + "' and numOrder>'" + lists.getNumOrder() + "' ";
        jdbcTemplate.update(sql);

        sql = "delete from lists WHERE id='" + lists.getId() + "'";
        jdbcTemplate.execute(sql);

        sql = "delete from tasks WHERE listId='" + lists.getId() + "'";
        jdbcTemplate.execute(sql);

        return 1;
    }

    public int changeShowedList(Account account, int id) {
        Lists lists = getList(account, id);
        if (lists == null)
            return 2; //List doesn't exist

        String sql = "update lists SET showed = NOT showed WHERE id='" + id + "' and accountId='" + account.getId() + "'";
        jdbcTemplate.update(sql);

        return 1;
    }

    public int changeNumOrderList(Account account, int id, int newNumOrder) {
        Lists listCheck = getList(account, id);
        if (listCheck == null)
            return 2; //List doesn't exist

        List<Lists> lists = getLists(account);

        lists.sort(Comparator.comparing(Lists::getNumOrder));

        lists.remove(listCheck.getNumOrder()-1);

        listCheck.setNumOrder(newNumOrder-1);

        lists.add(listCheck.getNumOrder(), listCheck);

        int i=1;
        for (Lists list : lists) {
            String sql = "update lists SET numOrder = ? WHERE id='" + list.getId() + "' and accountId='" + account.getId() + "'";
            jdbcTemplate.update(sql, i);
            i++;
        }

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
        lists.setNumOrder(rs.getInt("numOrder"));
        lists.setShowed(rs.getBoolean("showed"));
        return lists;
    }
}
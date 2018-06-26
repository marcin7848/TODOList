package TODOList.dao;

import TODOList.models.Account;
import TODOList.models.Lists;
import TODOList.models.Task;
import TODOList.services.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class TaskDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ListService listService;

    public Task getTask(Account account, int id){
        String sql = "select * from tasks where id='" + id + "'";
        List<Task> task = jdbcTemplate.query(sql, new TaskMapper());

        if(task.isEmpty())
            return null; //task doesn't exist

        sql = "select * from lists where accountId='" + account.getId() + "' and id='" + task.get(0).getListId() + "'";
        List<Lists> lists = jdbcTemplate.query(sql, new ListsMapper());

        if(lists.isEmpty())
            return null; //this task isn't yours

        return task.get(0);
    }

    public List<Task> getTasks(Account account, Lists lists){
        String sql = "select * from lists where accountId='" + account.getId() + "' and id='" + lists.getId() + "'";
        List<Lists> list = jdbcTemplate.query(sql, new ListsMapper());

        if(list.isEmpty())
            return null; //List doesn't exist

        sql = "select * from tasks where listId='" + lists.getId() + "'";
        List<Task> task = jdbcTemplate.query(sql, new TaskMapper());

        return task;
    }

    public int addTask(Account account, Task task){
        Lists lists = listService.getList(account, task.getListId());

        if(lists == null)
            return 2; //lists doesn't exist


        String sql = "insert into tasks (listId, name, comment, priority, date, repeatTime, done)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, task.getListId(), task.getName(), task.getComment(), task.getPriority(),
                task.getDate(), task.getRepeatTime(), task.getDone());


        return 1;
    }

    public int editTask(Account account, Task task, int listId, String name, String comment, int priority, Date date, int repeatTime, int done){
        Task taskCheck = getTask(account, task.getId());
        if(taskCheck == null)
            return 2; //task doesn't exist

        Lists lists = listService.getList(account, listId);
        if(lists == null)
            return 3; //lists doesn't exist

        String sql = "update tasks SET listId=?, name=?, comment=?, priority=?, date=?, repeatTime=?, done=? WHERE id='" + task.getId() + "'";
        jdbcTemplate.update(sql, listId, name, comment, priority, date, repeatTime, done);

        return 1;
    }

    public int deleteTask(Account account, int id){
        Task taskCheck = getTask(account, id);
        if(taskCheck == null)
            return 2; //task doesn't exist

        String sql = "delete from tasks WHERE id='" + id + "'";
        jdbcTemplate.execute(sql);

        return 1;
    }

    public List<Task> getTasksRemindersToday(Account account){
        String sql = "select t.* from tasks t, lists l where l.accountId='" + account.getId() + "' and l.id = t.listId and date(t.date)=CURRENT_DATE() and t.done=0 ORDER BY t.date LIMIT 6;";
        List<Task> tasks = jdbcTemplate.query(sql, new TaskMapper());

        if(tasks.isEmpty())
            return null; //no tasks

        return tasks;
    }

}

class TaskMapper implements RowMapper<Task> {
    public Task mapRow(ResultSet rs, int arg1) throws SQLException {
        Task task = new Task();
        task.setId(rs.getInt("id"));
        task.setListId(rs.getInt("listId"));
        task.setName(rs.getString("name"));
        task.setComment(rs.getString("comment"));
        task.setPriority(rs.getInt("priority"));
        task.setDate(rs.getTimestamp("date"));
        task.setRepeatTime(rs.getInt("repeatTime"));
        task.setDone(rs.getInt("done"));
        return task;
    }
}
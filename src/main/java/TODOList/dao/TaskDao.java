package TODOList.dao;

import TODOList.models.Lists;
import TODOList.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TaskDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;


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
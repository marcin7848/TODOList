package TODOList.services;

import TODOList.dao.TaskDao;
import TODOList.models.Account;
import TODOList.models.Lists;
import TODOList.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class TaskService implements TaskServiceInterface{

    @Autowired
    private TaskDao taskDao;

    public Task getTask(Account account, int id){
        return taskDao.getTask(account, id);
    }

    public List<Task> getTasks(Account account, Lists lists){
        return taskDao.getTasks(account, lists);
    }

    public int addTask(Account account, Task task){
        return taskDao.addTask(account, task);
    }

    public int editTask(Account account, Task task, int listId, String name, String comment, int priority, Date date, int repeatTime, boolean done){
        return taskDao.editTask(account, task, listId, name, comment, priority, date, repeatTime, done);
    }

    public int deleteTask(Account account, int id){
        return taskDao.deleteTask(account, id);
    }

    public List<Task> getTasksRemindersToday(Account account){
        return taskDao.getTasksRemindersToday(account);
    }

    public int doTask(Account account, int id){
        return taskDao.doTask(account, id);
    }
}

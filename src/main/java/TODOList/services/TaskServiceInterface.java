package TODOList.services;


import TODOList.models.Account;
import TODOList.models.Lists;
import TODOList.models.Task;

import java.util.Date;
import java.util.List;

public interface TaskServiceInterface {
    Task getTask(Account account, int id);
    List<Task> getTasks(Account account, Lists lists);
    int addTask(Account account, Task task);
    int editTask(Account account, Task task, int listId, String name, String comment, int priority, Date date, int repeatTime, int done);
    int deleteTask(Account account, int id);
    List<Task> getTasksRemindersToday(Account account);
}

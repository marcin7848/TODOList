package TODOList.services;

import TODOList.dao.TaskDao;
import TODOList.models.Account;
import TODOList.models.Lists;
import TODOList.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    
}

package TODOList.services;


import TODOList.models.Account;
import TODOList.models.Lists;
import TODOList.models.Task;

import java.util.List;

public interface TaskServiceInterface {
    Task getTask(Account account, int id);
    List<Task> getTasks(Account account, Lists lists);

}

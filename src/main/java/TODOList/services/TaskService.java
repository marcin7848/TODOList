package TODOList.services;

import TODOList.dao.ListsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TaskService implements TaskServiceInterface{

    @Autowired
    private ListsDao listsDao;



}

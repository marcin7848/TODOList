package TODOList.services;

import TODOList.dao.ListsDao;
import TODOList.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListService implements ListServiceInterface{

    @Autowired
    private ListsDao listsDao;

    public int addList(Account account, String name, String colour){
        return listsDao.addList(account, name, colour);
    }

}

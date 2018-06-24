package TODOList.services;

import TODOList.dao.ListsDao;
import TODOList.models.Account;
import TODOList.models.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListService implements ListServiceInterface{

    @Autowired
    private ListsDao listsDao;

    public int addList(Account account, String name, String colour){
        return listsDao.addList(account, name, colour);
    }

    public int editList(Account account, Lists lists, String name, String colour){
        return listsDao.editList(account, lists, name, colour);
    }

    public Lists getList(Account account, int id){
        return listsDao.getList(account, id);
    }

    public int deleteList(Account account, int id){
        return listsDao.deleteList(account, id);
    }
}

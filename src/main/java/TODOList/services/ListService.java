package TODOList.services;

import TODOList.dao.ListsDao;
import TODOList.models.Account;
import TODOList.models.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListService implements ListServiceInterface{

    @Autowired
    private ListsDao listsDao;

    public int addList(Account account, String name, String colour, int numOrder, boolean showed){
        return listsDao.addList(account, name, colour, numOrder, showed);
    }

    public int editList(Account account, Lists lists, String name, String colour){
        return listsDao.editList(account, lists, name, colour);
    }

    public Lists getList(Account account, int id){
        return listsDao.getList(account, id);
    }
    public Lists getList(Account account, String name){
        return listsDao.getList(account, name);
    }

    public int deleteList(Account account, int id){
        return listsDao.deleteList(account, id);
    }
    public List<Lists> getLists(Account account){
        return listsDao.getLists(account);
    }

    public int changeShowedList(Account account, int id){
        return listsDao.changeShowedList(account, id);
    }

    public int changeNumOrderList(Account account, int id, int newNumOrder){
        return listsDao.changeNumOrderList(account, id, newNumOrder);
    }
}

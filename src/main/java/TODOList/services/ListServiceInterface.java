package TODOList.services;

import TODOList.models.Account;
import TODOList.models.Lists;

import java.util.List;

public interface ListServiceInterface {
    int addList(Account account, String name, String colour, int numOrder, boolean showed);
    int editList(Account account, Lists lists, String name, String colour);
    Lists getList(Account account, int id);
    int deleteList(Account account, int id);
    List<Lists> getLists(Account account);
    int changeShowedList(Account account, int id);
    int changeNumOrderList(Account account, int id, int newNumOrder);
}

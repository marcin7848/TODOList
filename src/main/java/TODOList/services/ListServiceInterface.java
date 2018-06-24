package TODOList.services;

import TODOList.models.Account;
import TODOList.models.Lists;

public interface ListServiceInterface {
    int addList(Account account, String name, String colour);
    int editList(Account account, Lists lists, String name, String colour);
    Lists getList(Account account, int id);
}

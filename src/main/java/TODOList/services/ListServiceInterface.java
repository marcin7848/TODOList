package TODOList.services;

import TODOList.models.Account;

public interface ListServiceInterface {
    int addList(Account account, String name, String colour);
}

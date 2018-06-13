package TODOList.services;

import TODOList.models.Account;

public interface AccountServiceInterface {
    Account validateAccount(Account account);
    boolean activateAccount(String activateCode);

}

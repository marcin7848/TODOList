package TODOList.services;

import TODOList.models.Account;

public interface AccountServiceInterface {
    Account validateAccount(Account account);
    boolean activateAccount(String activateCode);
    int registerAccount(Account account);
    int editAccount(Account account, String firstName, String secondName, String password, String email);
    Account getAccount(int id);
    boolean resendActivateCode(String email);
    boolean sendResetPassword(String email, String host);
    boolean checkResetPasswordCode(String code);
    boolean resetPassword(String code, String password);
}

package TODOList.services;

import TODOList.dao.AccountDao;
import TODOList.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

@Service
public class AccountService implements AccountServiceInterface {

    @Autowired
    private AccountDao accountDao;

    private static AccountDao accountDaoStatic;

    @PostConstruct
    public void initAccountDao(){
        accountDaoStatic = accountDao;
    }

    public Account getAccount(int id){
        return accountDao.getAccount(id);
    }

    public Account validateAccount(Account account){
        return accountDao.validateAccount(account);
    }

    public static boolean validateCookies(Account account){
        return accountDaoStatic.validateCookies(account);
    }

    public static Account validateCookiesReturnAcc(Account account){
        return accountDaoStatic.validateCookiesReturnAcc(account);
    }

    public static void deleteCookies(HttpServletResponse response){
        accountDaoStatic.deleteCookies(response);
    }

    public boolean activateAccount(String activateCode) {
        return accountDao.activateAccount(activateCode);
    }

    public int registerAccount(Account account){
        return accountDao.registerAccount(account);
    }

    public int editAccount(Account account, String firstName, String secondName, String password, String email){
        return accountDao.editAccount(account, firstName, secondName, password, email);
    }
    public boolean resendActivateCode(String email){
        return accountDao.resendActivateCode(email);
    }
}

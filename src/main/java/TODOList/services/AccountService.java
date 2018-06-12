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

    public Account validateAccount(Account account){
        return accountDao.validateAccount(account);
    }

    public static boolean validateCookies(Account account){
        return accountDaoStatic.validateCookies(account);
    }

    public static void deleteCookies(HttpServletResponse response){
        accountDaoStatic.deleteCookies(response);
    }
}

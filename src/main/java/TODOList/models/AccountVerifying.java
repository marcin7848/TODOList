package TODOList.models;

public class AccountVerifying {
    private int id;
    private int accountId;
    private String activateCode;
    private int type;
    /*type:
    1 - activate Account
    */


    public AccountVerifying() {
    }

    public AccountVerifying(int id, int accountId, String activateCode, int type) {
        this.id = id;
        this.accountId = accountId;
        this.activateCode = activateCode;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getActivateCode() {
        return activateCode;
    }

    public void setActivateCode(String activateCode) {
        this.activateCode = activateCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

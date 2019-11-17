package cn.fjmz.agt.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RememberPasswordEntity extends RealmObject {
    @PrimaryKey
    private String account;
    private String password;
    private boolean isSaveAccount;
    private boolean isAutomaticLogin;
    private String comName;

    public RememberPasswordEntity() {

    }


    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public boolean getIsSaveAccount() {
        return isSaveAccount;
    }

    public void setIsSaveAccount(boolean isSaveAccount) {
        this.isSaveAccount = isSaveAccount;
    }

    public boolean getIsAutomaticLogin() {
        return isAutomaticLogin;
    }

    public void setIsAutomaticLogin(boolean isAutomaticLogin) {
        this.isAutomaticLogin = isAutomaticLogin;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

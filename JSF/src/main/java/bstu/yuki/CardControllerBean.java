package bstu.yuki;

import bstu.yuki.db.models.User;

import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import java.sql.SQLException;

import static bstu.yuki.db.operations.dbOperations.GetUser;
import static bstu.yuki.db.operations.dbOperations.reduceMoney;

@ManagedBean
@Named
public class CardControllerBean {
    private User user = new User();
    private int sumOfPay = 600;
    private boolean isSuccess = false;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void isValid() throws SQLException {
        user = GetUser(user.getName(),user.getPassword());
    }

    public void tryPay()
    {
        isSuccess = reduceMoney(user.getName(), sumOfPay);
    }

}

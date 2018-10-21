package bstu.yuki.db.models;

public class User {
    private String name;
    private String info;
    private String password;
    private int money;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}

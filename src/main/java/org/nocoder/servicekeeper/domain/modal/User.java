package org.nocoder.servicekeeper.domain.modal;

public class User {

    private int id;
    private String name;
    private String password;
    private String createTime;
    private String lastSignInTime;
    private String lastSignInIp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastSignInTime() {
        return lastSignInTime;
    }

    public void setLastSignInTime(String lastSignInTime) {
        this.lastSignInTime = lastSignInTime;
    }

    public String getLastSignInIp() {
        return lastSignInIp;
    }

    public void setLastSignInIp(String lastSignInIp) {
        this.lastSignInIp = lastSignInIp;
    }
}

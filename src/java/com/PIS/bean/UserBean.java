
package com.PIS.bean;

public class UserBean {
    private int userid;
    private String username;
    private String password;
    private String name;
    private String address;
    private String mobile;
    private String email;

    public UserBean() {
    }
    

    public UserBean(int userid, String username, String password, String name, String address, String mobile, String email) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
}

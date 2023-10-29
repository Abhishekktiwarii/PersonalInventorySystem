
package com.PIS.dao;

import com.PIS.bean.UserBean;
import com.PIS.utility.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    static Connection conn;
    
    //addUser()
    public int addUser(UserBean ub){
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "insert into users(username, password, name, address, mobile, email) values ('"+ub.getUsername()+"','"+ub.getPassword()+"','"+ub.getName()+"','"+ub.getAddress()+"','"+ub.getMobile()+"','"+ub.getEmail()+"')";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return r;
    }
    
    //deleteUser()
    public int deleteUser(int i){
        int r=0;
        conn = ConnectionPool.connectDB();
        String sql = "delete from users where userid='"+i+"'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    //updateUser()
    public int updateEmp(UserBean eb,int old){
        int r = 0;
        conn = ConnectionPool.connectDB();
        String str = "update users set userid='"+eb.getUserid()+"', username='"+eb.getUsername()+"', password='"+eb.getPassword()+"',name='"+eb.getName()+"',address='"+eb.getAddress()+"',mobile='"+eb.getMobile()+"',email='"+eb.getEmail()+"' where userid='"+old+"'";
        Statement stmt;
        try {
            stmt = conn.createStatement();
            r = stmt.executeUpdate(str);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    //findId()
    public int findId(String username,String password){
        conn = ConnectionPool.connectDB();
        String sql = "select * from users where username='"+username+"' && password='"+password+"'";
        UserBean eb = new UserBean();
        int u=0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery(sql);
            
            while(rs.next()){
                eb.setUserid(rs.getInt("userid"));
                eb.setUsername(rs.getString("username"));
                eb.setMobile(rs.getString("mobile"));
                eb.setPassword(rs.getString("password"));
                eb.setName(rs.getString("name"));
                eb.setEmail(rs.getString("email"));
                eb.setAddress(rs.getString("address"));
            }
           u=eb.getUserid();
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    //findAll()
    public ArrayList<UserBean> findAll(){
        conn = ConnectionPool.connectDB();
        ArrayList<UserBean> al = new ArrayList<UserBean>();
        String sql = "select * from users";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                UserBean eb = new UserBean();
                eb.setUserid(rs.getInt("userid"));
                eb.setUsername(rs.getString("username"));
                eb.setMobile(rs.getString("mobile"));
                eb.setPassword(rs.getString("password"));
                eb.setName(rs.getString("name"));
                eb.setEmail(rs.getString("email"));
                eb.setAddress(rs.getString("address"));
                al.add(eb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
    //findById()
    public UserBean findById(int id){
        conn = ConnectionPool.connectDB();
        String sql = "select * from users where userid='"+id+"'";
        UserBean eb = new UserBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                eb.setUserid(rs.getInt("userid"));
                eb.setUsername(rs.getString("username"));
                eb.setMobile(rs.getString("mobile"));
                eb.setPassword(rs.getString("password"));
                eb.setName(rs.getString("name"));
                eb.setEmail(rs.getString("email"));
                eb.setAddress(rs.getString("address"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eb;
    }
    // getUserByUsernameAndPassword
    public UserBean getUserByUsernameandPassword(String username,String password){
        conn = ConnectionPool.connectDB();
        String sql = "select * from users where username='"+username+"' && password='"+password+"'";
        UserBean eb = new UserBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                eb.setUserid(rs.getInt("userid"));
                eb.setUsername(rs.getString("username"));
                eb.setMobile(rs.getString("mobile"));
                eb.setPassword(rs.getString("password"));
                eb.setName(rs.getString("name"));
                eb.setEmail(rs.getString("email"));
                eb.setAddress(rs.getString("address"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eb;
    }
    //forgotPassoword()
    public String forgotPassword(String username, String name){
        conn = ConnectionPool.connectDB();
        String password="";
        String sql="select password from users where username='"+username+"'&& name='"+name+"'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            password+=rs.getString("password");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return password;
    }
    public static void main(String[] args) {
        
        
        //forgotPassword()
        UserDAO ud = new UserDAO();
        String username="Anand123";
        String name = "12345";
        int a = ud.findId(username,name);
        System.out.println("userNaem: "+a);
        
    }
    
}

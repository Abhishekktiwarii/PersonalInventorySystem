package com.PIS.dao;

import com.PIS.bean.ExpenseCategoryBean;
import static com.PIS.dao.IncomeDAO.conn;
import com.PIS.utility.ConnectionPool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpenseCategoryDAO {
    
    //add income
    public int addOnBankBook(ExpenseCategoryBean ib){
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "insert into expenses_category(exp_catname, exp_catdetails, userid) values ('"+ib.getExp_catname()+"','"+ib.getExp_catdetails()+"','"+ib.getUserid()+"')";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    //delete from bankbook()
    public int deleteBank_book(int i){
        int r=0;
        conn = ConnectionPool.connectDB();
        String sql = "delete from expenses_category where exp_catid='"+i+"'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    //update income()
    public int updateIncome(ExpenseCategoryBean eb,int old){
        int r = 0;
//        exp_catid='"+eb.getExpcatid()+"',
        conn = ConnectionPool.connectDB();
        String str = "update expenses_category set exp_catname='"+eb.getExp_catname()+"', exp_catdetails='"+eb.getExp_catdetails()+"', userid='"+eb.getUserid()+"' where exp_catid='"+old+"'";
        Statement stmt;
        try {
            stmt = conn.createStatement();
            r = stmt.executeUpdate(str);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
//    findAll
    public ArrayList<ExpenseCategoryBean> findAll(int id){
        conn = ConnectionPool.connectDB();
        ArrayList<ExpenseCategoryBean> al = new ArrayList<ExpenseCategoryBean>();
        String sql = "select * from expenses_category where userid='"+id+"'";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                ExpenseCategoryBean ib = new ExpenseCategoryBean();
                ib.setExpcatid(rs.getInt("exp_catid"));
                ib.setExp_catname(rs.getString("exp_catname"));
                ib.setExp_catdetails(rs.getString("exp_catdetails"));
                ib.setUserid(rs.getInt("userid"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseCategoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
//    FindId()
    public int findId(String username,String password){
        conn = ConnectionPool.connectDB();
        String sql = "select * from expenses_category where exp_catname='"+username+"' && exp_catdetails='"+password+"'";
        ExpenseCategoryBean eb = new ExpenseCategoryBean();
        int u=0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery(sql);
            
            while(rs.next()){
                eb.setUserid(rs.getInt("userid"));
                eb.setExp_catname(rs.getString("exp_catname"));
                eb.setExp_catname(rs.getString("exp_catdetails"));
                eb.setExpcatid(rs.getInt("exp_catid"));
                
            }
           u=eb.getExpcatid();
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseCategoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    //findById()
    public ExpenseCategoryBean findById(int id){
        conn = ConnectionPool.connectDB();
        String sql = "select * from expenses_category where exp_catid='"+id+"'";
        ExpenseCategoryBean ib = new ExpenseCategoryBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                ib.setExpcatid(rs.getInt("exp_catid"));
                ib.setExp_catname(rs.getString("exp_catname"));
                ib.setExp_catdetails(rs.getString("exp_catdetails"));
                ib.setUserid(rs.getInt("userid"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseCategoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }
    public static void main(String[] args) {
        ExpenseCategoryBean bb = new ExpenseCategoryBean();
        ExpenseCategoryDAO id = new ExpenseCategoryDAO();
//        ArrayList<ExpenseCategoryBean> al = id.findAll();
        
//        for(ExpenseCategoryBean x:al){
//            System.out.println(x.getExpcatid()+":"+x.getExp_catname()+":"+x.getExp_catdetails()+":"+x.getUserid());
//        }   
    }   
}

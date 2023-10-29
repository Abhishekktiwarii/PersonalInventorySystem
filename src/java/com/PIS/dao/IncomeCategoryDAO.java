package com.PIS.dao;

import com.PIS.bean.ExpensesBean;
import com.PIS.bean.IncomeCategouryBean;
import static com.PIS.dao.IncomeDAO.conn;
import com.PIS.utility.ConnectionPool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IncomeCategoryDAO {
    
    //add income
    public int addExpens(IncomeCategouryBean ib){
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "insert into income_category(inc_catid, inc_catname, inc_catdetails,userid) values ('"+ib.getInc_catid()+"','"+ib.getInc_catname()+"','"+ib.getInc_catdetails()+"','"+ib.getUserid()+"')";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    //delete from bankbook()
    public int deleteExpense(int i){
        int r=0;
        conn = ConnectionPool.connectDB();
        String sql = "delete from income_category where inc_catid='"+i+"'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    //updateExpense()
    public int updateExpense(IncomeCategouryBean eb,int old){
        int r = 0;
        conn = ConnectionPool.connectDB();
        String str = "update income_category set inc_catid='"+eb.getInc_catid()+"',inc_catname='"+eb.getInc_catname()+"', inc_catdetails='"+eb.getInc_catdetails()+"', userid='"+eb.getUserid()+"' where inc_catid='"+old+"'";
        Statement stmt;
        try {
            stmt = conn.createStatement();
            r = stmt.executeUpdate(str);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
//    findAll
    public ArrayList<IncomeCategouryBean> findAll(int id){
        conn = ConnectionPool.connectDB();
        ArrayList<IncomeCategouryBean> al = new ArrayList<IncomeCategouryBean>();
        String sql = "select * from income_category where userid='"+id+"'";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                IncomeCategouryBean ib = new IncomeCategouryBean();
                ib.setInc_catid(rs.getInt("inc_catid"));
                ib.setInc_catname(rs.getString("inc_catname"));
                ib.setInc_catdetails(rs.getString("inc_catdetails"));
                ib.setUserid(rs.getInt("userid"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeCategouryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
//    FindId()
    public int findId(String username,String password){
        conn = ConnectionPool.connectDB();
        String sql = "select * from income_category where inc_catname='"+username+"' && inc_catdetails='"+password+"'";
        IncomeCategouryBean eb = new IncomeCategouryBean();
        int u=0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery(sql);
            
            while(rs.next()){
                eb.setUserid(rs.getInt("userid"));
                eb.setInc_catname(rs.getString("inc_catname"));
                eb.setInc_catdetails(rs.getString("inc_catdetails"));
                eb.setInc_catid(rs.getInt("inc_catid"));
                
            }
           u=eb.getInc_catid();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeCategouryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    //findById()
    public IncomeCategouryBean findById(int id){
        conn = ConnectionPool.connectDB();
        String sql = "select * from income_category where inc_catid='"+id+"'";
        IncomeCategouryBean ib = new IncomeCategouryBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                ib.setInc_catid(rs.getInt("inc_catid"));
                ib.setInc_catname(rs.getString("inc_catname"));
                ib.setInc_catdetails(rs.getString("inc_catdetails"));
                ib.setUserid(rs.getInt("userid"));      
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeCategouryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }
    public static void main(String[] args) {
//        IncomeCategouryBean bb = new IncomeCategouryBean();
//        IncomeCategoryDAO id = new IncomeCategoryDAO();
//        ArrayList<IncomeCategouryBean> al = id.findAll();
//        
//        for(IncomeCategouryBean x:al){
//            System.out.println(x.getInc_catid()+":"+x.getInc_catname()+":"+x.getUserid()+":"+x.getInc_catdetails()+":"+x.getUserid());
//        }   
    }
}

package com.PIS.dao;

import com.PIS.bean.IncomeBean;
import com.PIS.bean.UserBean;
import static com.PIS.dao.UserDAO.conn;
import com.PIS.utility.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IncomeDAO {
    static Connection conn;
    
    //add income
    public int addIncome(IncomeBean ib){
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "insert into incomes(inc_ac, userid, inc_catid, amount, transaction_date, receiveby,remark) values ('"+ib.getInc_ac()+"','"+ib.getUserid()+"','"+ib.getInc_catid()+"','"+ib.getAmount()+"','"+ib.getTransaction_date()+"','"+ib.getReceiveby()+"','"+ib.getRemark()+"')";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    //delete income()
    public int deleteIncome(int i){
        int r=0;
        conn = ConnectionPool.connectDB();
        String sql = "delete from incomes where inc_id='"+i+"'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    //update income()
    public int updateIncome(IncomeBean eb,int old){
        int r = 0;
        conn = ConnectionPool.connectDB();
        String str = "update incomes set inc_id='"+eb.getInc_id()+"',inc_ac='"+eb.getInc_ac()+"', userid='"+eb.getUserid()+"', inc_catid='"+eb.getInc_catid()+"', amount='"+eb.getAmount()+"',transaction_date='"+eb.getTransaction_date()+"',recvby='"+eb.getReceiveby()+"',remark='"+eb.getRemark()+"' where inc_id='"+old+"'";
        Statement stmt;
        try {
            stmt = conn.createStatement();
            r = stmt.executeUpdate(str);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    public ArrayList<IncomeBean> findAll(){
        conn = ConnectionPool.connectDB();
        ArrayList<IncomeBean> al = new ArrayList<IncomeBean>();
        String sql = "select * from incomes";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                IncomeBean ib = new IncomeBean();
                ib.setInc_id(rs.getInt("inc_id"));
                ib.setInc_ac(rs.getString("inc_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setInc_catid(rs.getString("inc_catid"));
                ib.setAmount(rs.getInt("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setReceiveby(rs.getString("receiveby"));
                ib.setRemark(rs.getString("remark"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
    //findById()
    public IncomeBean findById(int id){
        conn = ConnectionPool.connectDB();
        String sql = "select * from incomes where inc_id='"+id+"'";
        IncomeBean ib = new IncomeBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                ib.setInc_id(rs.getInt("inc_id"));
                ib.setInc_ac(rs.getString("inc_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setInc_catid(rs.getString("inc_catid"));
                ib.setAmount(rs.getInt("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setReceiveby(rs.getString("receiveby"));
                ib.setRemark(rs.getString("remark"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }public ArrayList<IncomeBean> findAllDateWise(String sDate, String eDate, int userid) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from incomes where transaction_date between '" + sDate + "' and '" + eDate + "' and userid='" + userid + "'";
        ArrayList<IncomeBean> al = new ArrayList<IncomeBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                IncomeBean ib = new IncomeBean();
                ib.setInc_id(rs.getInt("inc_id"));
                ib.setInc_ac(rs.getString("inc_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setInc_catid(rs.getString("inc_catid"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setReceiveby(rs.getString("reveiveby"));
                ib.setRemark(rs.getString("remark"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    public double findAmountById(String sdate, String edate,int userid) {
        conn = ConnectionPool.connectDB();
        double x = 0.0;
        String sql = "select sum(amount) as money from incomes where transaction_date between '" + sdate + "' and '" + edate + "' and userid='" + userid + "'";
        IncomeBean ib = new IncomeBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                x=rs.getDouble("money");
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    
    
    public static void main(String[] args) {
        IncomeDAO id = new IncomeDAO();
        IncomeBean ib = new IncomeBean();
        
        //findByInc_id()
//        x=id.findById(1);
//        System.out.println(x.getInc_id()+":"+x.getInc_ac()+":"+x.getUserid()+":"+x.getInc_catid()+":"+x.getAmount()+":"+x.getTransaction_date()+":"+x.getRecvby()+":"+x.getRemark());

        
//        findAll()
        ArrayList<IncomeBean> al = id.findAll();
        for(IncomeBean x:al){
            System.out.println(x.getInc_id()+":"+x.getInc_ac()+":"+x.getUserid()+":"+x.getInc_catid()+":"+x.getAmount()+":"+x.getTransaction_date()+":"+x.getReceiveby()+":"+x.getRemark());
        }
        //updateIncome
//        ib.setInc_id(1);
//        ib.setInc_ac("lmn");
//        ib.setUserid(101);
//        ib.setInc_catid(5);
//        ib.setAmount(10000);
//        ib.setTransaction_date("01-07-2023");
//        ib.setRecvby("abcd");
//        ib.setRemark("remark");
//        int r=id.updateIncome(ib,2);
        
        // delete income
//        int r = id.deleteIncome(1);
        
        // addIncome()
//        ib.setInc_id(0);
//        ib.setInc_ac("xyz");
//        ib.setUserid(101);
//        ib.setInc_catid(5);
//        ib.setAmount(10000);
//        ib.setTransaction_date("01-07-2023");
//        ib.setRecvby("abcd");
//        ib.setRemark("remark");
//        int r=id.addIncome(ib);
//        if(r>0){
//            System.out.println("true");
//        }
//        else System.out.println("false");
    }
}

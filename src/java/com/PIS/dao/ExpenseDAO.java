
package com.PIS.dao;

import com.PIS.bean.ExpensesBean;
import static com.PIS.dao.IncomeDAO.conn;
import com.PIS.utility.ConnectionPool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpenseDAO {
    //add income
    public int addExpens(ExpensesBean ib){
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "insert into expenses(exp_ac, userid, exp_catid, amount, transaction_date, payby, remark) values ('"+ib.getExp_ac()+"','"+ib.getUserid()+"','"+ib.getExp_catid()+"','"+ib.getAmount()+"','"+ib.getTransaction_date()+"','"+ib.getPayby()+"','"+ib.getRemark()+"')";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    //delete from bankbook()
    public int deleteExpense(int i){
        int r=0;
        conn = ConnectionPool.connectDB();
        String sql = "delete from expenses where exp_id='"+i+"'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    //updateExpense()
    public int updateExpense(ExpensesBean eb,int old){
        int r = 0;
        conn = ConnectionPool.connectDB();
        String str = "update expenses set exp_id='"+eb.getExp_id()+"',exp_ac='"+eb.getExp_ac()+"', userid='"+eb.getUserid()+"', exp_catid='"+eb.getExp_catid()+"',amount='"+eb.getAmount()+"',transaction_date='"+eb.getTransaction_date()+"',payby='"+eb.getPayby()+"',remark='"+eb.getRemark()+"' where exp_id='"+old+"'";
        Statement stmt;
        try {
            stmt = conn.createStatement();
            r = stmt.executeUpdate(str);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
//    findAll
    public ArrayList<ExpensesBean> findAll(){
        conn = ConnectionPool.connectDB();
        ArrayList<ExpensesBean> al = new ArrayList<ExpensesBean>();
        String sql = "select * from expenses";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                ExpensesBean ib = new ExpensesBean();
                ib.setExp_id(rs.getInt("exp_id"));
                ib.setExp_ac(rs.getString("exp_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setExp_catid(rs.getString("exp_catid"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setPayby(rs.getString("payby"));
                ib.setRemark(rs.getString("remark"));                
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
    //findById()
    public ExpensesBean findById(int id){
        conn = ConnectionPool.connectDB();
        String sql = "select * from expenses where exp_catid='"+id+"'";
        ExpensesBean ib = new ExpensesBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                ib.setExp_id(rs.getInt("exp_id"));
                ib.setExp_ac(rs.getString("exp_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setExp_catid(rs.getString("exp_catid"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setPayby(rs.getString("payby"));
                ib.setRemark(rs.getString("remark"));    
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpensesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }
    public ArrayList<ExpensesBean> findAllDateWise(String sDate, String eDate, int userid) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from expenses where transaction_date between '" + sDate + "' and '" + eDate + "' and userid='" + userid + "'";
        ArrayList<ExpensesBean> al = new ArrayList<ExpensesBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ExpensesBean ib = new ExpensesBean();
                ib.setExp_id(rs.getInt("exp_id"));
                ib.setExp_ac(rs.getString("exp_ac"));
                ib.setUserid(rs.getInt("userid"));
                ib.setExp_catid(rs.getString("exp_catid"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setPayby(rs.getString("payby"));
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
        String sql = "select sum(amount) as money from expenses where transaction_date between '" + sdate + "' and '" + edate + "' and userid='" + userid + "'";
        ExpensesBean ib = new ExpensesBean();
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
//        ExpensesBean bb = new ExpensesBean();
//        ExpenseDAO id = new ExpenseDAO();
//        ArrayList<ExpensesBean> al = id.findAll();
//        
//        for(ExpensesBean x:al){
//            System.out.println(x.getExp_id()+":"+x.getExp_ac()+":"+x.getUserid()+":"+x.getExp_catid()+":"+x.getAmount()+":"+x.getTransaction_date()+":"+x.getRemark());
//        }   
    }
}

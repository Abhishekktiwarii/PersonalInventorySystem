 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PIS.dao;

import com.PIS.bean.BankBookBean;
import com.PIS.bean.IncomeBean;
import static com.PIS.dao.IncomeDAO.conn;
import com.PIS.utility.ConnectionPool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankBookDAO {
    
    //add income
    public int addOnBankBook(BankBookBean ib){
        int r = 0;
        conn = ConnectionPool.connectDB();
        String sql = "insert into bank_book(account, transaction_date, amount, userid, operation) values ('"+ib.getAccount()+"','"+ib.getTransaction_date()+"','"+ib.getAmount()+"','"+ib.getUserid()+"','"+ib.getOperation()+"')";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BankBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    //delete from bankbook()
    public int deleteBank_book(int i){
        int r=0;
        conn = ConnectionPool.connectDB();
        String sql = "delete from bank_book where acid='"+i+"'";
        try {
            Statement stmt = conn.createStatement();
            r = stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BankBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    //update income()
    public int updateIncome(BankBookBean eb,int old){
        int r = 0;
        conn = ConnectionPool.connectDB();
        String str = "update bank_book set acid='"+eb.getAcid()+"',account='"+eb.getAccount()+"', transaction_date='"+eb.getTransaction_date()+"', amount='"+eb.getAmount()+"', userid='"+eb.getUserid()+"',operation='"+eb.getOperation()+"' where inc_id='"+old+"'";
        Statement stmt;
        try {
            stmt = conn.createStatement();
            r = stmt.executeUpdate(str);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BankBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
//    findAll
    public ArrayList<BankBookBean> findAll(){
        conn = ConnectionPool.connectDB();
        ArrayList<BankBookBean> al = new ArrayList<BankBookBean>();
        String sql = "select * from bank_book";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                BankBookBean ib = new BankBookBean();
                ib.setAcid(rs.getInt("acid"));
                ib.setAccount(rs.getString("account"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setAmount(rs.getInt("amount"));
                ib.setUserid(rs.getInt("userid"));
                ib.setOperation(rs.getString("operation"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankBookBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
    //findById()
    public BankBookBean findById(int id){
        conn = ConnectionPool.connectDB();
        String sql = "select * from incomes where inc_id='"+id+"'";
        BankBookBean ib = new BankBookBean();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                ib.setAcid(rs.getInt("acid"));
                ib.setAccount(rs.getString("account"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setAmount(rs.getInt("amount"));
                ib.setUserid(rs.getInt("userid"));
                ib.setOperation(rs.getString("operation"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankBookBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ib;
    }
    
    public ArrayList<BankBookBean> findAllDateWise(String sDate, String eDate, int userid) {
        conn = ConnectionPool.connectDB();
        String sql = "select * from bank_book where transaction_date between '" + sDate + "' and '" + eDate + "' and userid='" + userid + "'";
        ArrayList<BankBookBean> al = new ArrayList<BankBookBean>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BankBookBean ib = new BankBookBean();
                ib.setAcid(rs.getInt("acid"));
                ib.setAccount(rs.getString("account"));
                ib.setTransaction_date(rs.getString("transaction_date"));
                ib.setAmount(rs.getDouble("amount"));
                ib.setUserid(rs.getInt("userid"));
                ib.setOperation(rs.getString("operation"));
                al.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
public double closingBalance(String sDate,String eDate,int userid){
        double r = 0;
        conn=ConnectionPool.connectDB();
        String sql = "select (select sum(amount) as total_payment from bank_book b where transaction_date between '" + sDate + "' and '" + eDate + "' and userid='"+userid+"' and operation='Receive') - (select sum(amount) as total_receive from bank_book b where transaction_date between '" + sDate + "' and '" + eDate + "' and userid='"+userid+"' and operation='Pay')as closing_balance from dual;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet q=stmt.executeQuery(sql);
            if(q.next()){
                r=q.getDouble("closing_balance");
            }
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BankBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return r;
           
    }
    public static void main(String[] args) {
        
        BankBookBean bb = new BankBookBean();
        BankBookDAO id = new BankBookDAO();
        ArrayList<BankBookBean> al = id.findAll();
        
        for(BankBookBean x:al){
            System.out.println(x.getAcid()+":"+x.getAccount()+":"+x.getTransaction_date()+":");
        }
        
//        int r = id.deleteBank_book(7);
//        if(r>0){
//            System.out.println("True");
//        }
//        else System.out.println("false");
        
    }

        
}

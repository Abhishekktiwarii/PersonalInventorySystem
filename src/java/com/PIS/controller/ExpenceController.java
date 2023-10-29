/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PIS.controller;

import com.PIS.bean.BankBookBean;
import com.PIS.bean.CashBookBean;
import com.PIS.bean.ExpensesBean;
import com.PIS.dao.BankBookDAO;
import com.PIS.dao.CashBookDAO;
import com.PIS.dao.ExpenseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class ExpenceController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String expenses = request.getParameter("ex");
            String category = request.getParameter("cat");
            double amount = Double.parseDouble(request.getParameter("amount"));
            String recieve = request.getParameter("payby");
            String date = request.getParameter("date");
            String remark = request.getParameter("remark");
            int id = Integer.parseInt(request.getParameter("id"));
            String payby = request.getParameter("payby");
            
            ExpensesBean eb = new ExpensesBean();
            eb.setExp_ac(expenses);
            eb.setUserid(id);
            eb.setExp_catid(category);
            eb.setAmount(amount);
            eb.setTransaction_date(date);
            eb.setRemark(remark);
            eb.setPayby(payby);
            ExpenseDAO ed = new ExpenseDAO();
            
            int x = ed.addExpens(eb);
            
            if("Cash".equals(payby)){
            out.println("Cash Start");
                CashBookBean cb = new CashBookBean();
                cb.setAccount(category);
                cb.setTransaction_date(date);
                cb.setAmount(amount);
                cb.setUserid(id);
                cb.setOperation("Pay");
                CashBookDAO cd = new CashBookDAO();
                int k = cd.addOnBankBook(cb);
                
            out.println("Cash End");
            }
            
            else if("Online".equals(payby)){
                
                BankBookBean cb = new BankBookBean();
                cb.setAccount(category);
                cb.setTransaction_date(date);
                cb.setAmount(amount);
                cb.setUserid(id);
                cb.setOperation("Pay");
                BankBookDAO cd = new BankBookDAO();
                int k = cd.addOnBankBook(cb);
            }
            
            if(x>0){

                response.sendRedirect("expence");
            }
            else{
                out.println("error");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

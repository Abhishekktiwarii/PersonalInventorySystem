/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PIS.controller;

import com.PIS.bean.BankBookBean;
import com.PIS.bean.CashBookBean;
import com.PIS.bean.IncomeBean;
import com.PIS.dao.BankBookDAO;
import com.PIS.dao.CashBookDAO;
import com.PIS.dao.IncomeDAO;
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
public class IncomeController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             String expenses = request.getParameter("expenses");
            String category = request.getParameter("category");
            double amount = Double.parseDouble(request.getParameter("amount"));
            String date = request.getParameter("date");
            String remark = request.getParameter("remark");
            int id = Integer.parseInt(request.getParameter("id"));
            String payby = request.getParameter("payby");
            IncomeBean eb = new IncomeBean();
            
            eb.setInc_ac(expenses);
            eb.setUserid(id);
            eb.setInc_catid(category);
            eb.setAmount(amount);
            eb.setTransaction_date(date);
            eb.setRemark(remark);
            eb.setReceiveby(payby);
            IncomeDAO ed = new IncomeDAO();
            int x = ed.addIncome(eb);
            if("Cash".equals(payby)){
                CashBookBean cb = new CashBookBean();
                cb.setAccount(category);
                cb.setTransaction_date(date);
                cb.setAmount(amount);
                cb.setUserid(id);
                cb.setOperation("Receive");
                CashBookDAO cd = new CashBookDAO();
                int k = cd.addOnBankBook(cb);
            }
            else if("Online".equals(payby)){
                BankBookBean cb = new BankBookBean();
                cb.setAccount(category);
                cb.setTransaction_date(date);
                cb.setAmount(amount);
                cb.setUserid(id);
                cb.setOperation("Receive");
                BankBookDAO cd = new BankBookDAO();
                int k = cd.addOnBankBook(cb);
            }
            if(x>0){
                response.sendRedirect("Income");
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

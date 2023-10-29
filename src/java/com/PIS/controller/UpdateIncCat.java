/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PIS.controller;

import com.PIS.bean.IncomeCategouryBean;
import com.PIS.bean.UserBean;
import com.PIS.dao.IncomeCategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateIncCat extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession hs = request.getSession();
            UserBean ub = (UserBean)hs.getAttribute("user");
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateIncCat</title>");            
            out.println("</head>");
            out.println("<body>");
            
            IncomeCategouryBean eb = new IncomeCategouryBean();
            IncomeCategoryDAO ed = new IncomeCategoryDAO();
            String ename = request.getParameter("cn");
            String edetail = request.getParameter("cd");
            
            int id = ed.findId(ename, edetail);
            
            int userid = ub.getUserid();
            
//            out.println(ename);
//            out.println(edetail);
//            out.println(id);
//            out.println(userid);
//            
            
            eb.setUserid(userid);
            eb.setInc_catname(ename);
            eb.setInc_catdetails(edetail);
            eb.setInc_catid(id);
            
            
            int r = ed.updateExpense(eb, id);

            if(r>0){
                response.sendRedirect("IncomeCategory");
            }
            {
                out.println("Update data Failed");
            }

            
            out.println("</body>");
            out.println("</html>");
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

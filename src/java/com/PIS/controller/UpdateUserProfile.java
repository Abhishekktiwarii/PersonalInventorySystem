/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PIS.controller;

import com.PIS.bean.UserBean;
import com.PIS.dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class UpdateUserProfile extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateUserProfile</title>");            
            out.println("</head>");
            out.println("<body>");
            int userid = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String uname = request.getParameter("un");
            String mob =request.getParameter("mob");
            String pwd = request.getParameter("pwd");
            String Email = request.getParameter("email");
            String Address = request.getParameter("add");
            
            UserBean ub = new UserBean();
            UserDAO ud = new UserDAO();
            
            int id = ud.findId(uname, pwd);
            ub.setUserid(userid);
            ub.setName(name);
            ub.setUsername(uname);
            ub.setPassword(pwd);
            ub.setMobile(mob);
            ub.setEmail(Email);
            ub.setAddress(Address);
            
            int r = ud.updateEmp(ub,id);
            if(r>0){
                HttpSession hs = request.getSession();
                hs.setAttribute("user", ub);
                response.sendRedirect("showProfile");
            }
            else out.println("update Failed");
            
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

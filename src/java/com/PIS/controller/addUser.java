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

public class addUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String username = request.getParameter("un");
            String pwd = request.getParameter("pwd");
            String name = request.getParameter("name");
            String add = request.getParameter("add");
            String mob = request.getParameter("mob");
            String email = request.getParameter("email");
            
            UserBean ub = new UserBean();
            UserDAO ud = new UserDAO();
            
            ub.setUsername(username);
            ub.setPassword(pwd);
            ub.setName(name);
            ub.setMobile(mob);
            ub.setAddress(add);
            ub.setEmail(email);
            
            int x = ud.addUser(ub);
            UserBean b = ud.getUserByUsernameandPassword(ub.getUsername(), ub.getPassword());
            if(b!=null) {
                HttpSession hs = request.getSession();
                hs.setAttribute("user", b);
                response.sendRedirect("showProfile");
            }
            else out.println("User Added Failed");
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

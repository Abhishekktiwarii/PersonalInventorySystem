/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PIS.controller;

import com.PIS.bean.ExpenseCategoryBean;
import com.PIS.bean.UserBean;
import com.PIS.dao.ExpenseCategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class expence extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            HttpSession hs = request.getSession();
            UserBean ub = (UserBean)hs.getAttribute("user");

        out.println("<title>Servlet showProfile</title>");  
        out.println("<style>");
        out.println("* {margin: 0;padding: 0;}");

        out.println(".container {width: 100%;height: 100%;position: fixed;}");

        out.println(".b1 {width: 100%;height: 25vh;background-color: rgb(26, 25, 25);border-bottom: 0.5vw solid gray;}");

        out.println(".icon{width: 50vw;height: 100%;float: left;}");

        out.println("#b1a1 {color: rgb(15, 15, 153);font-size: 3.5vw;padding-top: 3vw;padding-left: 5vw;display: inline-block;}");

        out.println("#b1a2 {color: rgb(255, 255, 255);font-size: 3.5vw;padding-left: 3vw;display: inline-block;}");

        out.println("#span {color: gray;}");

        out.println(".menu {width: 45vw;height: 100%;float: right;}");

        out.println(".menu ul {list-style: none;width: 100%;display: flex;}");

        out.println(".menu ul li{color: white;font-size: 1.5vw;list-style: none;margin:4vw 3vw ;text-decoration: none;position: relative;transition: color 0.3s, border-bottom-color 0.3s;}");
        
        out.println(".menu ul li a{color: white;font-size: 1.5vw;text-decoration:none;}");

        out.println(".menu ul li::before{content: '';position: absolute;width: 100%;height: 2px;background-color: white;bottom: -2px;left: 0;transform: scaleX(0);transform-origin: left;transition: transform 0.3s;}");

        out.println(".menu ul li:hover::before{transform: scaleX(1);}");

        out.println(".sidemenu{text-decoration: none;color: rgb(86, 86, 236);font-weight: 600;}");

        out.println(".sidemenu:hover{color: white;}");

        out.println(".b2 {width: 100%;height: 75vh;background-color: rgb(13, 13, 94);}");
        
        out.println(".b21 {width: 20vw;height: 77%;background-color: rgb(26, 25, 25);float: left;margin-top: 1vw;margin-left: 3vw;border-top: 0.5vw solid gray;}");
//        out.println(".name{width: 2vw; padding-top: 1vw; color: white; padding-left: 3vw; font-size: 2vw; display: block;}");
        
        out.println(".b21 ul{list-style: none;padding-left: 2vw;}");
        
        out.println("#loginusername{padding-top: 1vw; color:white; padding-left: 3vw; font-size: 1vw;}");
            
        out.println(".b21 ul li{color: rgb(82, 82, 197);margin-top: 1.5vw;font-size: 1.5vw;font-weight: 600;}");
        
        out.println(".b22 {width: 70vw;height: 85%;float: right;margin-top: -0.5vw;margin-right: 3vw;}");
        out.println("td{padding: 0.4vw; color: white}");
        
        out.println("input {border-radius: 5px;font-size: 1.5vw;padding: 5px;}");
        out.println("#category,#payby {border-radius: 5px;font-size: 1.5vw;padding: 5px;}");
        out.println(".centr th,.centr  td,.centr table,.centr tr {border: 3px solid gray; border-collapse: collapse;}");
        out.println("</style>");
            
        out.println("<div class='container'>");
        out.println("<div class='b1'>");
        out.println("<div class='icon'>");
        out.println("<h2 id='b1a1'>PERSONAL</h2><br>");
        out.println("<h2 id='b1a2'>Inventory<span id='span'>System</span></h2>");
        out.println("</div>");
        out.println("<div class='menu'>");
        out.println("<ul>");
        out.println("<li><a href=\"HomeLogin\">Home</a></li>");
        out.println("<li><a href=\"showProfile\">Profile</a></li>");
        out.println("<li><a href=\"updateUser\">Update Profile</a></li>");
        out.println("<li><a href=\"Index.html\">Logout</a></li>");
        out.println("</ul>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='b2'>");
        String name = ub.getName();
        out.println("<div id='loginusername'><h1>Welcome "+name+"</h1></div>");
//    out.println("<p class='name'>"+name+"</p> ");
        out.println("<div class='b21'>");
        out.println("<ul>");
        out.println("<li> <a  class='sidemenu' href='ExpenseCat'> Expense Category</a></li>");
        out.println("<li> <a  class='sidemenu' href='IncomeCategory'> Income Category</a> </li>");
        out.println("<li> <a  class='sidemenu' href='expence'> Expenses</a></li>");
        out.println("<li> <a  class='sidemenu' href='Income'> Income</a></li>");
        out.println("<li> <a  class='sidemenu' href='CashBook'> Cash book</a></li>");
        out.println("<li> <a  class='sidemenu' href='BankBook'> Bank Book</a></li>");
        out.println("<li> <a  class='sidemenu' href='DayBook'> Day Book</a></li>");
        out.println("<li> <a  class='sidemenu' href='BalanceSheet'> Balance Sheet</a></li>");
        out.println("</ul>");
        out.println("</div>");
        out.println("<div class='b22'>");
        out.println("<fieldset  style='width: 60%; margin: auto; padding:2vw'>");
        out.println("<legend style=\"font-size: 3vw; font-weight:600\">Expense <span id='span'>Category </span></legend>");
        out.println("<form action='ExpenceController'>");
                
        ExpenseCategoryBean eb = new ExpenseCategoryBean();
        ExpenseCategoryDAO ed = new ExpenseCategoryDAO();
        int id = ub.getUserid();
        
        ArrayList<ExpenseCategoryBean> al = ed.findAll(id);
        out.println("<center><table style=\"font-size: 2vw;\">");
        out.println("<tr><td>Expenses</td><td> : </td><td><input type='text' name='ex' ></td></tr>");
        out.println("<td>Category</td><td> : </td>");
        out.println("<td><select name='cat' id='category'>");
        for(ExpenseCategoryBean r:al){
            String str = r.getExp_catname();
        out.println("<option value="+str+">"+str+"</option>");
        }
        out.println("</select></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println(" <td>Amount </td><td> : </td>");
        out.println(" <td><input type='text' name='amount'></td>");
        out.println("</tr>");
            out.println("<tr>");
            out.println(" <td>Pay By</td><td> : </td>");
            out.println(" <td><select name='payby' id='payby'><option value='Cash'>Cash</option><option value='Online'>Online</option></select></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Date</td><td> : </td>");
            out.println("<td><input type='date' name='date'></td>");
            out.println("</tr>");
            out.println(" <tr>");
            out.println("<td>Remark </td><td> : </td>");
            out.println("<td><input type='text' name='remark'></td>");
            out.println("</tr>");
            out.println(" <tr>");
            out.println("<td></td>");
            out.println("<td><input type='hidden' name='id' value=" + id + "></td>");
            out.println("</tr>");
            out.println(" <tr>");
            out.println("<td></td><td></td>");
            out.println("<td><input type='submit' value='Add Expenses'></td>");
            out.println("</tr>");
        
        
        out.println("</table></center>");
        out.println("</form>");
        out.println("</fieldset>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");

            
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

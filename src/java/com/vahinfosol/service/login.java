/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vahinfosol.service;

import com.vahinfosol.logic.login_logic;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author kiku
 */
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            login obj=new login();
            
            if(loginCheck(request, out))
            {
                String uname=request.getParameter("username");
                HttpSession S=request.getSession(true);
                S.setAttribute("uname", uname);
                
                
                JSONObject jobject = new JSONObject();
                jobject.put("user_name", uname);
                jobject.put("full_name", "Kamal Nath");
                jobject.put("Phone", "1234567890");
                jobject.put("email", "a@b.com");
                
                
                S.setAttribute("user_json", jobject.toJSONString());
                
                
                response.sendRedirect("index.html");
            }
            else{
                /*RequestDispatcher rd= request.getRequestDispatcher("/page_login.html");
                request.setAttribute("ivalid login", "enter correct password");*/
                out.println("invalid login");
                //rd.forward(request, response);
                
            }
        }
    }

    public boolean loginCheck(HttpServletRequest request, PrintWriter out) {

        String uname = request.getParameter("username");
        String password = request.getParameter("password");        

        login_logic obj = new login_logic();
        obj.SetOut(out);
        if(obj.LoginCh(uname, password))
        {
            return true;
        }
        else
        {
            return false;
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
        if (request.getParameter("hide").equals("login")) {
            processRequest(request, response);
        }
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

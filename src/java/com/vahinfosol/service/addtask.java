/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vahinfosol.service;

import com.vahinfosol.logic.addtask_logic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Intel
 */
public class addtask extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Servlet addtask</title>");
            out.println("</head>");
            out.println("<body>");
//            Enumeration<String> parameterNames = request.getParameterNames();
//            while (parameterNames.hasMoreElements()) {
//
//                String paramName = parameterNames.nextElement();
//                out.write(paramName);
//                out.write("n");
//
//                String[] paramValues = request.getParameterValues(paramName);
//                for (int i = 0; i < paramValues.length; i++) {
//                    String paramValue = paramValues[i];
//                    out.write("t" + paramValue);
//                    out.write("n");
//                }
//
//            }

            out.println(add_new_task(out, request));
     /*       if (*/    /*) {*/
//                response.sendRedirect("#ajax/page_task.html");
//            } else {
//                response.sendRedirect("#ajax/page_404.html");
//            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    public String add_new_task(PrintWriter out, HttpServletRequest request) {

        String task_name = request.getParameter("task_name");
        String task_type = request.getParameter("task_type");
        String frequency = request.getParameter("task_frequency");
        String target_date = request.getParameter("target_date");
        String deadlne = request.getParameter("deadlne");
        String task_details = request.getParameter("task_details");
        
//        out.println(target_date+deadlne)

        addtask_logic obj = new addtask_logic();
       return obj.DBEntry(task_name, task_type, frequency, target_date, deadlne, task_details);
            
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
        if (request.getParameter("hideCr").equals("Create")) {
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

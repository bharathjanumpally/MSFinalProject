/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bharath.servlets;

/**
 *
 * @author bharath
 */
//import com.bharath.dao.GoSolarhandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bharath.dao.LoginDao;
//import java.io.FileDescriptor;
//import java.io.FileInputStream;
import java.io.OutputStream;
//import java.nio.file.Files;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        response.setContentType("text/html");
       // PrintWriter out = response.getWriter();
        OutputStream outStream ;

        String userName = request.getParameter("username");
        String password = request.getParameter("userpass"); 
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("name", userName);
        }
        LoginDao ld = new LoginDao();
        
        if (ld.validate(userName, password)) {
                 
                
           RequestDispatcher rd = request.getRequestDispatcher("term.jsp");
            rd.forward(request, response);   
            
            
        } else {
            PrintWriter out = response.getWriter();
            out.print("<p style=\"color:red\">Sorry username or password error</p>");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request, response);
        }

        //out.close();
    }

}

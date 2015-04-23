/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bharath.servlets;

import com.bharath.dao.SelectTermDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bharath
 */
public class TermServlet extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String term = request.getParameter("term");
        SelectTermDao st = new SelectTermDao();
        List<String> courseNames = st.selectTerm(term);
        for (String courseName : courseNames) {
            System.out.println(courseName);
        }
        System.out.println("go for course page");
        request.setAttribute("crNames",courseNames);
        RequestDispatcher rd = request.getRequestDispatcher("course.jsp");
        rd.forward(request, response); 
        
        
       
    }

}

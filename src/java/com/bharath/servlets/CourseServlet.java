/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bharath.servlets;

import com.bharath.dao.SelectCourseDao;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bharath
 */
@WebServlet(name = "CourseServlet", urlPatterns = {"/CourseServlet"})
public class CourseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           OutputStream outStream ;     
        String crn = request.getParameter("course");
        System.out.println(crn);
        String crn1 = (crn.length() > 4)? crn.substring(crn.length() - 5): crn;
        System.out.println(crn1);
        SelectCourseDao sc = new SelectCourseDao();
        try
        {
              sc.getStudentList(crn1);
                response.setContentType("application/ms-excel");
                response.setContentLength(sc.outArray.length);
                response.setHeader("Expires:", "0"); // eliminates browser caching
                response.setHeader("Content-Disposition", "attachment; filename=Studentlist.xls");
                outStream = response.getOutputStream();
                outStream.write(sc.outArray);  
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    

}

<%-- 
    Document   : welcome
    Created on : Mar 29, 2015, 5:00:25 PM
    Author     : bharath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Term Page</title>
    </head>
    <body bgcolor = "#A9D0F5"><form action="TermServlet" method="post">  
        <fieldset style="width: 600px">  
              
            <table align="center">  
                <tr>  
                    <td>Enter Term(YYYYMM (MM 01-Spring, 05-Summer, 08-Fall))</td> <br></br> 
                    <td><input type="text" name="term"  /></td>  
                </tr>
                <tr>
                 <td><input type="submit" value="Submit" /></td>  
                </tr>  
            </table>  
        </fieldset>  
    </form>  
 
</body>
</html>


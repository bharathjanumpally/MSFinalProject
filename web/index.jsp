<%-- 
    Document   : index
    Created on : Mar 29, 2015, 4:58:35 PM
    Author     : bharath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title align="center">Welcome Page</title>
    </head>
    <body bgcolor = "#A9D0F5" margin-left: auto  margin-right: auto><form action="LoginServlet" method="post" >  
        <fieldset align = "center" style="width: 600px">  
            <legend> Login to App </legend>  
            <table align="center">  
                <tr>  
                    <td>User ID</td>  
                    <td><input type="text" name="username" /></td>  
                </tr>  
                <tr>  
                    <td>Password</td>  
                    <td><input type="password" name="userpass"  /></td>  
                </tr> 
                <tr>  
                    <td><input type="submit" value="Submit" /></td>  
                </tr>  
            </table>  
        </fieldset>  
    </form>  
 
</body>
</html>

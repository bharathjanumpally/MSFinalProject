<%-- 
    Document   : course
    Created on : Apr 9, 2015, 2:48:05 AM
    Author     : bharath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Course Page</title>
    </head>
    <body bgcolor = "#A9D0F5" ><form action="CourseServlet" method="get">  
          <fieldset style="width: 600px">  
             <table align="center">  
                <tr>
				<td>Select Course :</td>
				<td><select name="course">
                                       
					  <option value="NONE" label="--- Select ---" />
                                          <c:forEach items="${crNames}" var="crName"> 
					  <option value="${crName}"> ${crName} </option>
                                          </c:forEach>
				       </select>
                                </td>
				<td><form:errors path="crNames" cssClass="error" /></td>
			</tr>
                <tr>  
                    <td><input type="submit" value="Submit" /></td>  
                </tr> 
             </table>
        
          </fieldset>
        </form>
    </body>
</html>

<%--
	Document: aboutl.jsp
		Created On: Feb 28, 2016
	Authors: Deepak Rohan, Keval

 --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- title of the Page--%>
    <title>Researchers Exchange Participations</title>
        <%-- importing CSS stylesheet --%>
        <link rel="stylesheet" href="styles/main.css">
        <script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        
        <!-- BootStrap -->
        
        <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
        integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous"> -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script type="text/javascript">
        </script>
    </head>
    <body>
        <%-- Code to specify Header section of the page--%>
        <div id="header">
            <nav id="header_menu">
                <ul  class="left" >
              <li><a href="userControllerServlet?action=home">Researchers Exchange Participations</a></li>
                </ul>
                
                <%
                    String temp_obj=request.getParameter("adminname_jstl");
                     String temp_obj_user=request.getParameter("username_jstl");

                    try{
                    
                    
                    System.out.println("admin is from header...:::"+temp_obj);
                    
                   
                        System.out.println("user from headeris:::"+temp_obj_user);
                    
                    }catch(Exception e)
                    {
                        System.out.println("error in header.jsp:"+e);
                    }
                %>
                <ul class="right">
                    <c:if test="${sessionScope.theAdmin==null && sessionScope.theUser==null}">
                        <li><a href="userControllerServlet?action=about">About Us</a></li>
                        <li><a href="userControllerServlet?action=how">How it Works</a></li>
                        <li><a href="login.jsp">Login</a></li>
                        </c:if>
                        <c:if test="${sessionScope.theUser!=null}">
                        <li><a href="aboutl.jsp?user=${user.email }">About Us</a></li>
                        <li><a href="main.jsp?user=${user.email }">How it Works</a></li>
                        <li>${sessionScope.theUser.getName()}</li>
                         <li><a href="userControllerServlet?action=logout">Logout</a></li>
                        </c:if>
                        <c:if test="${sessionScope.theAdmin!=null}">
                        <li><a href="aboutl.jsp?user=${user.email }">About Us</a></li>
                        <li><a href="admin.jsp?user=${user.email }">How it Works</a></li>
                        <li>${sessionScope.theAdmin.getName()}</li>
                         <li><a href="userControllerServlet?action=logout">Logout</a></li>

                        </c:if>
                </ul>

            </nav>



        </div>


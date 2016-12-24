<%--
	Document: aboutl.jsp
	Created On: Feb 28, 2016
	Authors: Deepak Rohan, Keval

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%
   response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);
try{
    if(session.getAttribute("theUser")==null )
    {
        request.setAttribute("msg", "Session has ended.  Please login.");
       RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
       
     }
    else if(session.getAttribute("theAdmin")==null)
    {
        request.setAttribute("msg", "Session has ended.  Please login.");
       RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
    }
    else
    {
        System.out.println("user logged in is::"+session.getAttribute("theUser").toString());
    }
//User username_session_tester=(User)session.getAttribute("theUser");
//User admin_session_tester=(User)session.getAttribute("theAdmin");

//System.out.println("username and admin is:::for session checker:::"+username_session_tester.getName()+":::and::"+admin_session_tester.getName());

}
catch(NullPointerException e)
{
    System.out.println(e);
}
%>

<%-- Code to go back to Main page  --%>
<br>
<a href="StudyController?user=${user.email }" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section tag is used to display Message Sent   --%>
<section>
    <h3 class="text-center">Message Sent. . .</h3>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
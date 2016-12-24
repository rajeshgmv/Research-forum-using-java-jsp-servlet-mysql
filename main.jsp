<%--
	Document: aboutl.jsp
		Created On: Feb 28, 2016
	Authors: Deepak Rohan, Keval
 --%>
<%-- Include tag is used to import header page --%>


<%@ include file="header.jsp" %>
<%-- Code to display items in List --%>
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


<nav id="menu">
    <c:if test="${sessionScope.theUser!=null }">
    <ul>
        <li>Coins (<span class="count">${sessionScope.theUser.getNumCoins()}</span>) </li>
        <li>Participants (<span class="count">${participantCount}</span>) </li>
        <li>Participation (<span class="count"> ${sessionScope.theUser.getNumParticipation()}</span>) </li>
        <li><br></li>
        <li><a href="userControllerServlet?action=main">Home</a></li>
        <li><a href="StudyController?user=${sessionScope.theUser.getEmail()}&amp;action=participate">Participate</a></li>
        <li><a href="StudyController?user=${user.email}&amp;action=studies">My Studies</a></li>
        <li><a href="recommend.jsp?user=${user.email}">Recommend</a></li>
        <li><a href="contact.jsp?user=${user.email}">Contact</a></li>
    </ul>
</c:if>
    <c:if test="${sessionScope.theAdmin!=null }">
		<li><a href="home.jsp?user=${user.email }">Home</a></li>
        <li><a href="reportques.jsp?user=${user.email }">Reported Questions</a></li>
</c:if>
</nav>
<%-- Section tag is used to write description  --%>
<section class="main">
    <h3>How it Works</h3>
    <p>This site was built to help researchers conduct their user studies</p>
    <p>1 participation = 1 coin</p>
    <p><b>To participate,</b> go to "Participate" section and choose a study to complete</p>
    <p><b>To get participants,</b> submit your study here to start getting Participations. Inorder to do so, you must have enough coins in Your account</p>

</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
<%--
	Document: aboutl.jsp
		Created On: Feb 28, 2016
	Authors: Deepak Rohan, Keval
 --%>
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

<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display items in List --%>
<nav id="menu">
    <ul>
        <li>Coins (<span class="count">${sessionScope.theUser.getNumCoins()}</span>) </li>
        <li>Participants (<span class="count">${participantCount}</span>) </li>
        <li>Participation (<span class="count">${sessionScope.theUser.getNumParticipation()}</span>) </li>
        <li><br></li>
        <li><a href="home.jsp?user=Hello,Kim">Home</a></li>
        <li><a href="StudyController?user=${user.email}&amp;action=participate">Participate</a></li>
        <li><a href="StudyController?user=${user.email}&amp;action=studies">My Studies</a></li>
        <li><a href="recommend.jsp?user=${user.email}">Recommend</a></li>
        <li><a href="contact.jsp?user=${user.email}">Contact</a></li>
    </ul>
</nav>
<%-- Code to Display Question--%>
<section class="question_section">
    <h3><span class="label label-default" >Question</span></h3>
    <%-- Img tag to display image--%>
    <img src="images/small_tree.jpg" class="img-responsive" height="250" width="250" alt="Tree"/>

<%--Code to rating the Question --%>
    <p class="text-left">${study.question}</p>
   

        <form action="StudyController?user=${user.email}&action=answer&amp;StudyCode=${study.studyCode}&amp;survey=yes" method="post">
        <c:forEach var="answer" items="${study.answer}">
            <div class="radio">
            <input type="radio" name="number" value="${answer}" required> ${answer}
            </div>
            </c:forEach>
        
  
    
<%-- Code to submit the Rating  --%>
    
         <div class="form-group">
        <div class="col-sm-offset-3 col-sm-4">
        <button type="submit"  class="btn btn-primary">Submit</button>
         </div>
            </div>
            <br/><br/><br/>   
        </form>
        
    
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
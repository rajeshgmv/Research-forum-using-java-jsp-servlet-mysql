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

<%-- Code to display items in List --%>
<nav id="menu">
    <ul>
        <li>Coins (<span class="count">${sessionScope.theUser.getNumCoins()}</span>) </li>
        <li>Participants (<span class="count">${participantCount}</span>) </li>
        <li>Participation (<span class="count">${sessionScope.theUser.getNumParticipation()}</span>) </li>
        <li><br></li>
        <li><a href="StudyController?user=${user.email}">Home</a></li>
        <li><a href="StudyController?user=${user.email}&amp;action=participate">Participate</a></li>
        <li><a href="StudyController?user=${user.email}&amp;action=studies">My Studies</a></li>
        <li><a href="recommend.jsp?user=${user.email}">Recommend</a></li>
        <li><a href="contact.jsp?user=${user.email}">Contact</a></li>
    </ul>

</nav>
<%-- Section to display studies and participate in that study--%>
<div>
   
     <h3 class="text-left"><span class="label label-default " >Studies</span>
     <span ><a class="label label-default" href="StudyController?user=${user.getEmail()}&action=report">Report History</a></span></h3>
     </div>
     
    <%-- Display the studies in the table --%>
    <%-- Clicking on Participate button displays Question.jsp page where 
            you can rate the question--%>
     <div class="table-responsive">
    <table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Study Name</th>
            <th>Image</th>      
            <th>Question</th>
            <th>Action</th>
            <th>Report</th>
        </tr>
        <c:forEach var="study" items="${studies}">
        <tr>
            <%-- First study details --%>
            <td>${study.studyName }</td>
            <td><img src="images/small_tree.jpg" alt="Tree"></td>
            <td>${study.question }</td>
            <td><form action="StudyController?user=${user.getEmail()}&amp;action=participate&amp;StudyCode=${study.studyCode}&questionId=${study.questionID}" method="post"><input type="submit" class="participate_button"
                                                                                value="participate" /></form></td>
            <td><form action="StudyController?user=${user.getEmail()}&amp;action=report&amp;StudyCode=${study.studyCode}&questionId=${study.questionID}" method="post"><input type="submit" class="participate_button"
                                                                                value="report" /></form></td>

        </tr>
        </c:forEach>
        
    </table>
    </div>


<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
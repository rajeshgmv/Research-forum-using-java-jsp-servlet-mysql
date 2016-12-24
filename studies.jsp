<%--
	Document: aboutl.jsp
		Created On: Feb 28, 2016
	Authors: Deepak Rohan, Keval

 --%>
<%@page import="business.Study"%>
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

<%-- Code to display Page Name --%>
<h3 id="page_name">My Studies</h3>
 <%-- Code to add new study   --%>
<h3 id="add_new_study"><a href="newstudy.jsp?user=${user.email }" >Add a new study</a></h3>
 <%-- Code to go Back to the Main Page  --%>
<a href="main.jsp?user=${user.email }" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to display studies details --%> 
<%-- Clicking on Start, Stop to Participate in that study and  Edit button to display edit page and edit study details in it--%>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.5";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<section >

<div class="table-responsive">
    <table class="table" >
        <tr>
            <th>Study Name</th>
            <th>Requested Participants</th>     
            <th>Participations</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        
        <c:forEach var="study" items="${studies}">
        <tr>
            <%-- First study details --%>
            <td>${study.studyName}</td>
            <td>${study.requestedParticipants}</td>
            <td>${study.numOfParitipants}</td>
            <c:choose>
            <c:when test="${study.status == 'start' }">
            <td><form action="StudyController?action=stop&amp;StudyCode=${study.studyCode}" method="post">
            <button type="Submit" class="btn btn-primary">Stop</button></form></td>
            </c:when>
            <c:otherwise>
               <td><form action="StudyController?action=start&amp;StudyCode=${study.studyCode}" method="post">
            <button type="Submit" class="btn btn-primary">Start</button></form></td>
            </c:otherwise>
            </c:choose>
            <%-- Code to display edit page --%>
            <td><form action="StudyController?user=${user.email}&amp;action=edit&StudyCode=${study.studyCode}" method="post">
                    <button type="submit" class="btn btn-primary">Edit</button></form></td>
            
            <td><% Study study=new Study();
            String scode=study.getStudyCode(); 
            String studyName = study.getStudyName();
            String description = study.getDescription();
            
            String str="http://localhost:8080/vani/test.jsp";
            %>                       
            <div class="fb-share-button" data-href="<%=str%>" data-layout="button"></div></td>

        </tr>
        </c:forEach>
 
    </table>
</div>
</section>
<%-- Include tag is used to import footer page --%>
<%-- %@ include file="footer.jsp" --%>
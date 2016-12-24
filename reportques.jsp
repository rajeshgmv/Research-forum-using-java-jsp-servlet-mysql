<%--
	Document: aboutl.jsp
		Created On: Feb 28, 2016
	Authors: Deepak Rohan, Keval

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to go back to Main page  --%>
<br>
<h3><span id="studies">Reported Questions</span></h3><br/>
<a href="admin.jsp?user=Hello,Admin" id="back_to_page">&laquo;Back to the Main Page</a><br/>
<br/><br/><br/>


<!-- TODO: Add more code to get the table here.
  -->
  <div class="table-responsive">
  <table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Question</th>
            <th>Action</th>	
            <th>Reported By</th>		
        </tr>
        <c:forEach var="report" items="${reports }">
        <tr>
            <%-- First study details --%>
            <td>${report.question }</td>
            <td>
            <form action="StudyController" method="post">
            <input type="submit" class="btn btn-primary" formaction="StudyController?user=${user.email }&amp;StudyCode=${report.studyCode }&amp;action=approve&amp;reportee=${report.userName }&questionID=${report.questionID}"  value="Approve">
            <input type="submit" class="btn btn-primary" formaction="StudyController?user=${user.email }&amp;StudyCode=${report.studyCode }&amp;action=disapprove&amp;reportee=${report.userName }&questionID=${report.questionID}"  value="Dispprove">
            </form>
           </td>
           <td>${report.userName }</td>
        </tr>
        </c:forEach>
        <!-- TODO Add one more for removal and not re -->
        
       
        </table>
        </div>
  
  
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
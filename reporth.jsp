<%-- 
    Document   : reporth
   	Created On: Feb 28, 2016
	Authors: Deepak Rohan, Keval
--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to go back to Main page  --%>
<br>
<a href="StudyController?user=${user.email }" id="back_to_page">&laquo;Back to the Main Page</a>
<br>
 <div class="table-responsive">
     <% if(request.getAttribute("reports")!=null){ %>   
<table class="table" >
        <%--Column Names --%>
        
        <tr>
            <th>Report Date</th>
            <th>Report Question</th>		
            <th>Report Status</th>
            
        </tr>
          <c:forEach var="report" items="${reports}">
        <tr>
            <%-- First study details --%>
            <td>${report.dateReported }</td>
            <td>${report.question }</td>
            <td>${report.status }</td>
            

        </tr>
        </c:forEach>
    </table>
        <%}else{ %>
        <div style="margin:0px auto;text-align:center; "> No Data Found</div>
        <%}%>
 </div>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
<%-- 
    Document   : requestc
   	Created On: Feb 28, 2016
	Authors: Deepak Rohan, Keval
--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to go back to Main page  --%>
<br>
<a href="admin.jsp?user=Hello,Admin" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section tag is used to display Message Sent   --%>
<br/>
<br/>
<br/>
    <h3 class="text-center">Question ${param.status} ..</h3>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>

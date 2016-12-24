<%--
	Document: aboutl.jsp
	Created On: Feb 28, 2016
	Authors: Deepak Rohan, Keval

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to go back to Main page  --%>
<br>
<a href="StudyController?user=${user.email }" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section tag is used to display Recommendation Sent   --%>
<section>
    <h3 class="text-center">Question Reported. . .</h3>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
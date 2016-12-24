<%@ include file="header.jsp" %>
<h3 id="page_name">Forgot Password</h3>

<a href="userControllerServlet?action=main" id="back_to_page">&laquo;Back to the Main Page</a>

<section id="contact_form">
    <form action="userControllerServlet" method="post">
        <input type="hidden" name="action" value="forgotpassword" />
       
        <label>Email *</label>
        <input type="email" name="email" required/><br><br>
        
        <button type="submit"  id="submit_button">Submit</button>

    </form>
    
    <p>${msg}</p>

</section>






<%@ include file="footer.jsp" %>
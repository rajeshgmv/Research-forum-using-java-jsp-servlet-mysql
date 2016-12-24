<%@ include file="header.jsp" %>

<h3 id="page_name">Change Password</h3>

<section id="contact_form">
    <form action="userControllerServlet" method="post">
        <input type="hidden" name="action" value="changepassword" />
        <input type="hidden" name="token" value="<c:out value='${token}' />" />

        <label>Email *</label>
        <input type="email" name="email" value="<c:out value='${email}' />" readonly/><br><br>
        <label>Password *</label>
        <input type="password" name="password" required/> <br><br>
        <label>Confirm Password *</label>
        <input type="password" name="confirm_password" required /> <br>
        <button type="submit"  id="submit_button">Submit</button>

    </form>

    <p>${msg}</p>

</section>






<%@ include file="footer.jsp" %>
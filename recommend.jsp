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
<%-- Code to display Page Name --%>
<h3 id="page_name">Recommend to a friend</h3>
<%-- Code to display Page Name --%>
<a href="StudyController?user=${user.email }" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to input Contact details and Send Message --%>
<section>
    <form class="form-horizontal" action="StudyController?user=${user.email }&&action=confirmr" method="post">
    
    <div class="form-group">
        <label class="col-sm-4 control-label">Name *</label>
        <div class="col-sm-4">
        <input type="text" class="form-control" name="study_name" required />
        </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label">Email *</label>
        <div class="col-sm-4">
        <input type="email" class="form-control" name="email" required/>
        </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label">Friend's Email *</label>
        <div class="col-sm-4">
        <input type="email" class="form-control" name="friend_email" required/>
        </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label">Message *</label>
         <div class="col-sm-4"> 
        <textarea name="message" class="form-control" required></textarea>
        </div>
            </div>
        
        <div class="form-group">
        <div class="col-sm-offset-5 col-sm-4">
        <button type="submit"  class="btn btn-primary">Submit</button>
		</div>
            </div>
            
    </form>
</section>

<p class="text-center">When your friend logs in and participates in one user study, you'll get 2 coins bonus</p>
<br><br><br><br>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
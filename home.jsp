<%--
	Document: aboutl.jsp
		Created On: Feb 28, 2016
	Authors: Deepak Rohan, Keval

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>

<section id="home_page">
    <%-- Img tag is used to import image --%>
   
    
    <img src="images/home_image.png" class="img-responsive center-block" alt="Responsive image" />
</section>
    <%
    
        
    boolean first_check=false;
    if(first_check==false)
    {
    String host=request.getRemoteHost();
    int port=request.getRemotePort();
    /*  System.out.println("printing from home.jsp:::for cookie purpose:::");
    System.out.println("remote host is::"+host+":::and port is:::"+port);
 */
    //creating cookie...
    Cookie cookie_host=new Cookie("host_name", host);
    Cookie cookie_port=new Cookie("host_port", port+"");

    //setting expiry date::24 hours after setup...
    cookie_host.setMaxAge(60*60*24); 
    cookie_port.setMaxAge(60*60*24);
    
    //adding both cookies into response header..
    response.addCookie(cookie_host);
    response.addCookie(cookie_port);
    
    //setting boolean value to true...so that it doest not run again for the same user...
    first_check=true;
    }

    %>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
<%--
	Document: aboutl.jsp
		Created On: Feb 28, 2016
	Authors: Deepak Rohan, Keval
 --%>
<%-- Section to display description --%>
<section class="copyright">
    &copy; Researchers Exchange Participations
    <%
     try{   
       Cookie[] cookies=null;
        Cookie ck=null;
        cookies=request.getCookies();
        String hostName="";
        String portNumber="";
     //   System.out.println("total entries in cookies array::"+cookies.length);
        for(int i=1;i<cookies.length;i++)
        {
            ck=cookies[i];
            //System.out.println("from footer::::cookie name is::"+ck.getName()+"::and value is:::"+ck.getValue());
            
            if (ck.getName().equals("host_name")){
            	hostName = ck.getValue();
            }else if(ck.getName().equals("host_port")){
            	portNumber = ck.getValue();
            }
            
            //ck=cookies[1];
            //System.out.println("from footer:::cookie name is::"+ck.getName()+"and value is:::"+ck.getValue());   
            
        }%>
        <%="hostname: " +hostName+" port number: "+portNumber  %>
    <%  }catch(NullPointerException npe){
    	 npe.printStackTrace();
     }%>
</section>
</body>
</html>


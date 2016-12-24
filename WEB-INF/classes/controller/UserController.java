package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import business.*;
import data.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import mail.MailClass;
/**
 * 
 * @author mohan
 */
public class UserController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		String message="";
		String trial="";
		String url = "/login.jsp";
		
		String action = request.getParameter("action");
		HttpSession session=request.getSession();
		if (action == null) {
			action = "join";  
			url="/home.jsp";
		}
		if (action.equals("main")) {
			
			User check_usr_obj_main=(User)session.getAttribute("theUser");
			if(check_usr_obj_main==null)
			{
                            url="/login.jsp";
			}
			else
			{
                            url="/main.jsp";
                            request.setAttribute("adminname_jstl",check_usr_obj_main.getEmail());
                        }
		} 
		else if(action.equals("login") || action.equals("loginadmin"))
		{
			String username=request.getParameter("email");
			String password=request.getParameter("password");
			String salt = null;
                        User user = null;
                        
                        salt = UserDB.getSalt(username);

                        if (salt != null && !salt.isEmpty()) {
                            String pass = hashPassword(password, salt);
                            user = UserDB.validateUser(username, pass);
                        }
                        
                        //user = UserDB.selectUser(username);
			if(user != null){
                            System.out.println("vani123");
                            System.out.println(action);
                            System.out.println(user.getType());
                                  	if(user.getType().equals("admin") && action.equals("loginadmin")){
						url="/admin.jsp";
				
						session.setAttribute("theAdmin", user);
						String adminName=user.getEmail();
						request.setAttribute("adminname_jstl","Hi "+user.getEmail());
				
						session.setAttribute("theAdmin", user);
					}else if(action.equals("login")&&user.getType().equals("user"))
					{
                                            System.out.println("vani1234");
						user.setEmail(username);
						request.setAttribute("username_jstl","Hi "+user.getEmail());

                                        	session.setAttribute("theUser", user);
						String participations = StudyDB.getTotalParticipantsCount(user.getEmail())+"";
						request.setAttribute("participantCount", participations);
						session.setAttribute("participantCount", participations);
						url="/main.jsp";
					}else if(user.getType().equals("admin") && action.equals("login")){
						message="Click on the appropriate login button";
						url="/login.jsp";
						request.setAttribute("msg", message);
						request.setAttribute(trial,"try");
					}else if(user.getType().equals("user") && action.equals("loginadmin")){
						message="Click on the appropriate login button";
						url="/login.jsp";
						request.setAttribute("msg", message);
						request.setAttribute(trial,"try");
					}
                        }else{
				message="wrong username password";
				url="/login.jsp";
				request.setAttribute("msg", message);
				System.out.println("username password pair does not exist..");

			}
		}
		else if(action.equals("create"))
		{
			
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			String confirm_passowrd=request.getParameter("confirm_password");

                        boolean validPassword = false;
                        String salt = "";
                        String hashAndSalt = "";
                        User user = null;
                        
                        if (name != null && email != null && password != null && confirm_passowrd != null) {
                        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()
                                && !confirm_passowrd.isEmpty()) {

                            if (password.equals(confirm_passowrd)) {
                                try {
                                    System.out.println(password);
                                    PasswordUtil.checkPasswordStrength(password);
                                    message = "";
                                    validPassword = true;
                                    salt = PasswordUtil.getSalt();
                                    hashAndSalt = hashPassword(password, salt);

                                } catch (Exception e) {
                                    System.out.println("why not");
                                    message = e.getMessage();
                                    System.out.println(message);
                                    request.setAttribute("msg", message);
                                    url = "/signup.jsp";
                                }
                                if (validPassword) {
                                    user = UserDB.selectUser(email);
                                    if (user == null) {
                                        UUID uuid = UUID.randomUUID();
                                        Date todaysDate = new Date();
                                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                        String dateCreated = df.format(todaysDate) ;

                                        TempUserDB.addTempUser(name, email, hashAndSalt, dateCreated,
                                                uuid.toString(), salt);
                                        String emailMessage = "Please click the below link to activate your account" + "\n"
                                                +"http://jonna-srivani.rhcloud.com/vani/userControllerServlet?action=activate&token=" + uuid.toString();
                                                //+ "http://localhost:8080/vani/userControllerServlet?action=activate&token=" + uuid.toString();
                                        MailClass.sendMail(emailMessage, "Researchers Exchange", "Activate Account", email);
                                        message = "Account created. Please activate your account by clicking on your email link";
                                        request.setAttribute("msg", message);
                                        url = "/login.jsp";

                                    } else {
                                        message = "Email already exists";
                                        request.setAttribute("msg", message);
                                        url = "/signup.jsp";
                                    }
                                }
                            } else {
                                message = "Passwords do not match";
                                request.setAttribute("msg", message);
                                url = "/signup.jsp";
                            }

                        } else {
                            message = "Please complete all the details";
                            request.setAttribute("msg", message);
                            url = "/signup.jsp";
                        }
                    }
		}
                else if ("recommendafriend".equals(action)) {
                    System.out.println("In recommend");
                    String emailid = request.getParameter("useremail");
                    System.out.println("Hey "+emailid);
                    session.setAttribute("recommendfriend", emailid);
                    url = "/login.jsp";
                }
                else if(action.equals("activate"))
                {
                    String token = request.getParameter("token");
                    String tempUser = "";
                    User user = null;
                    if (token != null && !token.isEmpty()) {
                        tempUser = TempUserDB.getUser(token);
                    }

                    if (tempUser != null && !tempUser.isEmpty()) {
                        user = new User(tempUser.split("::")[0], tempUser.split("::")[1], 0, 0, 0);
                        System.out.println(tempUser.split("::")[2]);
                        System.out.println(tempUser.split("::")[3]);
                        UserDB.insert(user, tempUser.split("::")[2], tempUser.split("::")[3]);
                        TempUserDB.deleteUser(token);
                        String recemail = (String) session.getAttribute("recommendfriend");
                                        if (recemail != null) {
                                            UserDB.addbonuscoins(recemail);
                                            session.removeAttribute("recommendfriend");
                                        }
                        session.setAttribute("theUser", user);
                        url = "/main.jsp";
                    } else {
                        message = "Invalid activation, please sign-up again";
                        request.setAttribute("msg", message);
                        url = "/signup.jsp";
                    }
                }
		else if(action.equals("how"))
		{
			User check_usr_obj=(User)session.getAttribute("theUser");
			if(check_usr_obj!=null)
			{
                            url="/main.jsp";
			}
			else
			{
                            url="/how.jsp";
			}
                }
		else if(action.equals("about"))
		{
			User check_usr_obj=(User)session.getAttribute("theUser");

			if(check_usr_obj==null)
			{
                            url="/aboutl.jsp";
			}
			else
			{
                            url="/about.jsp";
			}
		}
		else if(action.equals("home"))
		{
			User check_usr_obj_home=(User)session.getAttribute("theUser");
			if(check_usr_obj_home==null)
			{
                            url="/home.jsp";
			}
			else
			{
                            url="/main.jsp";
			}
		}
                else if(action.equals("forgotpassword"))
		{
                    String email = request.getParameter("email");
                    if (email != null && !email.isEmpty()) {
                        UUID uuid = UUID.randomUUID();
                        Date todaysDate = new Date();
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String dateCreated = df.format(todaysDate) ;
                        
                        ForgotPasswordDB.addTempUser(email, uuid.toString(),dateCreated + (60000 * 5));
                        String emailMessage = "Please click the below link to activate your account" + "\n"
                                +"http://jonna-srivani.rhcloud.com/vani/userControllerServlet?action=changep&token=" + uuid.toString();
                                //+ "http://localhost:8080/vani/userControllerServlet?action=changep&token=" + uuid.toString();
                        MailClass.sendMail(emailMessage, "Researchers Exchange", "Change Password", email);
                        message = "Email Sent. Please click your email link to change password";
                        request.setAttribute("msg", message);
                        url = "/login.jsp";
                    } else {
                        message = "Please enter a password";
                        request.setAttribute("msg", message);
                        url = "/forgotpassword.jsp";
                    }
                }
                else if(action.equals("changep"))
		{
                    String token = request.getParameter("token");
                    String getTokenDetails = ForgotPasswordDB.getUser(token);
                    String expirationTime = null;

                    if (getTokenDetails != null && !getTokenDetails.isEmpty()) {
                        System.out.println("vani1234");
                        //String expirationTimestamp = Timestamp.valueOf(getTokenDetails.split("::")[2]);
                        expirationTime = getTokenDetails.split("::")[2];
                        System.out.println(expirationTime);
                    }

                    Date todaysDate = new Date();
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String currentTime = df.format(todaysDate) ;
                   

                    boolean compare1 = false;
                    int compare = expirationTime.compareTo(currentTime);
                    if(compare > 0)
                    {
                        System.out.println("vani1234");
                        compare1 = true;
                    }
                    if (compare1 && getTokenDetails != null && !getTokenDetails.isEmpty()) {

                        request.setAttribute("email", getTokenDetails.split("::")[0]);
                        request.setAttribute("token", getTokenDetails.split("::")[1]);
                        url = "/changepassword.jsp";
                    } else {

                        message = "Token expired";
                        request.setAttribute("msg", message);

                        url = "/login.jsp";
                    }
		}
                else if(action.equals("changepassword"))
		{
                    boolean validPassword = false;
                    String email = request.getParameter("email");
                    String token = request.getParameter("token");

                    String salt = null;
                    String hashAndSalt = null;
                    String password = request.getParameter("password");
                    String confirmPassword = request.getParameter("confirm_password");
                    String getTokenDetails = ForgotPasswordDB.getUser(token);

                    String expirationTime = getTokenDetails.split("::")[2];
                    Date todaysDate = new Date();
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String currentTime = df.format(todaysDate) ;
                    
                    try {
                        PasswordUtil.checkPasswordStrength(password);
                        message = "";
                        validPassword = true;
                        salt = PasswordUtil.getSalt();
                        hashAndSalt = hashPassword(password, salt);

                    } catch (Exception e) {
                        message = e.getMessage();
                        request.setAttribute("msg", message);
                        request.setAttribute("email", email);
                        request.setAttribute("token", token);
                        url = "/changepassword.jsp";
                    }

                    boolean compare1 = false;
                    int compare = expirationTime.compareTo(currentTime);
                    if(compare > 0)
                    {
                        compare1 = true;
                    }
                    if (validPassword) {
                        if (email != null && !email.isEmpty() && !password.isEmpty()
                                && !confirmPassword.isEmpty()) {

                            if (password.equals(confirmPassword)) {

                                if (compare1) {

                                    ForgotPasswordDB.deleteUser(token);
                                    int count = UserDB.updatePassword(email, hashAndSalt, salt);

                                    if (count > 0) {
                                        String emailMessage = "Your password has been changed";

                                        MailClass.sendMail(emailMessage, "Researchers Exchange", "Change Password", email);
                                        message = "Password Changed. Please login now";
                                        request.setAttribute("msg", message);
                                        url = "/login.jsp";
                                    } else {
                                        message = "Password change failed";
                                        request.setAttribute("msg", message);
                                        url = "/login.jsp";
                                    }
                                } else {
                                    message = "Token expired";
                                    request.setAttribute("msg", message);
                                    url = "/login.jsp";
                                }
                            } else {
                                message = "Passwords do not match";
                                request.setAttribute("email", email);
                                request.setAttribute("token", token);
                                request.setAttribute("msg", message);
                                url = "/changepassword.jsp";
                            }

                        } else {
                            message = "Please enter valid details";
                            request.setAttribute("email", email);
                            request.setAttribute("token", token);
                            request.setAttribute("msg", message);
                            url = "/changepassword.jsp";
                        }
                    }
		}
		else if(action.equals("logout"))
		{
			User logout_user=(User)session.getAttribute("theUser");
			User logout_admin=(User)session.getAttribute("theAdmin");

			if(logout_user!=null)
			{
				session.removeAttribute("theUser");
				session.invalidate();
			}
			else if(logout_admin!=null)
			{
				session.removeAttribute("theAdmin");
				session.invalidate();
			}
			else
			{
				System.out.println("empty session...");
			}
			url="/home.jsp";
		}


		getServletContext()
		.getRequestDispatcher(url)
		.forward(request, response);
	}   


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {

		doPost(request, response);
	}
        String hashPassword(String password, String salt) {

        String saltedAndHashedPassword;

        try {

            saltedAndHashedPassword = PasswordUtil.hashAndSaltPassword(password, salt);

            return saltedAndHashedPassword;

        } catch (NoSuchAlgorithmException ex) {
            log(ex.getMessage());
        }
        return null;
    }
}
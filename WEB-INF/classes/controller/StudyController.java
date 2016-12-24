package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.Answer;
import business.Question;
import business.Reports;
import business.Study;
import business.User;
import data.AnswerDB;
import data.ReportDB;
import data.StudyDB;
import javax.servlet.http.Part;
import mail.MailClass;

/**
 * 
 * @author mohan
 */
@WebServlet("/StudyController")
public class StudyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = (HttpSession) request.getSession();
                
                String imagesPath = getServletContext().getRealPath("/images");

		User user1 = (User) session.getAttribute("theUser");
		if(user1 == null){
			user1 = (User) session.getAttribute("theAdmin");
		}

		request.getSession().setAttribute("user", user1);
		//		HttpSession sesion
		session.setAttribute("test", "test");
		String url = "";
		String action = request.getParameter("action");
		//Checking to see for the user
		//ReportDB.populateData();
		//ReportDB.getAllReports();
		User user = (User) request.getSession().getAttribute("user");
		String participations = StudyDB.getTotalParticipantsCount(user.getEmail())+"";
		request.setAttribute("participantCount", participations);
		session.setAttribute("participantCount", participations);
		/**
		 * Populating the DB with mock data
		 */
		//StudyDB.populateData();
		if(action == null){
			//TODO Get the user parameter from the session
			if(user != null){
				//Admin Redirection
				if(user.getType().equals("Admin")){
					url = "/admin.jsp";	
				}else{
					System.out.println("User redirecting to main.jsp");
					url = "/main.jsp";
				}
			}else{
				url = "/home.jsp";
			}

		}else if(action.equals("participate")){

			if(user != null){
				String studyCode = request.getParameter("StudyCode");
				if(studyCode == null){
					ArrayList<Study> studies = StudyDB.getStudiesStart();
					request.setAttribute("studies", studies);
					participations = StudyDB.getTotalParticipantsCount(user.getEmail())+"";
					request.setAttribute("participantCount", participations);
					url = "/participate.jsp";
				}else{
					Study study = StudyDB.getStudy(studyCode);
					request.setAttribute("study", study);
					url = "/question.jsp";
				}
			}else{
				url = "/login.jsp";
			}
			
		}else if(action.equals("edit")){
			if(user != null){
				String studyCode = request.getParameter("StudyCode");
				Study study = StudyDB.getStudyEdit(studyCode, user.getEmail());
				request.setAttribute("study", study);
				url = "/editstudy.jsp";
			}else{
				url = "/login.jsp";
			}
		}
                
                
                else if(action.equals("confirmr")){
                    if(user != null){
                        String name = request.getParameter("study_name");
                        String fromEmail = request.getParameter("email");
                        String toEmail = request.getParameter("friend_email");
                        String message = request.getParameter("message");
                        String emailMessage = "Please click the below link to login" + "\n"
                                                +"http://mohan-n.rhcloud.com/Assignment4/userControllerServlet?action=recommendafriend&useremail="+fromEmail;
                        MailClass.sendMail(emailMessage, "Researchers Exchange", "Activate Account", toEmail);
                        message = "Recommended a Friend";
                        MailClass.sendMail(message, fromEmail, name, toEmail);
                        url = "/confirmr.jsp";
                    } else {

                        url = "/home.jsp";
                    }
                }
                
                
                else if(action.equals("report")){
			if(user!=null){ 
                            
                            
				String studyCode = request.getParameter("StudyCode");
				if((!(studyCode == null)) && user.getType().equals("user")){
					//String reporterEmail = request.getParameter("user");
                                        String questionId=request.getParameter("questionId");
					//Study study = StudyDB.getStudy(studyCode);
                                        
					////Date todaysDate = new Date();
					//DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					//String dateCreated = df.format(todaysDate) ;
                                        String reporterEmail=user.getEmail();
					Reports report = new Reports();
                                        report.setStudyCode(studyCode);
                                        report.setQuestionID(questionId);
                                        report.setUserName(reporterEmail);
                                        report.setStatus("Under review");
                                        report.setNumOfParitipants("0");
                                        ReportDB reportDB=new ReportDB();
                                        reportDB.addReport(report);
					
					url = "/confirmrep.jsp";
				}else if(studyCode == null && user.getType().equals("user")){
					//System.out.println("type is user::"+user.getType());
                                        String eMail=user.getEmail();
					ArrayList<Reports> reportsList = ReportDB.getUserReports(eMail);
					request.setAttribute("reports", reportsList);
					url = "/reporth.jsp";
				}else if(studyCode == null && user.getType().equals("admin")){
					ArrayList<Reports> reportsList = ReportDB.getPendingReports();
					//System.out.println(reportsList.toString());
					request.setAttribute("reports", reportsList);
					url = "/reportques.jsp";
				}
			}else{
				url = "/login.jsp";
			}
		}
                
                else if (action.equals("approve")){
			if(user.getType().equals("admin")){
				String studyCode = request.getParameter("StudyCode");
				String userEmail = request.getParameter("reportee");
				System.out.println(studyCode+"::"+userEmail);
                                String questionID= request.getParameter("questionID");
				ReportDB.updateStatus(studyCode, "approve",questionID);
					//StudyDB.deleteStudy(studyCode);
					System.out.println("approved the question");
					ArrayList<Reports> reportsList = ReportDB.getPendingReports();
					System.out.println(reportsList.toString());
					request.setAttribute("reports", reportsList);
				
				url = "/reportques.jsp";
                
                }else{
				url = "/login.jsp";
			}

                }else if (action.equals("disapprove")){
			if(user.getType().equals("admin")){
				String studyCode = request.getParameter("StudyCode");
				String userEmail = request.getParameter("reportee");
				System.out.println(studyCode+"::"+userEmail);
                                String questionID= request.getParameter("questionID");
				ReportDB.updateStatus(studyCode, "disapprove",questionID);
					//StudyDB.deleteStudy(studyCode);
					System.out.println("disapproved the question");
					ArrayList<Reports> reportsList = ReportDB.getPendingReports();
					System.out.println(reportsList.toString());
					request.setAttribute("reports", reportsList);
				
				url = "/reportques.jsp";
                
                }else{
				url = "/login.jsp";
			}
		}
                    else if (action.equals("update")){
			if(user!=null){
				String userEmail = user.getEmail();
				String studyName = request.getParameter("study_name");
				String questionText = request.getParameter("question_text");
				String participantText = request.getParameter("participants");
				String answersCount = request.getParameter("answers");
                                String imageURL = request.getParameter("image_file");
                                //Part filePart = request.getPart("image_file");
                                //String imagesFolder = "";
                                Study study = null;
				Date todaysDate = new Date();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String dateCreated = df.format(todaysDate) ;
				String studyCode = request.getParameter("StudyCode");
                                
                                /*if (filePart.getSize() > 0) {

                                String fileName = filePart.getSubmittedFileName();

                                InputStream fileContent = filePart.getInputStream();

                                try {
                                    File file = new File(imagesPath, fileName);
                                    Files.copy(fileContent, file.toPath());
                                } catch (FileAlreadyExistsException e) {
                                e.printStackTrace();
                            }
                            imagesFolder = "images" + "/" + fileName;
                            //study.setImageURL(imagesFolder);
                        }*/

				ArrayList<String> answersList = null;
				if(answersCount.equals("3")){
					String[] answers = new String[3];
					int dynamicTextBoxCount = 0;
					for(int i = 0; i < answers.length; i++){
						dynamicTextBoxCount ++;
						answers[i]=request.getParameter("DynamicTextBox"+dynamicTextBoxCount);
						System.out.println(answers[i]);
					}
					answersList = new ArrayList<>(Arrays.asList(answers));
				}else if(answersCount.equals("4")){
					String[] answers = new String[4];
					int dynamicTextBoxCount = 0;
					for(int i = 0; i < answers.length; i++){
						dynamicTextBoxCount ++;
						answers[i]=request.getParameter("DynamicTextBox"+dynamicTextBoxCount);
					}
					answersList = new ArrayList<>(Arrays.asList(answers));
				}else if(answersCount.equals("5")){
					String[] answers = new String[5];
					int dynamicTextBoxCount = 0;
					for(int i = 0; i < answers.length; i++){
						dynamicTextBoxCount ++;
						answers[i]=request.getParameter("DynamicTextBox"+dynamicTextBoxCount);
					}
					answersList = new ArrayList<>(Arrays.asList(answers));
				}
				String description = request.getParameter("description");
				System.out.println(userEmail+"::"+studyName+"::"+questionText+"::"+participantText+
						"::"+answersCount+"::"+dateCreated+"::"+studyCode+"::"+answersList.toString()+
						"::"+description);
				study = new Study(studyName,studyCode,userEmail,questionText,imageURL,
						Integer.parseInt(participantText),
						0, description, "start", "text",answersList);
				System.out.println("Inside Update::"+study.toString());
				boolean i = StudyDB.editStudy(studyCode, userEmail, study);
				if(i){
					System.out.println("Updated");
				}
				ArrayList<Study> studies = new ArrayList<>();
				studies = StudyDB.getStudies(user.getEmail());

				System.out.println("Updated");
				request.setAttribute("studies", studies);
				url = "/studies.jsp";
			}else{
				url = "/login.jsp";
			}
		}else if (action.equals("add")){
			if(user!=null){
                                System.out.println("vani11");
				String userEmail = user.getEmail();
				String studyName = request.getParameter("study_name");
				String questionText = request.getParameter("question_text");
				String participantText = request.getParameter("participant_text");
				String answersCount = request.getParameter("answers");
                                String imageURL = request.getParameter("image_file");
                                //Part filePart = request.getPart("image_file");
                                //System.out.println(filePart);
                                String imagesFolder = "";
                                
                                /*if (filePart.getSize() > 0) {
                                    System.out.println("vani");

                                String fileName = filePart.getSubmittedFileName();

                                InputStream fileContent = filePart.getInputStream();

                                try {
                                    File file = new File(imagesPath, fileName);
                                    Files.copy(fileContent, file.toPath());
                                } catch (FileAlreadyExistsException e) {
                                e.printStackTrace();
                            }
                            imagesFolder = "images" + "/" + fileName;
                            //study.setImageURL(imagesFolder);
                        }*/
				
				Random rand = new Random();
				String studyCode = rand.nextInt((1000 - 100) + 1) + 100+"";
				ArrayList<String> answersList = null;
				if(answersCount.equals("3")){
					String[] answers = new String[3];
					int dynamicTextBoxCount = 0;
					for(int i = 0; i < answers.length; i++){
						dynamicTextBoxCount ++;
						answers[i]=request.getParameter("DynamicTextBox"+dynamicTextBoxCount);
						System.out.println(answers[i]);
					}
					answersList = new ArrayList<>(Arrays.asList(answers));
				}else if(answersCount.equals("4")){
					String[] answers = new String[4];
					int dynamicTextBoxCount = 0;
					for(int i = 0; i < answers.length; i++){
						dynamicTextBoxCount ++;
						answers[i]=request.getParameter("DynamicTextBox"+dynamicTextBoxCount);
					}
					answersList = new ArrayList<>(Arrays.asList(answers));
				}else if(answersCount.equals("5")){
					String[] answers = new String[5];
					int dynamicTextBoxCount = 0;
					for(int i = 0; i < answers.length; i++){
						dynamicTextBoxCount ++;
						answers[i]=request.getParameter("DynamicTextBox"+dynamicTextBoxCount);
					}
					answersList = new ArrayList<>(Arrays.asList(answers));
				}
				String description = request.getParameter("description");
				System.out.println(userEmail+"::"+studyName+"::"+questionText+"::"+participantText+
						"::"+answersCount+"::"+studyCode+"::"+answersList.toString()+
						"::"+description);
				Study study = new Study(studyName,studyCode,userEmail,questionText,imageURL,
						Integer.parseInt(participantText),
						0, description, "start", "text",answersList);
				System.out.println("Inside Add::"+study.toString());
				int i = StudyDB.addStudy(study);
				if(i == 1){

					//adding 1 to postedstudies values...
					user.setNumPostedStudies(user.getNumPostedStudies()+1);
					System.out.println("Added");
				}

				ArrayList<Study> studies = new ArrayList<>();
				studies = StudyDB.getStudies(user.getEmail());

				System.out.println("ADDED");
				request.setAttribute("studies", studies);

				url = "/studies.jsp";
			}else{
				url ="/login.jsp";
			}
		}else if (action.equals("start")){
			if(user!=null){
				String studyCode = request.getParameter("StudyCode");
				String userEmail = user.getEmail();
				String status = "start";
				boolean updateStatus = StudyDB.updateStatus(studyCode, userEmail, status);
				ArrayList<Study> studies = new ArrayList<>();
				studies = StudyDB.getStudies(user.getEmail());

				System.out.println("Started");
				request.setAttribute("studies", studies);
				url = "/studies.jsp";
			}else{
				url = "/login.jsp";
			}
		}else if (action.equals("stop")){
			if(user!=null){
				String studyCode = request.getParameter("StudyCode");
				String userEmail = user.getEmail();
				String status = "stop";
				boolean updateStatus = StudyDB.updateStatus(studyCode, userEmail, status);
				ArrayList<Study> studies = new ArrayList<>();
				studies = StudyDB.getStudies(user.getEmail());

				System.out.println("Stopped");
				request.setAttribute("studies", studies);
				url = "/studies.jsp";
			}else{
				url = "/login.jsp";
			}
		}else if (action.equals("answer")){
			if(user!=null){
				String studyCode = request.getParameter("StudyCode");
				String answer = request.getParameter("number");
				Date todaysDate = new Date();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String dateCreated = df.format(todaysDate) ;
				System.out.println("StudyCode::"+studyCode+"::answer::"+answer);
				if(StudyDB.incrementParticipantCount(studyCode)){
					System.out.println("Successfully Inc Count");
				}
				Answer answered = new Answer(dateCreated, user.getEmail(), answer, studyCode);

				if(AnswerDB.addAnswer(answered)){
					//				String date, String email, String choice
					System.out.println("Answer added::"+AnswerDB.getAllAnswers().toString());
					ArrayList<Study> studies = StudyDB.getStudiesStart();
					request.setAttribute("studies", studies);
					 user.setNumCoins(user.getNumCoins()+1);
                     user.setNumParticipation(user.getNumParticipation()+1);
                     System.out.println("user new coins are::"+user.getNumCoins());
                     participations = StudyDB.getTotalParticipantsCount(user.getEmail())+"";
 					request.setAttribute("participantCount", participations);
					url = "/participate.jsp";
				}
			}else{
				url = "/login.jsp";
			}
		}else if (action.equals("studies")){
			if(user!=null){
				ArrayList<Study> studies = new ArrayList<>();
				studies = StudyDB.getStudies(user.getEmail());

				request.setAttribute("studies", studies);
				url = "/studies.jsp";
			}else{
				url = "/login.jsp";
			}
		}
		getServletContext()
		.getRequestDispatcher(url)
		.forward(request, response);
	}

}




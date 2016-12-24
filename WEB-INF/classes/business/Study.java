package business;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author mohan
 *
 */
public class Study implements Serializable {

	private String studyName;
	private String studyCode;
	private String dateCreated;
	private String email;
	private String question;
        private String questionID;
        private String imageURL;

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
   
        private int requestedParticipants;
	private int numOfParitipants;
	private String description;
	private String status;
	private String answerType;
	private ArrayList<String> answer;

    
    public Study() { 
		studyName="";
		studyCode="";
		//dateCreated="";
		email = "";
		question="";
		requestedParticipants=0;
		numOfParitipants=0;
		description="";
		status="";
		answerType="";
		answer=new ArrayList<>();
                imageURL = "";
               

	}
	public Study(String studyName, String studyCode, String email, String question,String imageURL,int requestedParticipants, int numOfParitipants, String description, String status, String answerType, ArrayList<String> answer) { 

        
        this.studyName = studyName;
		this.studyCode=studyCode;
		this.dateCreated = dateCreated;
		this.email = email;
		this.question = question;
		this.requestedParticipants=requestedParticipants;
		this.numOfParitipants=numOfParitipants;
		this.description=description;
		this.status = status;
		this.answerType = answerType;
		this.answer=answer;
                this.imageURL = imageURL;

	}
   

	

	//getter setter methods for all var...
	public String getStudyName() {
		return studyName;
	}
	public void setStudyName(String StudyName) {
		this.studyName = StudyName;
	}


	public String getStudyCode() {
		return studyCode;
	}
	public void setStudyCode(String StudyCode) {
		this.studyCode = StudyCode;
	}


	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String DateCreated) {
		this.dateCreated = DateCreated;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public String getQuestion() {
		return question;
	}
	public void setQuestion(String Question) {
		this.question = Question;
	}


	public int getRequestedParticipants() {
		return requestedParticipants;
	}
	public void setRequestedParticipants(int requestedParticipants) {
		this.requestedParticipants = requestedParticipants;
	}
	public int getNumOfParitipants() {
		return numOfParitipants;
	}
	public void setNumOfParitipants(int numOfParitipants) {
		this.numOfParitipants = numOfParitipants;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String Description) {
		this.description = Description;
	}


	public String getStatus() {
		return status;
	}
	public void setStatus(String Status) {
		this.status = Status;
	}


	public String getAnswerType() {
		return answerType;
	}
	public void setAnswerType(String AnswerType) {
		this.answerType = AnswerType;
	}


	public ArrayList<String> getAnswer() {
		return answer;
	}
	public void setAnswer(ArrayList<String> answer) {
		this.answer = answer;
	}
        
        public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {

        return imageURL;
    }

	// All the following methods are experimental...change accordingly after clarification about its functioning... 
	public double getAverage()
	{
		double x=0;
		return x;
	}
	public int getMinimun()
	{
		int x=0;
		return x;
	}
	public double getMaximum()
	{
		int x=0;
		return x;
	}
	public double getSD()
	{
		double x=0;
		return x;
	}

	
	@Override
	public String toString() {
		return "Study [studyName=" + studyName + ", studyCode=" + studyCode + ", email=" + email + ", question=" + question + ", requestedParticipants=" + requestedParticipants
				+ ", numOfParitipants=" + numOfParitipants + ", imageURL=" + imageURL +",description=" + description + ", status=" + status
				+ ", answerType=" + answerType + ", Answer=" + answer +"]";
	}
     
	}
	

package business;

import java.io.Serializable;
/**
 * 
 * @author mohan
 *
 */

public class Reports implements Serializable {
        private String questionID;
	private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
	private String studyCode;
	//private String reporterEmail;
	private String status="Under review";
	private String dateReported;
        private String numOfParitipants;
        private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
	
	public Reports() {
		super();
	}
	
	public Reports(String questionID, String studyCode, String status, String dateReported,String numOfParitipants) {
		super();
		this.questionID = questionID;
		this.studyCode = studyCode;
		//this.reporterEmail = reporterEmail;
		this.status = status;
		this.dateReported = dateReported;
                this.numOfParitipants = numOfParitipants;
	}
        
        public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getNumOfParitipants() {
        return numOfParitipants;
    }

    public void setNumOfParitipants(String numOfParitipants) {
        this.numOfParitipants = numOfParitipants;
    }
	public String getDateReported() {
		return dateReported;
	}

	public void setDateReported(String dateReported) {
		this.dateReported = dateReported;
	}

	/*public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}*/
	public String getStudyCode() {
		return studyCode;
	}
	public void setStudyCode(String studyCode) {
		this.studyCode = studyCode;
	}
	/*public String getReporterEmail() {
		return reporterEmail;
	}
	public void setReporterEmail(String reporterEmail) {
		this.reporterEmail = reporterEmail;
	}*/
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "Report [questionID=" + questionID + ", studyCode=" + studyCode + ", status=" + status + ", dateReported=" + dateReported + ",numOfParitipants="+ numOfParitipants+"]";
	}
	
	
	
	
	
}

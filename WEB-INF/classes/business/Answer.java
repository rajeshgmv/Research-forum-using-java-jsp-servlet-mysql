package business;

import java.io.Serializable;
/**
 * 
 * @author mohan
 *
 */

public class Answer implements Serializable {

    private String SubmissionDate;
    private String Email;
    private String Choice;
    private String StudyCode;

    
    
   //zero argument constructor...
    public Answer() { 
        SubmissionDate="";
        Email = "";
        Choice="";
        StudyCode="";
    }
    
   
    public Answer(String submissionDate, String email, String choice, String studyCode) {
	super();
	SubmissionDate = submissionDate;
	Email = email;
	Choice = choice;
        StudyCode = studyCode;
}

 public String getStudyCode() {
        return StudyCode;
    }

    public void setStudyCode(String studyCode) {
        this.StudyCode = studyCode;
    }


	//getter setter methods for all var...
    public String getSubmissionDate() {
        return SubmissionDate;
    }
    public void setSubmissionDate(String SubmissionDate) {
        this.SubmissionDate = SubmissionDate;
    }
    
    
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    
    
    public String getChoice() {
        return Choice;
    }
    public void setChoice(String Choice) {
        this.Choice = Choice;
    }



	@Override
	public String toString() {
		return "Answer [SubmissionDate=" + SubmissionDate + ", Email=" + Email + ", Choice=" + Choice + ", studyCode=" + StudyCode + "]";
	}
 
     
}
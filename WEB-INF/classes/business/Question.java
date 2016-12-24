/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;

/**
 *
 * @author mohan
 */
public class Question implements Serializable{

    private String Question;
    private String AnswerType;
    private String Option1;
    private String Option2;
    private String Option3;
     private String Option4;
    private String Option5;
    
   //zero argument constructor...
    public Question() { 
       Question="";
       AnswerType="";
       Option1="";
       Option2="";
       Option3="";
       Option4="";         
       Option5="";
 
    }
    
    // parameterized constructor....
    
    public Question(String Question, String AnswerType, String Option1,String Option2,String Option3,String Option4,String Option5) {
        this.Question = Question;
        
        this.AnswerType= AnswerType;
        this.Option1 = Option1;
        this.Option1 = Option2;
        this.Option1 = Option3;
        this.Option1 = Option4;
        this.Option1 = Option5;
    }    
    
    public String Question() {
        return Question;
    }
    public void setQuestionID(String question) {
		this.Question = question;
	}
    
    public String getAnswerType() {
        return AnswerType;
    }
    public void setAnswerType(String answerType) {
        this.AnswerType = answerType;
    }
    
    
    public String getOption1() {
        return Option1;
    }
    public void setOption1(String option1) {
        this.Option1 = option1;
    }
    
    
    public String getOption2()
    {
        return Option2;
    }
     public void setOption2(String option2)
    {
        this.Option2= option2;
    }
     
     
    public String getOption3()
    {
        return Option3;
    }
     public void setOption3(String option3)
    {
        this.Option3= option3;
    }
     
     
    public String getOption4()
    {
        return Option4;
    }
     public void setOption4(String option4)
    {
        this.Option4= option4;
    }
       public String getOption5()
    {
        return Option5;
    }
     public void setOption5(String option5)
    {
        this.Option5= option5;
    }

	@Override
	public String toString() {
		return "Question [QuestionID=" + Question + ", AnswerType =" + AnswerType
				+ ", Option1=" + Option1 + ", Option2=" + Option2 +", Option3=" + Option3 + ", Option4=" + Option4 + ", Option5=" + Option5 +"]";
	}
     
}
    

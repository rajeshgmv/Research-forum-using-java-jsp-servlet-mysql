<%--
	Document: aboutl.jsp
	Created On: Feb 28, 2016
	Authors: Deepak Rohan, Keval

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<script type="text/javascript" src="js/editstudy.js">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</script>
<%-- Code to display Page Name --%>
<h3 id="page_name">Editing a study</h3>
<%-- Code to go back to Main page  --%>
<a href="StudyController?user=${user.email }" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to input study details --%>
<section>
    <form class="form-horizontal" action="StudyController?user=${user.email }&action=update&StudyCode=${study.studyCode}" method="post">
    
    	<div class="form-group">
        <label class="col-sm-4 control-label">Study Name *</label>
        <div class="col-sm-4">
        <input type="text" class="form-control" name="study_name" value="${study.studyName}" required />
         </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label">Question Text *</label>
        <div class="col-sm-4">
        <input type="text" class="form-control" name="question_text" value="${study.question}" required/>
         </div>
            </div>
            
            <input type="hidden" name="country" id="optionsAvailable" value="${fn:length(study.answer)}">
            <c:set var="totalHours" value="${0}"/>
            <c:forEach var="answer" items="${study.answer }">
            
            <input type="hidden" name="country" id="hiddenAnswers${totalHours }" value="${answer}">
            <c:set var="totalHours" value="${totalHours+1}"/>
            </c:forEach>
       
        
        
        <%-- Img tag is used to import image --%>
        <div class="form-group">
        <label class="col-sm-4 control-label">Image *</label>
        <div class="col-sm-4">
        <img src="<c:out value='${study.getImageURL()}' />" id="edit_study_image"/>
        <input id="image_file" type="file" name="image_file" accept="images/*"  onchange="loadFile(event)" />
        
         </div>
            </div>
        
        
        <div class="form-group">
        <label class="col-sm-4 control-label"># Participants *</label>
         <div class="col-sm-4"> 
        <input type="number" maxlength="2" class="form-control" name="participants" value="${study.requestedParticipants}" required/>
         </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label"># Answers *</label>
        <div class="col-sm-4">
        <select name="answers" class="form-control" id="edit_study_answers">
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select> 
         </div>
            </div>
        
        
        <div id="TextBoxContainer1">
    <!--Textboxes will be added here -->
		</div>
       
       
       <div class="form-group">
        <label class="col-sm-4 control-label">Description *</label>
         <div class="col-sm-4"> 
        <textarea name="description" class="form-control" required >${study.description}</textarea>
         </div>
            </div>
        
        <div class="form-group">
        <div class="col-sm-offset-5 col-sm-4">
        <button type="submit"  class="btn btn-primary">Update</button>
         </div>
            </div>
            <br/><br/><br/>
    </form>
</section>
<script type="text/javascript">
$(document).ready(function() {
	 var options = document.getElementById('optionsAvailable').value;
		$("#edit_study_answers").val(options).change();
//		alert("done");

		if(options == 3){
			var option1 = document.getElementById('hiddenAnswers0').value; 
			var option2 = document.getElementById('hiddenAnswers1').value; 
			var option3 = document.getElementById('hiddenAnswers2').value;
			document.getElementById('1').value = option1;
			document.getElementById('2').value = option2;
			document.getElementById('3').value = option3;
			
		}else if(options == 4){
			var option1 = document.getElementById('hiddenAnswers0').value; 
			var option2 = document.getElementById('hiddenAnswers1').value; 
			var option3 = document.getElementById('hiddenAnswers2').value; 
			var option4 = document.getElementById('hiddenAnswers3').value;
			document.getElementById('1').value = option1;
			document.getElementById('2').value = option2;
			document.getElementById('3').value = option3;
			document.getElementById('4').value = option4;
		}else{
			var option1 = document.getElementById('hiddenAnswers0').value; 
			var option2 = document.getElementById('hiddenAnswers1').value; 
			var option3 = document.getElementById('hiddenAnswers2').value; 
			var option4 = document.getElementById('hiddenAnswers3').value; 
			var option5 = document.getElementById('hiddenAnswers4').value;
			document.getElementById('1').value = option1;
			document.getElementById('2').value = option2;
			document.getElementById('3').value = option3;
			document.getElementById('4').value = option4;
			document.getElementById('5').value = option5;
		}
	
});

</script>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
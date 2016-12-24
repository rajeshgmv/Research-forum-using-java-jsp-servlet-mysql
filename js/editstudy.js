/**
 *  Document: aboutl.jsp
 *	Created On: Feb 4, 2016
 *	Authors: Deepak Rohan
 */

//edit_study_answers
//new_study_answers
//var answers = $("#new_study_answers").val()
//alert(answers)
//
//$('#new_study_answers').on('change', function() {
//	 alert( $(this).find(":selected").val() );
//});
var answers = 3;
var i =1;

 $(document).ready(function() {
	 
	
	 
	 while(answers > 0){
         var div = $("<div />");
         	
     		div.html(GetDynamicTextBox(""));
//     		alert("This is before: "+answers);
     		$("#TextBoxContainer1").append(div);
     		answers--;
     		i++;
//     		alert("This is after: "+answers);
     }
	 
	      $('#edit_study_answers').change(function() {
	    	  	answers = $(this).val();
//	            alert(answers);
	            $("#TextBoxContainer1").html("");
//	            var i =1;
	            i = 1;
	            
	            while(answers > 0){
		            var div = $("<div />");
		            	
	            		div.html(GetDynamicTextBox(""));
//	            		alert("This is before: "+answers);
	            		$("#TextBoxContainer1").append(div);
	            		answers--;
	            		i++;
//	            		alert("This is after: "+answers);
	            }
	      });
	});
 
 function GetDynamicTextBox(value) {
	    return '<div class="form-group"><label class="col-sm-4 control-label">Answer '+i+' *</label><div class="col-sm-4"><input name = "DynamicTextBox'+i+'" id="'+i+'" class="form-control" type="text" required value = "' + value + '" /></div></div>'
	}


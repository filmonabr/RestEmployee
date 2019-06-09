(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);
function makeAjaxCall() {

	let data = JSON.stringify($("#employeeForm").serializeFormJSON());
	$
			.ajax({
				type : "POST",
				url : "/",
				contentType : "application/json",
				data : data,
				success : function(data) {
					$('#formInput').html("");
					$("#formInput")
							.append(
									'<H3 align="center"> New Employee Information <H3>');
					$('#formInput').append(
							"<H4 align='center'>First Name:  " + data.firstName
									+ "</h4>");
					$('#formInput').append(
							"<H4 align='center'>Last Name: " + data.lastName
									+ "</h4>");
					$('#formInput')
							.append(
									"<H4 align='center'>Email: " + data.email
											+ "</h4>");
					$("#formInput")
							.append(
									'<h4 align="center"> <a href="#" onclick="toggle_visibility(\'formInput\');resetForm(\'employeeForm\');"><b>EXIT</b> </a> </h4>');
					make_visible('formInput');
					make_hidden('errors');
				},

				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(XMLHttpRequest.responseJSON);
					$("#errors").empty();
					if (XMLHttpRequest.status == 400) {
						// if (XMLHttpRequest.responseJSON.errorType ==
						// "ValidationError") {
						let errorMsg = '<h3> Error(s)!! </h3>';
						errorMsg += "<p>";
						var errorList = XMLHttpRequest.responseJSON.errors;
						$.each(errorList, function(i, error) {
							errorMsg = errorMsg + error.message + '<br>';
						});
						errorMsg += '</p>';
						$('#errors').append(errorMsg);
						$('#errors').show();
					}

					// } else {
					// alert(errorObject.responseJSON.errors(0)); // "non"
					// Validation
					// }

				}

			});
}

toggle_visibility = function(id) {
	var element = document.getElementById(id);
	if (element.style.display == 'block')
		element.style.display = 'none';
	else
		element.style.display = 'block';
}

make_hidden = function(id) {
	var element = document.getElementById(id);
	element.style.display = 'none';
}

make_visible = function(id) {
	var element = document.getElementById(id);
	element.style.display = 'block';
}

resetForm = function(id) {
	var element = document.getElementById(id);
	$(element)[0].reset();

}

// Translate form to array
// Then put in JSON format
function serializeObject(form) {
	var jsonObject = {};
	var array = form.serializeArray();
	$.each(array, function() {
		jsonObject[this.name] = this.value;
	});
	return jsonObject;

};


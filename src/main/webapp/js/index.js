/**
 * Javascript allows global variables/functions, which locate inside window.
 */

var host = "http://chihmin-ask.appspot.com";
$(document).ready(function(){
	function updateMsg() {
		var title = $('#askinput').val();
        var request = $.ajax({
            url: host + "/comments/" + title,
            type: 'GET',
            success: function (data, status) {
				$('#comment').empty();
				
                var msgArray = eval(data);
				
                var i;
                for (i = 0; i < msgArray.length; i++) {
                    var msg = msgArray[i];
                    var title = msg['title'].toString();
					var cont = msg['content'].toString();
					cont = cont.replace(/&/g, "&amp;")
								.replace(/</g, "&lt;")
								.replace(/>/g, "&gt;")
								.replace(/"/g, "&quot;")
								.replace(/'/g, "&#039;");

					$("#comment").append('<div class="list-group-item">' + '<h4 class="list-group-item-heading">[' + title + ']</h4><p>' + cont + '</p><p class="text-right small">' + '</p><hr></div>');
                }

            },
            dataType: 'json'
        });
    };



	$('#add-btn').on('click', function () {
		console.log("post request");
        var title = $('#askinput').val();
        var description = $('#def-input').val();
        console.log( description );
        $.ajax({
            url: host + "/definitions",
            type: 'POST',
            success: function () {
				showAnswer('I have add the definitions!!');
            },
            data: JSON.stringify({

                "description": description,
                "title": title
            }),
            contentType: 'application/json'
        });
    });


	$('#comment-btn').on('click', function () {
        var msg = $('#comment-input').val();
        var title = $('#askinput').val();
        console.log("title is " + title );
        console.log( "comment is " + msg );
        $.ajax({
            url: host + "/comments",
            type: 'POST',
            success: function () {
                
				updateMsg();
            },
            data: JSON.stringify({
            	"title": title,
                "content": msg
            }),
            contentType: 'application/json'
        });

    });

onload = function() {
	initAskButton();
};

var initAskButton = function(){
	var askButton = document.getElementById('askbtn');
	var qtext = document.getElementById('askinput');
	askButton.onclick = function(e) {
		updateMsg();
		getAnswer();
	};
	qtext.onkeyup = function(e) {
		if(e.keyCode == 13) // enter pressed
			getAnswer();
			updateMsg();
	}; 
};

var getAnswer = function() {
	// indicate loading
	var question = document.getElementById('askinput').value;
	question = encodeURIComponent(question);
	answerEl = document.getElementById('answer');
	addClass(answerEl, 'content-hidden');
	var imgEl = document.getElementById('loading');
	removeClass(imgEl, 'content-hidden');
	console.log( question );

	if (question == '') {
		showAnswer('');
		return;
	}
	ajaxRequest('GET', 'definitions/' + question, {
		'Accept': 'application/json'
	}, null, function(status, headers, body) { // success callback
		// show answer
		var ans = eval('(' + body + ')');
		showAnswer(ans.description);
		$('#comment-field').show();

	}, function(status, headers, body) {  // error callback
		switch (status) {
			case 404: 
				status404Handler();
				var tmp = document.getElementById('addDef');
				removeClass(tmp, 'content-hidden');
			default:
				// do nothing
		}
	}, null); // run callbacks in global scope
};

var showAnswer = function(answer) {
	var answerEl = document.getElementById('answer');
	answerEl.innerHTML = escapeHtml(answer);
	answerEl.innerHTML = answerEl.innerHTML.replace(/\n/ig, '<br />'); 
	removeClass(answerEl, 'content-hidden');
	// hide loading indicator
	var imgEl = document.getElementById('loading');
	addClass(imgEl, 'content-hidden');
};

var status404Handler = function() {
	showAnswer('Sorry, I don\'t understand your question! Please enter your definition : ');

};

});
